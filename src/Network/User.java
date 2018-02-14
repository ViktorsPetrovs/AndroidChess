package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logging.Log;

public class User {
	private static Logger log = Logger.getLogger(User.class.getName());
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
			log.log(Level.WARNING, this.getClass().getName() + " User(Socket)", e);		
		}
		log.info(socket.toString() + " user added");
	}
	
	public String acceptMsg(BufferedReader reader) throws IOException {
		String str = null;
		try {
			while ((str = reader.readLine()) == null);
		} catch (IOException e) {
			log.log(Level.WARNING, this.getClass().getName() + " acceptMsg(BufferedReader)", e);
		}
		log.info("message accepted VALUE : " + str);
		return str;
	}
	public void outMsg(String msg) {
		srvout.println(msg);
		log.info("message sent VALUE : " + msg);
	}
	public Socket getSocket() {
		return socket;
	}
	
	public User getUser() {
		return this;
	}

}
