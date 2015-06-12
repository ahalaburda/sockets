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

    String response;

    String direccion = argv[0];
    int puerto = Integer.parseInt(argv[1]);
    
    Socket clientSocket = new Socket(direccion, puerto);
   
    BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
    BufferedReader inFromUser1 = new BufferedReader( new InputStreamReader(System.in));
    BufferedReader inFromUser2 = new BufferedReader( new InputStreamReader(System.in));
    BufferedReader inFromUser3 = new BufferedReader( new InputStreamReader(System.in));


    Console console = System.console();
    System.out.println("Conexion establecida con Exito!!!");

    String format = console.readLine("FORMAT:");
    String data = console.readLine("DATA:");
    String result = console.readLine("RESULT:");
    String resource = console.readLine("RESOURCE:");


    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    DataOutputStream outToServer1 = new DataOutputStream(clientSocket.getOutputStream());
    DataOutputStream outToServer2 = new DataOutputStream(clientSocket.getOutputStream());
    DataOutputStream outToServer3 = new DataOutputStream(clientSocket.getOutputStream());
      outToServer.writeBytes(format + '\n');
      outToServer.writeBytes(data + '\n');
      outToServer.writeBytes(result + '\n');
      outToServer.writeBytes(resource + '\n');

    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));       
    response = inFromServer.readLine();
    System.out.println(response);
      
    clientSocket.close();
    }
}