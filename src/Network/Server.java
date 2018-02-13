package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
	ServerSocket serverSocket;
	List<Socket> socket;
	List<User> users;
	BufferedReader readerFromFirstUser;
	BufferedReader readerFromSecondUser;
	PrintWriter srvout;
	boolean serverStatus;
	Connect conn = new Connect();

	public Server() {
	}

	public Server(Socket firstUser, Socket secondUser) {
		serverStatus = true;
		try {
			readerFromFirstUser = new BufferedReader(new InputStreamReader(firstUser.getInputStream()));
			readerFromSecondUser = new BufferedReader(new InputStreamReader(secondUser.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		users = new ArrayList<>();
		serverSocket = new ServerSocket(conn.getPort());
		int i = 0;
		while (i != 2) {
			Socket usr = null;
			if ((usr = serverSocket.accept()) != null) {
				System.out.println(usr.toString());
				User ue = new User(usr);
				users.add(ue);
				i++;
			}
		}
		Thread th = new Thread(new Server(users.get(0).getSocket(), users.get(1).getSocket()));
		th.run();

		while (serverStatus) {

		}
		serverSocket.close();
		serverSocket = null;
	}

	@Override
	public void run() {
		String in, out;
		try {
			while (serverStatus) {


				users.get(0).acceptMsg();
				in = readerFromFirstUser.readLine();
				if (in != null) 
					users.get(1).outMsg(in);
				
				users.get(1).acceptMsg();
				in = readerFromSecondUser.readLine();
				if (in != null) 
					users.get(0).outMsg(in);
				
				if (!in.equals("exit"))
					serverStatus = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
