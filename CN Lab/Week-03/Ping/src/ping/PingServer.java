/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ping;
import java.io.*;
import java.net.*;
/**
 *
 * @author K152152
 */
public class PingServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String line1,line2;
        int i;
        ServerSocket es;
        DataInputStream di;
        PrintStream ps;
        Socket csoc;
        es=null;
        csoc=null;
        try
        {
            es=new ServerSocket(9999);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        System.out.println("ping server");
        try
        {
            csoc=es.accept();
            di=new DataInputStream(csoc.getInputStream());
            ps=new PrintStream(csoc.getOutputStream());
            for(i=0;i<4;i++)
            {
                line1=di.readLine();
                System.out.println("pinged by client");
                ps.println(line1+"reply from host:bytes=3<time<1ms TT<=128");
            }
            di.close();
            ps.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }    
}
