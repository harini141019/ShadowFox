import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    // Map<roomName, Set<ClientHandler>>
    private static Map<String, Set<ClientHandler>> chatRooms = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Chat server started on port " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private String userName;
        private String roomName;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Get username
                out.println("Enter your username:");
                userName = in.readLine();

                // Get room name
                out.println("Enter room to join (will be created if doesn't exist):");
                roomName = in.readLine();

                // Join room
                synchronized (chatRooms) {
                    chatRooms.putIfAbsent(roomName, new HashSet<>());
                    chatRooms.get(roomName).add(this);
                }
                broadcast("[" + userName + " joined the room]", false);

                // Listen for messages
                String msg;
                while ((msg = in.readLine()) != null) {
                    broadcast(userName + ": " + msg, true);
                }
            } catch (IOException e) {
                System.out.println("Connection error: " + e.getMessage());
            } finally {
                // Remove user from room and close socket
                synchronized (chatRooms) {
                    if (roomName != null && chatRooms.containsKey(roomName)) {
                        chatRooms.get(roomName).remove(this);
                        broadcast("[" + userName + " left the room]", false);
                        if (chatRooms.get(roomName).isEmpty()) {
                            chatRooms.remove(roomName);
                        }
                    }
                }
                try { socket.close(); } catch (IOException ignored) {}
            }
        }

        private void broadcast(String message, boolean includeSender) {
            synchronized (chatRooms) {
                for (ClientHandler client : chatRooms.get(roomName)) {
                    if (client != this || includeSender) {
                        client.out.println(message);
                    }
                }
            }
        }
    }
}