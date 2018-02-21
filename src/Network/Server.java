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
	static List<User> spectators;

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
		spectators = new ArrayList<>();
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
			Thread spectatorsThread = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
					spectators.add(new User(serverSocket.accept()));
					} catch (Exception e) {
						log.log(Level.WARNING, this.getClass().getName() + " Spectators thread alert!");
					}
				}
			});
			spectatorsThread.start();
			String chooseColor = "";
			int whichUser = 0;
			while (true) {
				chooseColor = users.get(0).acceptMsgFromUser(readerFromFirstUser);
				if (chooseColor.equals("b")) {
					break;
				} else if (chooseColor.equals("w")){
					break;
				}
				System.out.println("loop" + chooseColor);
			}
			if (chooseColor.equals("w")) {
				users.get(0).setIsWhite(true);
				users.get(1).setIsWhite(false);
				whichUser = 0;
			} else {
				users.get(0).setIsWhite(false);
				users.get(1).setIsWhite(true);
				whichUser = 1;
				System.out.println("accepted BBBB");
			}
			String in;
			int count = 0;
			try {
				while (serverStatus) {
					System.out.println("looploopllopppopoo" + " " + whichUser);
					if (whichUser == 0) {
						in = null;
						in = users.get(whichUser).acceptMsgFromUser(readerFromFirstUser);
						users.get(++whichUser).sendMsgToUser(in);
					} else {
						in = null;
						in = users.get(whichUser).acceptMsgFromUser(readerFromSecondUser);
						users.get(--whichUser).sendMsgToUser(in);
					}
					count++;
					if (spectators.size() > 0) {
						for (User viewer : spectators) {
							if (in != null)
								viewer.sendMsgToUser(in);
						}
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverSocket.close();
			serverSocket = null;
		} catch (IOException e1) {
			log.log(Level.WARNING, Server.class.getName() + " " + " run method", e1);
		}
	}

	private void SpectatorAccept() {
		
	}
	public static ServerSocket getServer() {
		return serverSocket;
	}
}
