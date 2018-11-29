/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg2waytcp;
import java.io.*;
import java.net.*;
/**
 *
 * @author K152152
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket sr=new Socket("localHost",8000);
        PrintStream ps=new PrintStream(sr.getOutputStream());
        DataInputStream dis=new DataInputStream(sr.getInputStream());
        DataInputStream dis1=new DataInputStream(System.in);
        while(true)
        {
            String str1=dis.readLine();
            System.out.println("MSG Recieved: "+str1);
            if(str1.equals("abort"))
            {
                sr.close();
                break;
            }
            //------------------------------------ //recieve
            System.out.println("Enter MSG: ");
            String str=dis1.readLine();
            ps.println(str);
            if(str.equals("abort"))
            {
                sr.close();
                break;
            }
            //------------------------------------ //send
        }
    }
    
}
