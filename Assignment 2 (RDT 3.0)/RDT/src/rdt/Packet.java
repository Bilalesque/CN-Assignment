package rdt;

import java.io.*;

public class Packet implements java.io.Serializable{
	public String data="Some data";
	public int ack;
	public int seq;
	public long csum=0;
	
	public Packet() {}
	
	public Packet(int n) {
		seq=n;
	}
	
	public void Acknowledge(int n)
	{
		ack=n;
	}
	
	public void pack(String str)
	{
		this.data=str;
	}
	
	public String unpack()
	{
		return this.data;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException  
	{    out.defaultWriteObject();  }
	  private void readObject(ObjectInputStream in)
	               throws IOException, ClassNotFoundException  
	{    in.defaultReadObject();  }
	  
	 /* public long calculateChecksum(byte[] buf) {
		    int length = buf.length;
		    int i = 0;

		    long sum = 0;
		    long data;

		    // Handle all pairs
		    while (length > 1) {
		      // Corrected to include @Andy's edits and various comments on Stack Overflow
		      data = (((buf[i] << 8) & 0xFF00) | ((buf[i + 1]) & 0xFF));
		      sum += data;
		      // 1's complement carry bit correction in 16-bits (detecting sign extension)
		      if ((sum & 0xFFFF0000) > 0) {
		        sum = sum & 0xFFFF;
		        sum += 1;
		      }

		      i += 2;
		      length -= 2;
		    }

		    // Handle remaining byte in odd length buffers
		    if (length > 0) {
		      // Corrected to include @Andy's edits and various comments on Stack Overflow
		      sum += (buf[i] << 8 & 0xFF00);
		      // 1's complement carry bit correction in 16-bits (detecting sign extension)
		      if ((sum & 0xFFFF0000) > 0) {
		        sum = sum & 0xFFFF;
		        sum += 1;
		      }
		    }

		    // Final 1's complement value correction to 16-bits
		    sum = ~sum;
		    sum = sum & 0xFFFF;
		    return sum;

		  }*/
	  
	  long checksum(byte[] buf, int length) {
		    int i = 0;
		    long sum = 0;
		    while (length > 0) {
		        sum += (buf[i++]&0xff) << 8;
		        if ((--length)==0) break;
		        sum += (buf[i++]&0xff);
		        --length;
		    }

		    return (~((sum & 0xFFFF)+(sum >> 16)))&0xFFFF;
		}

}
