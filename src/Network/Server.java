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
	static ServerSocket serverSocket;
	static List<Socket> socket;
	static List<User> users;
	static BufferedReader readerFromFirstUser;
	static BufferedReader readerFromSecondUser;
	static PrintWriter srvout;
	static boolean serverStatus;
	static Connect conn = new Connect();

	public Server() {
		serverStatus = true;
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

	public static void start() throws IOException {
		 Thread th = new Thread(new Server());
		 th.run();
	}

	@Override
	public void run() {
		users = new ArrayList<>();
		try {
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
			new Server(users.get(0).getSocket(), users.get(1).getSocket());
			String in, out;
			try {
				while (serverStatus) {
					in = null;
					in = users.get(0).acceptMsg(readerFromFirstUser);
					users.get(1).outMsg(in);
					in = null;
					in = users.get(1).acceptMsg(readerFromSecondUser);
					users.get(0).outMsg(in);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverSocket.close();
			serverSocket = null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static ServerSocket getServer() {
		return serverSocket;
	}
}
