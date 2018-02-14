package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server implements Runnable {
	private static Logger log = Logger.getLogger(Server.class.getName());
	static ServerSocket serverSocket;
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
			log.log(Level.WARNING, Server.class.getName() + " " + " Server constructor", e);
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
			log.info(serverSocket.toString() + " server created");
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
			String in;
			int whichUser = 0;
			int count = 0;
			try {
				while (serverStatus) {
					if (whichUser == 0) {
						in = null;
						in = users.get(whichUser).acceptMsg(readerFromFirstUser);
						users.get(++whichUser).outMsg(in);
					} else {
						in = null;
						in = users.get(whichUser).acceptMsg(readerFromSecondUser);
						users.get(--whichUser).outMsg(in);
					}
					count++;
					System.out.println(count);

				}
				System.out.println(count);
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverSocket.close();
			serverSocket = null;
		} catch (IOException e1) {
			log.log(Level.WARNING, Server.class.getName() + " " + " run method", e1);
		}
	}

	public static ServerSocket getServer() {
		return serverSocket;
	}
}
