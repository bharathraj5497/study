package com.bharath.timeouts;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread 
{
	
	Socket s = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	int clientId = 0;
	public ClientHandler()
	{
		
	}
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, int clientId)
	{
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		this.clientId = clientId;
	}
	
	@Override
	public void run() 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try 
		{
			
			Thread.sleep(3000);
			String in = "", out = "";
			while(in!=null && !in.equals("stop"))
			{
 				in = dis.readUTF();
 				System.out.println("Client "+clientId+" Request "+in);
 				if(in.equals("stop"))
 				{
 					System.out.println("client "+clientId+" disconnected successfully...");
 					break;
 				}
				System.out.println("Client "+clientId+": Says: "+in);
				System.out.println("Enter response for Client "+clientId+": ");
				out = br.readLine();
				dos.writeUTF(out);
				if(out.equals("stop"))
 				{
 					System.out.println("client "+clientId+" disconnected successfully...");
 					break;
 				}
				dos.flush();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dis.close();
				dos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
}
