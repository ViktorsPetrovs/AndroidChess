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
	
	public String acceptMsg(BufferedReader reader) throws IOException {
		String str = null;
		try {
			while ((str = reader.readLine()) == null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	public void outMsg(String msg) {
		srvout.println(msg);
	}
	public Socket getSocket() {
		return socket;
	}
	
	public User getUser() {
		return this;
	}

}
