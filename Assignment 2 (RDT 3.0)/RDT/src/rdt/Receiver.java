package rdt;

import java.io.*;
import java.net.*;

public class Receiver {
	
	public void receive() throws IOException, ClassNotFoundException
    {
	Packet s,r;
	int expected=0;
	DatagramSocket ds;
	DatagramPacket dp;
        long check;
	byte buff[]=new byte[1024];
	boolean i=true;
        ds=new DatagramSocket(8080);
        while(i)
        {
        	System.out.println("\nWaiting for packet!");
        	
            dp=new DatagramPacket(buff,buff.length);
            ds.receive(dp);
            
            
            ByteArrayInputStream bais = new ByteArrayInputStream(buff);
    		ObjectInputStream ois=new ObjectInputStream(bais);
    		
    		r=(Packet)ois.readObject();
                
                check=r.checksum(buff, buff.length);
                
                //if(check==0)
                //    System.out.println("\nNon-Corrupted Packet: "+r.seq);
    		
    		if(r.seq!=expected || check!=0)
    		{
                    if(r.seq!=expected)
    			System.out.println("\nUnexpected SEQ: "+r.seq);
                    if(check!=0)
                        System.out.println("\nCorrupted Packet SEQ: "+r.seq);
                        
    			System.out.println("Acknowledging last correct packet: "+(expected+1)%2);
    			
    			buff=new byte[1024];
    			s=new Packet();
    			s.Acknowledge((expected+1)%2);
    			

                        ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
        		ObjectOutputStream oos = new ObjectOutputStream(baos);
        		
        		oos.writeObject(s);
        		buff=baos.toByteArray();
                        
                        s.csum=s.checksum(buff, buff.length);
            
                        baos = new ByteArrayOutputStream(6400);
                        oos = new ObjectOutputStream(baos);

                        oos.writeObject(s);
                        buff=baos.toByteArray();
                
        		
        		dp=new DatagramPacket(buff,buff.length,dp.getAddress(),dp.getPort());
                ds.send(dp);
                
                continue;
                
                /*buff=new byte[1024];
                
                System.out.println("\nWaiting for packet!");
                
                dp=new DatagramPacket(buff,buff.length);
                ds.receive(dp);
                
                
                bais = new ByteArrayInputStream(buff);
        		ois=new ObjectInputStream(bais);
        		
        		r=(Packet)ois.readObject();*/
                
    		}
    		
            System.out.println("\nCorrect Packet Received: "+r.seq);
            System.out.println("Acknowledging Correct Packet: "+ expected);
            
            
            buff=new byte[1024];
            s=new Packet();
			s.Acknowledge(expected);
			
			expected=(expected+1)%2;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
    		ObjectOutputStream oos = new ObjectOutputStream(baos);
    		
    		oos.writeObject(s);
    		buff=baos.toByteArray();
                
                s.csum=s.checksum(buff, buff.length);
            
                        baos = new ByteArrayOutputStream(6400);
                        oos = new ObjectOutputStream(baos);

                        oos.writeObject(s);
                        buff=baos.toByteArray();
    		
    		dp=new DatagramPacket(buff,buff.length,dp.getAddress(),dp.getPort());
            ds.send(dp);
            
            
           
            buff=new byte[1024];
        }
    }
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		Receiver re=new Receiver();
                re.receive();
	}

}
