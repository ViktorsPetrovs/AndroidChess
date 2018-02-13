package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		ServerSocket server = new ServerSocket(8088);
//		while (true) {
//		Socket client = server.accept();
//		System.out.println("Connection to server accepted! " + client.getInetAddress());
//		}
		Server server = new Server();
		server.start();

	}

}
