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
import org.json.*;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

class Server_socket{
    public static void main(String argv[]) throws Exception{
          int puerto = Integer.parseInt(argv[0]);
          String username = argv[1];
          String password = argv[2];
          String clientformat;
          String clientdata;
          String clientresult;
          String clientresource;
          String url_login = "http://localhost:3000/users/sign_in";

          ServerSocket welcomeSocket = new ServerSocket(puerto);

          HttpClient httpClient = new DefaultHttpClient();
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


          while(true){
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromClient1 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromClient2 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            BufferedReader inFromClient3 = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            clientformat = inFromClient.readLine();
            clientdata = inFromClient1.readLine();
            clientresult = inFromClient2.readLine();
            clientresource = inFromClient3.readLine();

            System.out.println("FORMAT: " + clientformat);
            System.out.println("DATA: " + clientdata);
            System.out.println("RESULT: " + clientresult);
            System.out.println("RESOURCE: " + clientresource);

              String url = "http://localhost:3000/"+clientresource+"/"+clientdata+"."+clientformat;

              try (InputStream is = new URL(url).openStream()) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                
                StringBuilder sb = new StringBuilder();
                int cp;
                while ((cp = rd.read()) != -1) {
                  sb.append((char) cp);
                }
                String stb =sb.toString();

                String output = null;
                String jsonText = stb;
                
                output = jsonText.replace("[", "").replace("]", "");
                JSONObject json = new JSONObject(output);
                System.out.println(json.toString());  
                
              }

             connectionSocket.close();
          }
        } 
}