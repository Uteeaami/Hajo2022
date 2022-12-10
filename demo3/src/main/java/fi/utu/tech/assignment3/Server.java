package fi.utu.tech.assignment3;

import java.io.IOException;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle

        System.out.println("Listening for clients");
        //Luodaan serversocket-olio
        ServerSocket listener=new ServerSocket(6666);
        try{
            while(true){
                //Tehdään socket-olio, joka alkaa kuuntelemaan yhteydenottoja
                Socket socket=listener.accept();
                System.out.println("New Client joined");

                //ClientHandler, jossa asiakaspalvelutoiminnallisuus
                ClientHandler client = new ClientHandler(socket);

                //Käynnistetään säie
                new Thread(client).start();
            }
        }catch(IOException e){e.printStackTrace();}

        System.err.println("Server closed....");

    }
}

