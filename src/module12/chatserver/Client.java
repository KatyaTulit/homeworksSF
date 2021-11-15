package module12.chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    private final Socket socket;
    private final ChatServer server;
    private String name = "Anonymous client";
    private PrintStream out;

    public Client(Socket socket, ChatServer server){
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }

    public void receiveMessage(String message) {
        out.println(message);
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            Scanner in = new Scanner(is);
            out = new PrintStream(os);

            // сначала узнаем имя клиента и сообщаем о прибытии всем участникам чата
            out.println("Welcome to the chat. Please type your name first");
            this.name = in.nextLine();
            server.sendAllMessages(this.name + " joined the chat.");

            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAllMessages(input);
                input = in.nextLine();
            }
            socket.close();

            // сообщаем всем участникам, что клиент покинул чат
            server.sendAllMessages(this.name + "left the chat. Bye-bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
