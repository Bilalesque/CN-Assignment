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
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        ServerSocket ss= new ServerSocket(8000);
        Socket st=ss.accept();
        PrintStream ps=new PrintStream(st.getOutputStream());
        DataInputStream dis=new DataInputStream(System.in);
        DataInputStream dis1=new DataInputStream(st.getInputStream());
        while(true)
        {
            System.out.println("Enter MSG: ");
            String str=dis.readLine();
            ps.println(str);
            if(str.equals("abort"))
            {
                ss.close();
                break;
            }
            //------------------------------------ //send
            String str1=dis1.readLine();
            System.out.println("MSG Recieved: "+str1);
            if(str1.equals("abort"))
            {
                ss.close();
                break;
            }
            //------------------------------------ //recieve
        }
        
        
    }
    
}
