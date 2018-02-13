package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class User {
	ServerSocket server;
	private static int Id = 0;
	private Socket socket;
	private PrintWriter srvout;
	
	public User(Socket socket) {
		server = Server.getServer();
		User.Id += 1;
		this.socket = socket;
		System.out.println("UserConnected!");
		try {
			srvout = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void acceptMsg() throws IOException {
		String str = "@@@@@";
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		try {
			str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		srvout.println(str);
	}
	public void outMsg(String msg) {
		System.out.println(msg);
		srvout.println(msg);
	}
	public Socket getSocket() {
		return socket;
	}
	
	public User getUser() {
		return this;
	}

}
