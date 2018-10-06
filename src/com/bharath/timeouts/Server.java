package com.bharath.timeouts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server
{
	public static void main(String []a) throws IOException
	{
		ServerSocket ss = new ServerSocket(3333);
		
		int clientId=0;
		while(true)
		{
			System.out.println("Server listening on port 3333");
			Socket s = null;
			DataInputStream dis = null;
			DataOutputStream dos = null;
			try
			{
				s=ss.accept();
				clientId++;
				if(s!=null)
					System.out.println("Client "+clientId+" Connected ");
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				Thread handler = new ClientHandler(s, dis, dos, clientId);
				handler.start();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
		}
	}
}
