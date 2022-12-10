package fi.utu.tech.assignment1;


import java.net.*;

public class Client {

    public static void main(String[] args) {
        // TODO: Asiakasohjelma

        try{
            //Luodaan socket-olio ja yhdistää sillä odottavaan porttiin
            Socket s = new Socket("localhost",6666);

        }catch (Exception e){
            System.err.println("Did not find a server");
            e.printStackTrace();

        }
    }

}
