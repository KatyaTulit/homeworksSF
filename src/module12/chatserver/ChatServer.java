package module12.chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    /*

    К серверу может присоединиться несколько клиентов одновременно.
    Всё, что пишется на любом из клиентов, передается в окна других клиентов после нажатия на Enter.
    При отсоединении любого клиента программа должна продолжать работать корректно.
    Уже присоединенные клиенты продолжают общаться между собой, и к ним могут присоединиться новые.

    Когда клиент присоединяется или отсоединяется, остальным участникам чата об этом сообщается.

    */

    protected ArrayList<Client> clients = new ArrayList<>();
    protected ServerSocket server;

    ChatServer() throws IOException {
        // создаем серверный сокет на порту 1234
        server = new ServerSocket(1234);
    }

    public void sendAllMessages(String message) {
        // отправляем всем клиентам сообщения
        for (Client client : clients) {
            client.receiveMessage(message);
        }
    }

    public static void main(String[] args) throws IOException {

        ChatServer chatServer = new ChatServer();

        while(true) {
            System.out.println("Waiting...");

            // ждем клиента из сети
            Socket socket = chatServer.server.accept();
            System.out.println("Client connected!");

            // создаем клиента, который сам запустится
            Client client = new Client(socket, chatServer);
            chatServer.clients.add(client);
        }
    }
}
