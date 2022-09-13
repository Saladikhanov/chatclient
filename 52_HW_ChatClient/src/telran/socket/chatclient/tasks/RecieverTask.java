package telran.socket.chatclient.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RecieverTask implements Runnable {
Socket socket;

	public RecieverTask(Socket socket) {
	this.socket = socket;
}

	@Override
	public void run() {
		try(Socket socket = this.socket){
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			while(true) {
				String responce = ois.readObject().toString();
				System.out.println(responce);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
