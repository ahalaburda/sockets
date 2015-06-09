/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
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



/**
 *
 * @author Usuario1
 */
public class JsonReader {
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
  
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    try (InputStream is = new URL(url).openStream()) {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String output = null;
      String jsonText = readAll(rd);
       output = jsonText.replace("[", "").replace("]", "");
      JSONObject json = new JSONObject(output);
      return json;
    }
  }
 public static void main(String[] args) throws IOException, JSONException {
     
    JSONObject json = readJsonFromUrl("http://localhost:3000/marcas.json");
    System.out.println(json.toString());  
    System.out.println(json.get("descripcion"));
}
}