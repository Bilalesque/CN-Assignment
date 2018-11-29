/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;
import java.net.*;
import java.io.*;
/**
 *
 * @author K152152
 */
public class Client {

    public static void main(String[] args) throws IOException{
        Socket s=new Socket(InetAddress.getLocalHost(),8001);
        DataInputStream in=new DataInputStream(s.getInputStream());
        String str;
        while((str=in.readLine())!=null){
            System.out.println("File Received. The Contents of The File Are:");
            System.out.println(str);
        }
        in.close();
        s.close();
    }
    
}
