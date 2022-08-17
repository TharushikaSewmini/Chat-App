package lk.ijse.chatapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private ArrayList<ClientHandler> clients;
    private Socket accept;
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) throws IOException {
        this.accept = socket;
        this.clients=clients;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

    }

    public void run() {
        try {
            String message;
            while (!(message = dataInputStream.readUTF()).equalsIgnoreCase("")) {
                if (message.equalsIgnoreCase("Exit")){
                    break;
                }

                for (ClientHandler cl : clients) {
                    cl.dataOutputStream.writeUTF(message);
                    System.out.println(message);
                }
            }
            dataOutputStream.writeUTF("Client Exit");
            accept.close();
            dataOutputStream.close();
            dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                dataInputStream.close();
                dataOutputStream.close();
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
