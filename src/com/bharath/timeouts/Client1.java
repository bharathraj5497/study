package com.bharath.timeouts;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.*;

public class Client1
{
	public static void main(String []aa) throws IOException
	{
		int port = 3333;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in = "" , out = "";
		try
		{
			InetAddress addr = InetAddress.getByName("localhost");
			SocketAddress sockAddr = new InetSocketAddress(addr, port);
			Socket s = new Socket();
			s.connect(sockAddr);
			dis=new DataInputStream(s.getInputStream());
			dos=new DataOutputStream(s.getOutputStream());
			s.setSoTimeout(2000);
			if(s!=null)
				System.out.println("Connected to server on port 3333");
			//hread.sleep(3000);
			while(in!=null && !in.equals("stop"))
			{
				System.out.println("Request: ");
				out = br.readLine();
				dos.writeUTF(out);
				dos.flush();
				if(out.equals("stop"))
					break;
				in = dis.readUTF();
				System.out.println("Server response: "+in);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(dis!=null)
				dis.close();
			if(dos!=null)
				dos.close();
			if(br!=null)
				br.close();
		}
		
		
	}
}
