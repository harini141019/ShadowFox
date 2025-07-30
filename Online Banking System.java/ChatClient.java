import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER, PORT);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        // Thread to read messages from server
        new Thread(() -> {
            String line;
            try {
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        }).start();

        // Main thread for user input
        String input;
        while ((input = userInput.readLine()) != null) {
            out.println(input);
            if (input.equals("/quit")) break;
        }

        socket.close();
        System.exit(0);
    }
} 
    
