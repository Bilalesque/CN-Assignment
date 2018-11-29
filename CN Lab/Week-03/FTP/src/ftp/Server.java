/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ftp;
import java.io.*;
import java.net.*;
/**
 *
 * @author K152152
 */
public class Server {

    
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(8001);
        Socket s=ss.accept();
        PrintStream ps=new PrintStream(s.getOutputStream());
        DataInputStream in=new DataInputStream(System.in);
        System.out.println("Enter File Path: ");
        String fname=in.readLine();
        File file = new File(fname);
        if(file.exists())
        {
                DataInputStream in1=new DataInputStream(new FileInputStream(fname));
                String str1;
                while((str1=in1.readLine())!=null)
                {
                    ps.print(str1);
                    ps.flush();
                }
                in.close();
                s.close();
                ss.close();
        }
        else
        {
            System.out.println(fname+" Doesn't Exist");
            ps.println("File Doesn't Exist");
        }
    } 
}

