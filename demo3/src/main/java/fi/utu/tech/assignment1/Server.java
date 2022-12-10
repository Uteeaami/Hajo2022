package fi.utu.tech.assignment1;

import java.io.IOException;
import java.net.*;

public class Server {




    public static void main(String[] args) {
        // TODO: Palvelinohjelma

        System.out.println("Listening for clients");
        try{
            //Luodaan serversocket-olio
            ServerSocket listener = new ServerSocket(6666);

            //Tehdään socket-olio, joka alkaa kuuntelemaan yhteydenottoja
            try(Socket socket = listener.accept()){
                System.out.println("Client was here");
            }

        }catch (IOException e){e.printStackTrace();}

        System.err.println("Server closed...");
    }
    
}
