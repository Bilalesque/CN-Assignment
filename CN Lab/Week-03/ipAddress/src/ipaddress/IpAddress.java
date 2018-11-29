/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipaddress;
import java.io.*;
import java.net.*;
/**
 *
 * @author Lenovo
 */
public class IpAddress {
    public InetAddress ip;
    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args)throws UnknownHostException {
     
    InetAddress ip=InetAddress.getLocalHost();
    System.out.println("\n IP address is :"+ip);
    String s1=ip.getHostName();
    System.out.println("system number is:"+s1);
    InetAddress ip1=InetAddress.getByName("system 10");
    System.out.println("\n name of other system is :"+ip1);
    
    }
}   
