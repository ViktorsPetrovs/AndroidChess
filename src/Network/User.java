package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
	private static Logger log = Logger.getLogger(User.class.getName());
	ServerSocket server;
	private Socket socket;
	private PrintWriter srvout;
	private boolean isWhite;
	
	public User(Socket socket) {
		server = Server.getServer();
		this.socket = socket;
		System.out.println("UserConnected!");
		try {
			srvout = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			log.log(Level.WARNING, this.getClass().getName() + " User(Socket)", e);		
		}
		log.info(socket.toString() + " user added");
	}
	
	public String acceptMsgFromUser(BufferedReader reader) throws IOException {
		String str = null;
		try {
			while ((str = reader.readLine()) == null);
		} catch (IOException e) {
			log.log(Level.WARNING, this.getClass().getName() + " acceptMsg(BufferedReader)", e);
		}
		log.info("message accepted VALUE : " + str);
		return str;
	}
	public void sendMsgToUser(String msg) {
		srvout.println(msg);
		log.info("message sent VALUE : " + msg);
	}
	public Socket getSocket() {
		return socket;
	}
	
	public void setIsWhite(boolean status) {
		isWhite = status;
	}
	public boolean isWhite() {
		return isWhite;
	}
	public User getUser() {
		return this;
	}

}
