package telran.socket.chatclient.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import telran.socket.chatclient.tasks.RecieverTask;
import telran.socket.chatclient.tasks.SenderTask;

public class ChatClientAppl {

	public static void main(String[] args) {
		String serverSocket = "127.0.0.1";
		int port = 9000;
		try (Socket socket = new Socket(serverSocket, port);) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter you name: ");
			String userName = br.readLine(); 
			Thread senderThread = new Thread(new SenderTask(socket, userName));
			Thread recieverThread = new Thread(new RecieverTask(socket));
			
			recieverThread.setDaemon(true);
			senderThread.start();
			recieverThread.start();
			senderThread.join();
			
			System.out.println("write your message or type exit:");
			} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
