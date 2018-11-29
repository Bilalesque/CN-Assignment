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
public class Reciever {

    DatagramSocket ds;
    DatagramPacket dp,dp1;
    byte buff[]=new byte[1024];
    byte buff1[]=new byte[1024];
    String str,str1;
    boolean flag=true;
    public void receive() throws IOException
    {
        ds=new DatagramSocket(8000); 
        while(true) 
        { 
            dp=new DatagramPacket(buff,buff.length);
            ds.receive(dp);
            str=new String (dp.getData(),0,0,dp.getLength());
            if(str.equalsIgnoreCase("abort"))
            {
                ds.close();
                break;
            }
            System.out.println("MSG: "+str);
            System.out.println("InetAddress of Server:"+dp.getAddress()); 
            System.out.println("Port no:"+dp.getPort()); 
            DataInputStream in=new DataInputStream(System.in);
            System.out.println("Enter MSG For Server:");
            str1=in.readLine();
            buff1=str1.getBytes();
            dp1=new DatagramPacket(buff1,buff1.length,dp.getAddress(),dp.getPort());
            ds.send(dp1);  
            if(str1.equalsIgnoreCase("abort"))
            {
                ds.close();
                break;
            }
        }
    }
    
    public static void main(String[] args)throws IOException {
    Reciever re=new Reciever();
    re.receive();
    }
}
