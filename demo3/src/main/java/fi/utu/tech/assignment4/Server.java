package fi.utu.tech.assignment4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle

        System.out.println("Listening for clients");
        //Luodaan serversocket-olio
        ServerSocket listener=new ServerSocket(6666);

        try {

            while (true) {
                Socket socket = listener.accept();
                System.out.println("New client joined");

                ClientHandler client = new ClientHandler(socket);

                new Thread(client).start();

            }//while
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
