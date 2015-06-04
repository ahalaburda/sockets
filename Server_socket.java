/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.*;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.File;
import java.sql.*;


class Server_socket{
    public static void main(String argv[]) throws Exception{
          String clientSentence,clientpassword;
          String capitalizedSentence;
          ServerSocket welcomeSocket = new ServerSocket(22);
          String url = "jdbc:mysql://localhost:3306/redes";
          String db = "redes";
          String driver ="com.mysql.jdbc.Driver";
          String user = "root";
          String pass = "";

          while(true){
             Socket connectionSocket = welcomeSocket.accept();
             
             BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
             BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
             
             DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
             clientSentence = inFromClient.readLine(); 
             clientpassword = inFromClient.readLine();
             
             System.out.println("Received User Name: " + clientSentence);
             System.out.println("Received Password: " + clientpassword);

                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                
                //load jdbc driver for mysql database
                try {
                  Class.forName("org.gjt.mm.mysql.Driver");
                }catch(Exception e) {
                  System.out.println("Unable to load Driver");
                }

             try {
                 connection = (Connection) DriverManager.getConnection(url, user, pass);
                 System.out.println("Conexion realizada");
                } catch (SQLException e) {
                  System.out.println("Unable to connect to database");
                }  
                //if connection is successfully established, create statement
                 if(connection != null) {
                    try {
                      System.out.println("estas en el try, pide lo que quieras!!!!");
                      statement = connection.createStatement();
                    } catch (SQLException e) {
                       System.out.println("Unable to create statement");
                    }
                }
                //if statement is created successfully, execute query and get results
                
              if(statement != null) {
                 try {
                  System.out.println("intentando hacer un select!!!");
                     resultSet = statement.executeQuery("SELECT * FROM  users");
                   } catch (SQLException e) {
                        System.out.println("Unable to create statement");
                   }
              }


             
             // Class.forName(driver).newInstance();
             
  
             while (resultSet.next()) { 
                String u = resultSet.getString("username");
                // String p = resultSet.getString("encrypted_password");
                // if (clientSentence.equals(u) && clientpassword.equals(p)){
                if (clientSentence.equals(u)){
                    //capitalizedSentence = "Welcome "+clientSentence+" \n";
                    //outToClient.writeBytes(capitalizedSentence); 
                  System.out.println(u);
                }else{  
                  System.out.println("no se puede retornar al cliente");
                    //capitalizedSentence = "Sorry, not authorized \n";
                    //outToClient.writeBytes(capitalizedSentence); 
                }    
          }
             connection.close();
        }
          
       }
}