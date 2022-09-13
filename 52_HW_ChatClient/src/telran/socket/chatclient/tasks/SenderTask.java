package telran.socket.chatclient.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SenderTask implements Runnable {
	Socket socket;
	String userName;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	public SenderTask(Socket socket, String userName) {
		this.socket = socket;
		this.userName = userName;
	}
	
	

	@Override
	public void run() {
	try(Socket socket = this.socket){
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message = (userName + " :" +" at:"+ LocalDateTime.now().format(formatter)+"\n" + (br.readLine()));
		while(!"exit".equalsIgnoreCase(message)) {
			oos.writeObject(message);
			message = (userName + " :" +" at:"+ LocalDateTime.now().format(formatter)+"\n" + (br.readLine()));
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

}
