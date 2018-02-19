package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spectator implements Runnable {
	private static Logger log = Logger.getLogger(Spectator.class.getName());
	ServerSocket server;
	private Socket socket;
	
	
	public void allowSpectate() {
		Thread th = new Thread(new Spectator());
		th.start();
	}
	public Spectator() {}
	public Spectator(Socket socket) {
		server = Server.getServer();
		this.socket = socket;
		System.out.println("SpectatorConnected!");
		log.info(socket.toString() + " spectator added");
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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
