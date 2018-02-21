package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import Logging.Log;
import Main.*;
import graphic.Desk;

public class Test implements Runnable {
	private static Logger log = Logger.getLogger(Test.class.getName());
	public static void main(String[] args) throws IOException, InterruptedException {
//		new Log();
		log.info(Test.class.getName() + " started!");

		Thread t = new Thread(new Test());
		log.info(t.getName() + " created!");
		t.start();
		Server server = new Server();
		server.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			new Thread().currentThread().sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connect conn = new Connect();
		Socket socket;
		
		try {
			socket = new Socket(conn.getHostname(), conn.getPort());
			log.info(socket.toString() + " connected to server!");
			PrintWriter srvout = null;
			try {
				srvout = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				log.log(Level.WARNING, srvout.getClass().getName(), e);
			}
			String in;
			String tmp;
			Initialization playingBoard = new Initialization();
			playingBoard.startingPositions();
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader sysR = new BufferedReader(new InputStreamReader(System.in));
			JFrame frame = new Desk();
			((Desk) frame).initializeBoardInGraphic(playingBoard.board);
			((Desk) frame).transformToArray();
			((Desk) frame).drawBoardGraphic(); 
			frame.pack();
			frame.setResizable(false);
			frame.setVisible(true);

			((Desk) frame).drawBoardGraphic();
			while (true) {
				tmp = null;
				if ((tmp = sysR.readLine()) != null) {
//					playingBoard.board.Move(IO.decodeString(tmp));
					srvout.println(tmp);
						
				}
//				System.out.println(tmp);
//				System.out.println("--------------------");
				in = null;
//				if ((in = r.readLine()) != null) 
//					playingBoard.board.Move(IO.decodeString(in));	
//					 IO.decodeString(in);
//					 M
//				}
//					System.out.println(in);
//				System.out.println("--------------------");
			}
		} catch (IOException e1) {
			log.log(Level.WARNING, Test.class.getName() + " in user method", e1);
		}
	}
}
