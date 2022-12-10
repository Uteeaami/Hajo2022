package fi.utu.tech.assignment2;

import java.io.DataOutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class Client {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle
        //Lähetettävä merkkijono
        String stringToSend = "Hello";

        try{
            //Luodaan socket-olio ja yhdistää sillä odottavaan porttiin
            Socket socket=new Socket("localhost",6666);
            //DataOutputStream kirjoittamista varten
            DataOutputStream dOut=new DataOutputStream(socket.getOutputStream());
            //Muunnetaan merkkijono tavutauluksi
            byte[] byteToSend = stringToSend.getBytes(StandardCharsets.UTF_8);
            //Kirjoitus tavuvirtaan
            dOut.write(byteToSend);
            dOut.flush();
            dOut.close();

        }catch(Exception e){System.out.println(e);}


    }

}
