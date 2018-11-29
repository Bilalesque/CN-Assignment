/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2wayudp;
import java.io.*;
import java.net.*;
/**
 *
 * @author K152152
 */
public class Sender {
    DatagramSocket ds;
    DatagramPacket dp,dp1;
    byte buff[]=new byte[1024];
    byte buff1[]=new byte[1024];
    String str,str1;
    boolean flag=true;
    public void send() throws IOException
    {
        while(true) 
        {
            ds=new DatagramSocket();
            DataInputStream in=new DataInputStream(System.in);
            System.out.println("Enter MSG: ");
            str=in.readLine();
            buff=str.getBytes();
            dp=new DatagramPacket(buff,buff.length,InetAddress.getLocalHost(),8000);
            ds.send(dp);  
            if(str.equalsIgnoreCase("abort"))
            {
                ds.close();
                break;
            }
            dp1=new DatagramPacket(buff1,buff1.length);
            ds.receive(dp1);
            str1=new String (dp1.getData(),0,0,dp1.getLength());
            if(str1.equalsIgnoreCase("abort"))
            {
                ds.close();
                break;
            }
            System.out.println("MSG Received From Receiver: "+str1);
            System.out.println("InetAddress of Receipient:"+dp1.getAddress()); 
        }
    }
    
    public static void main(String[] args) throws IOException{
    Sender se=new Sender();
    se.send();    
    }
    
}
