/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adrian
 */
import java.io.*;
import java.net.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.lang.*;
import java.util.Scanner;

class TCPclient{
  public static void main(String argv[]) throws Exception{
    String sentence,sentence1;
    String modifiedSentence;
    
    Socket clientSocket = new Socket("localhost", 22);
   
    BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
    BufferedReader inFromUser1 = new BufferedReader( new InputStreamReader(System.in));

    Console console = System.console();
      String email = console.readLine("Enter your email :");
      char pswd[] = console.readPassword("Enter your Password :");
      String upwd=new String(pswd);
      DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
      DataOutputStream outToServer1 = new DataOutputStream(clientSocket.getOutputStream());
        outToServer.writeBytes(email + '\n');
        outToServer1.writeBytes(upwd + '\n');

    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));       
    modifiedSentence = inFromServer.readLine();
    System.out.println("FROM SERVER: " + modifiedSentence);
      
    clientSocket.close();
    }
}