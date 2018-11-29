/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2waytcp;
import java.awt.ScrollPane;
import java.util.Scanner;
import java.io.*;
import java.net.*;
import javax.swing.JTextArea;
/**
 *
 * @author K152152
 */
public class Client{
    
    protected Socket s;
    DataInputStream dis,in;
    DataOutputStream dos;
    String str=null;
    
    static int port;
    String msg;
    String smsg;
    JTextArea ta;
    ScrollPane  scroll;
    
    public Client(int port) throws IOException
    {
        this.port = port;
        s=new Socket("localHost",port);
        s.setKeepAlive(true);
        in=new DataInputStream(System.in);
        dos=new DataOutputStream(s.getOutputStream());
        dis=new DataInputStream(s.getInputStream());
        ta = new JTextArea(20, 20);
    }
    public void setGUIcomponent(JTextArea ta,ScrollPane scroll){
        this.ta = ta;
        this.scroll = scroll;
    }
    public void Send() throws IOException
    {
        System.out.print("CLIENT: ");
        str=this.getmsg();
        dos.writeUTF(str);
        if(str.equalsIgnoreCase("abort"))
        {
            s.close();
            System.exit(0);
        }
        str=this.getmsg();
        
    }
    public void setmsg(String str){
        this.msg = str;
    }
    public String getmsg(){
        return this.msg;
    } 
    public void setport(int port){
        this.port = port;
    }
    public static int getport(){
        return port;
    }
    public void closeCon() throws IOException
    {
        s.close();
    }
    public void Recieve() throws IOException
    {
        while(true)
        {
            str=dis.readUTF();
            ta.append("SERVER>> "+str+'\n');
            scroll.add(ta);
            System.out.print("\nSERVER: "+str+'\n');
            System.out.print("CLIENT: ");
            if(str.equalsIgnoreCase("abort"))
            {
                s.close();
                System.exit(0);
                break;
            }
        }
    }
//    public static void main(String[] args) throws IOException {
//       
//        final Client client=new Client(778);
//        Thread r=new Thread()
//        {
//          @Override
//          public void run()
//          {
//                try 
//                {
//                    client.Recieve();
//                } 
//                catch (IOException e) 
//                {
//                    System.out.println(e);
//                }
//          }
//        };
//        Thread s=new Thread()
//        {
//            @Override
//            public void run()
//            {
//                try 
//                {
//                    client.Send();
//                } 
//                catch (IOException e) 
//                {
//                    System.out.println(e);
//                }
//            }
//        };
//        r.start();
//        s.start();
//        }
    }
