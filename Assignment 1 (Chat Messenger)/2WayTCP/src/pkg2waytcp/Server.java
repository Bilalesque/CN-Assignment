package pkg2waytcp;
import java.awt.ScrollPane;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.swing.JTextArea;

public class Server{
    
    ServerSocket ss;
    Socket s;
    DataOutputStream dos;
    DataInputStream dis,in;
    String str=null;
    static int port;
    String msg;
    String smsg;
    JTextArea ta;
    ScrollPane  scroll;
    public Server(int port) throws IOException
    {
        this.port = port;
        ss=new ServerSocket(port);
        s=ss.accept();
        s.setKeepAlive(true);
        dos=new DataOutputStream(s.getOutputStream());
        in=new DataInputStream(System.in);
        dis=new DataInputStream(s.getInputStream());
        ta = new JTextArea(20, 20);
    }
    public void setGUIcomponent(JTextArea ta,ScrollPane scroll){
        this.ta = ta;
        this.scroll = scroll;
    }
    
    public void Send() throws IOException
    {
       
            System.out.print("SERVER: ");
            //str=in.readLine();
            str=this.getmsg();
            
            dos.writeUTF(str);
            if(str.equalsIgnoreCase("abort"))
            {
                s.close();
                System.exit(0);
            }
        
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
    public static int getsport(){
        return port;
    }
    public void Recieve() throws IOException
    {
       
            while(true){
                str=dis.readUTF();
            ta.append("CLIENT>> "+str+'\n');
            scroll.add(ta);
            //System.out.print("\nCLIENT: "+str+'\n');
            
            if(str.equalsIgnoreCase("abort"))
            {
                s.close();
                System.exit(0);
            }
            }
    }
    public void closeCon() throws IOException
    {
        s.close();
    }
//    public static void main(String[] args) throws IOException{
//        
//        final Server server=new Server(8000);
//        Thread s=new Thread()
//        {
//            @Override
//            public void run()
//            {
//                try 
//                {
//                    server.Send();
//                } 
//                catch (IOException e) 
//                {
//                    System.out.println(e);
//                }
//            }
//        };
//        Thread r=new Thread()
//        {
//          @Override
//          public void run()
//          {
//                try 
//                {
//                    server.Recieve();
//                } 
//                catch (IOException e) 
//                {
//                    System.out.println(e);
//                }
//          }
//        };
//        s.start();
//        r.start();
//    }

} 
