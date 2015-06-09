/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Usuario1
 */
public class JsonToJava {
    public static void main(String[] args) throws IOException {
        try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("http://localhost:3000/productos/1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            Marcas p = gson.fromJson(reader, Marcas.class);
            System.out.println(p);
        }
    }
}
