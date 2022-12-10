package fi.utu.tech.assignment2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle

        System.out.println("Listening for clients");
        try{
            //Luodaan serversocket-olio
            ServerSocket listener=new ServerSocket(6666);

            //Tehdään socket-olio, joka alkaa kuuntelemaan yhteydenottoja
            try(Socket socket=listener.accept()) {
                System.out.println("New Client joined");

                //DataInputStream, josta voi lukea toisen osapuolen lähettämän datan
                DataInputStream dIn = new DataInputStream(socket.getInputStream());

                //Tallennetaan viesti tavutauluun käyttämällä readAllBytes-metodia
                byte[] received = dIn.readAllBytes();

                //Muutetaan merkkijonoksi
                String msg = new String(received, "utf-8");

                System.out.println("message: "+ msg);
            }
        }catch(IOException e){e.printStackTrace();}

        System.err.println("Server closed....");

    }
}



