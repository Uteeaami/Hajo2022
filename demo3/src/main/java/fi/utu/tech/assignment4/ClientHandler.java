package fi.utu.tech.assignment4;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    // TODO: Toteuta asiakaspalvelija tänne

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run(){

        //Output ja input
        PrintWriter output;
        BufferedReader input;

        try {

            output = new PrintWriter(clientSocket.getOutputStream(),true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;

            while((line = input.readLine())!= null){

                //Mikäli input "Hello/hello", niin lähetetään Clientille Ack
                if("Hello".equalsIgnoreCase(line)) {
                    output.println("Ack");
                }//if

                //Mikäli input "Quit/quit" suljetaan yhteys
                if("quit".equalsIgnoreCase(line)){
                    this.clientSocket.close();
                    System.err.println("Client " + Thread.currentThread().getName() + " left..");
                }else{
                    System.out.printf(" Sent from the client: %s\n", line);
                }//else

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        }//run


    }


