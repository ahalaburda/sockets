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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.*;
import org.json.JSONObject;
import org.json.JSONException;

/*import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;*/


class Server_socket{ // java ProxyServer <puerto> <Usuario> <contraseña>
    public static void main(String argv[]) throws Exception{

          int puerto = Integer.parseInt(argv[0]);
          //String username = argv[1];
          //String password = argv[2];
          String clientformat;
          String clientdata;
          String clientresult;
          String clientresource;
          String url_login = "http://localhost:3000/users/sign_in";

          ServerSocket welcomeSocket = new ServerSocket(puerto);

          /*HttpClient httpClient = new DefaultHttpClient();
          HttpPost httpPost = new HttpPost(url_login);

  
              // Add your data
              List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
              // nameValuePairs.add(new BasicNameValuePair("utf8", Character.toString('\u2713')));
              nameValuePairs.add(new BasicNameValuePair("username", username));
              nameValuePairs.add(new BasicNameValuePair("password", password));
              // nameValuePairs.add(new BasicNameValuePair("commit", "Sign in"));
              httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

              // Execute HTTP Post Request
              HttpResponse response = httpClient.execute(httpPost);
              String ret = EntityUtils.tostring(response.getEntity());
               System.out.println(ret);


*/          while(true){
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
             clientformat = inFromClient.readLine();
             System.out.println("FORMAT: " + clientformat);
            BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientdata = inFromClient1.readLine();
             System.out.println("DATA: " + clientdata);
            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
             clientresult = inFromClient2.readLine();
             System.out.println("RESULT: " + clientresult);
            BufferedReader inFromClient3 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            clientresource = inFromClient3.readLine();
            System.out.println("RESOURCE: " + clientresource);
            System.out.println("no pasas de aqui"); 

              String url = "http://localhost:3000/"+clientresource+"/"+clientdata+"."+clientformat;
              System.out.println(url); 
              try (InputStream is = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                  sb.append((char) cp);
                }
                String stb =sb.toString();
                System.out.println("estas aqui"); 

                String output = null;
                String jsonText = stb;
                
                output = jsonText.replace("[", "").replace("]", "");
                JSONObject json = new JSONObject(output);

                System.out.println(json.toString());  
                System.out.println("llegaste al final"); 
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
              outToClient.writeBytes(json.toString());
                
              }
              

             connectionSocket.close();
          }
        } 
}