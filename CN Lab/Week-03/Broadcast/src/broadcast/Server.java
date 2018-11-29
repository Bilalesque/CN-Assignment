/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package broadcast;

import java.io.*;
import java.net.*;

/**
 *
 * @author K152152
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        DatagramSocket ds=new DatagramSocket();
        DataInputStream in=new DataInputStream(System.in);
        String str, str1;
        while(true)
        {
            byte[] arr=new byte[1024];
            System.out.println("\nEnter Broadcast MSG: ");
            str=in.readLine();
            arr=str.getBytes();
            DatagramPacket dp=new DatagramPacket(arr,arr.length,InetAddress.getLocalHost(),8000);
            ds.send(dp);
            System.out.println("Client: "+dp.getAddress());
            dp=new DatagramPacket(arr,arr.length,InetAddress.getLocalHost(),8001);
            ds.send(dp);
            System.out.println("Client: "+dp.getAddress());
            dp=new DatagramPacket(arr,arr.length,InetAddress.getLocalHost(),8002);
            ds.send(dp);
            System.out.println("Client: "+dp.getAddress());
            if(str.equalsIgnoreCase("abort"))
                break;
            
        }
    }
    
}
