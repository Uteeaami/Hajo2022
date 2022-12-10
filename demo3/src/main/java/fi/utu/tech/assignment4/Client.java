package fi.utu.tech.assignment4;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        // TODO: Kopioi edellisen tehtäväsi vastaus tähän pohjalle

        try{
            Scanner scanner = new Scanner(System.in);
            String line = null;
            Socket socket=new Socket("localhost",6666);
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true); // OUTPUT
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //INPUT

            while(!"quit".equalsIgnoreCase(line)){
                line = scanner.nextLine();
                output.println(line);
                output.flush();

                //Todnäk ei 'haluttu' tapa toteuttaa serveriltä tulleen "Ack" merkkijonon tulostusta
                if("Hello".equalsIgnoreCase(line)){
                    System.out.println(input.readLine());
                    line = "Ack acknowledged..";
                    output.println(line);
                    output.flush();
                }//if
            }//while

        }catch(Exception e){
            e.printStackTrace();
        }
    }//main

}
