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
public class Client2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        DatagramSocket ds=new DatagramSocket(8001);
        while(true)
        {
            byte[] arr=new byte[1024];
            DataInputStream in=new DataInputStream(System.in);
            String str;
            DatagramPacket dp=new DatagramPacket(arr,arr.length);
            ds.receive(dp);
            str=new String(dp.getData(), 0, dp.getLength());
            InetAddress address=dp.getAddress();
            System.out.println("From :" + address);
            System.out.println("MSG: "+str);
            if(str.equalsIgnoreCase("abort"))
                break;
        }
    }
    
}
