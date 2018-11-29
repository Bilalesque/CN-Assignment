package rdt;

import java.io.*;
import java.net.*;

public class Sender {
	Packet s,r;
	DatagramSocket ds;
    DatagramPacket dp;
    byte buff[]=new byte[1024];
    int expected=0;
    long check;
    boolean i=true;
    public void send() throws IOException, ClassNotFoundException
    {
        while(i)
        {
           	System.out.println("\nSending packet with SEQ: " + expected);
        	
            ds=new DatagramSocket();
            s=new Packet(expected);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
    		ObjectOutputStream oos = new ObjectOutputStream(baos);
    		
    		oos.writeObject(s);
    		buff=baos.toByteArray();
            
            s.csum=s.checksum(buff, buff.length);
            
            baos = new ByteArrayOutputStream(6400);
            oos = new ObjectOutputStream(baos);
            
            oos.writeObject(s);
            buff=baos.toByteArray();
                
            dp=new DatagramPacket(buff,buff.length,InetAddress.getLocalHost(),8080);
            ds.send(dp);
            
            
            buff=new byte[1024];
            
            System.out.println("\nWaiting for ACK!");
            
            dp=new DatagramPacket(buff,buff.length);
            ds.setSoTimeout(1000);
            try{
                ds.receive(dp);
            }
            catch(SocketTimeoutException ex){
                System.out.println("\nTimeout occured!");
                System.out.println("Resending SEQ: "+expected);
                continue;
            }
            
            ByteArrayInputStream bais = new ByteArrayInputStream(buff);
    		ObjectInputStream ois=new ObjectInputStream(bais);
    		
    		r=(Packet)ois.readObject();
                
                check=r.checksum(buff, buff.length);
    		
    		if(r.ack!=expected || check!=0)
    		{
                    if(r.ack!=expected)
                        System.out.println("\nReceived incorrect ACK: "+r.ack);
                    if(check!=0)
                        System.out.println("\nReceived corrupt ACK");
    		System.out.println("Resending Packet with SEQ: "+expected);
                
                continue;
    			
    		/*s=new Packet(expected);
                
                baos = new ByteArrayOutputStream(6400);
       		oos = new ObjectOutputStream(baos);
     		
      		oos.writeObject(s);
       		buff=baos.toByteArray();
        		
       		dp=new DatagramPacket(buff,buff.length,InetAddress.getLocalHost(),8080);
                ds.send(dp);
                
                buff=new byte[1024];
                
                dp=new DatagramPacket(buff,buff.length);
                ds.setSoTimeout(1000);
                try{
                    ds.receive(dp);
                }
                catch(SocketTimeoutException ex){
                    System.out.println("\nTimeout occured!");
                    System.out.println("Resending SEQ: "+expected);
                    continue;
                }
                
                bais = new ByteArrayInputStream(buff);
        		ois=new ObjectInputStream(bais);
        		
        		r=(Packet)ois.readObject();*/
    		}  		
    		
            System.out.println("Received correct ACK: "+expected);
            
            expected=(expected+1)%2;
    		
            buff=new byte[1024];
        }
        
        
	
	}
    
    

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Sender se=new Sender();
		se.send();
		/*byte[] data=new byte[1024];
		Packet p=new Packet(0);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(6400);
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		//oos.flush();
		
		oos.writeObject(p);
		data = baos.toByteArray();
		
		p.csum=p.calculateChecksum(data);
		System.out.println(p.csum);
                
                baos = new ByteArrayOutputStream(6400);
		oos = new ObjectOutputStream(baos);
		
		oos.writeObject(p);
		data = baos.toByteArray();
                
                //p.csum=p.checksum(data, data.length);
                
                //System.out.println(p.csum);
		System.out.println(p.checksum(data, data.length));
                //data.
                
                
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		ObjectInputStream ois=new ObjectInputStream(bais);
		
		Packet p1=(Packet)ois.readObject();
              System.out.println(p1.csum);
		System.out.println(p1.checksum(data, data.length));*/
	}

}
