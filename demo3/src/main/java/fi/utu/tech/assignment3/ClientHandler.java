package fi.utu.tech.assignment3;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    // TODO: Toteuta asiakaspalvelija tänne

    //Socket-olio
    private final Socket clientSocket;

    //Konstruktori
    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){
        try{
            //Asiakaspalvelutoiminnallisuus siirretty Server-luokasta
            DataInputStream dIn = new DataInputStream(clientSocket.getInputStream());
            byte[] received = dIn.readAllBytes();
            String msg = new String(received, "utf-8");
            System.out.println("message: "+ msg);

            System.err.println("Client "+Thread.currentThread().getName()+" leaving..");

        }catch (IOException e){e.printStackTrace();}

    }

}
