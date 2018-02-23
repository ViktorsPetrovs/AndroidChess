import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import Logging.Log;
import Main.*;
import graphic.Desk;

public class Chess implements Runnable {
	String [] fens = {"1r2nb1k/1ppr1ppp/p2p2b1/3NP1B1/P7/1P3B1P/2PR1PP1/4R2K",
			"2r2rk1/1b3p2/1q2pPp1/3pP2B/pp2nP2/1N5Q/PPP4P/1KR3R1",
			"5rk1/p4pbp/BpQ3p1/4p3/P7/2P1q1PP/1P2n1PB/3R3K",
			"6k1/pp2p2p/3b2p1/7q/3p4/1P1P3P/P1N2rP1/1Q3R1K",
			"1rb2rnk/6qp/p1p2p2/2Np1P1Q/4p1P1/1P6/P1P1BR1P/R6K",
			"3r2k1/1b3ppp/p4n2/1P1N4/2r5/8/1B3PPP/2RR2K1",
			"r1b3k1/pp1n3p/2pbp3/3pNpP1/2PBn2q/1P1NKP2/P1Q1P1B1/R5R1",
			"3rn1k1/4bppp/2qpp3/1NP1n3/3BP3/6P1/5P1P/1R1Q1BK1",
			"2r3k1/p4p2/6pp/4p3/2n1Q2P/2B3R1/P4PPK/3q4",
			"2r3k1/1p4p1/4p1qP/pP1pPp2/P1rNnP2/R1P4R/7P/4Q2K",
			"r4nk1/p3q1pp/1p1r1n2/2pbN3/2B2P2/P3P2Q/1B4PP/3R1RK1",
			"2R5/1p2q2p/p5p1/Pk1p1r2/3Q4/2P3P1/1P4P1/6K1",
			"4r1k1/1p3p2/7p/1p4p1/2np4/P4PPq/1PQ1P1NP/3R2K1",
			"6k1/7p/p5p1/2nPp3/1qP2p2/7P/QP2N1PK/8",
			"8/6p1/6kp/4Qp2/2B1p3/P3PqPP/2b2P1K/8",
			"1r3r2/pp2k1p1/2p1P3/2Pp1pq1/3Q4/P3P3/1P2KR2/7R",
			"3r2k1/p4ppp/2pB4/1bPn4/r3p3/1KP1P1PB/3R1P1P/2R5"};
	public String tmp;
	private String fen;
	private static Initialization playingBoard;
	private static Logger log = Logger.getLogger(Chess.class.getName());
	JFrame frame;
	public static void main(String[] args) throws IOException, InterruptedException {
//		new Log();
		Chess tj = new Chess();
		log.info(Chess.class.getName() + " started!");
		playingBoard = new Initialization();
		if (args.length > 0)
			tj.fen = args[0];
		if (tj.fen != null) {
			if (tj.fen.equals("-r")) {
				playingBoard.startingPositions();
				playingBoard.board.Move(6,4,4,4);
				playingBoard.board = playingBoard.board.parseToFen(tj.fens[new Random().nextInt(tj.fens.length)]);
			} else {
				playingBoard.startingPositions();
				playingBoard.board.Move(6,4,4,4);
				playingBoard.board = playingBoard.board.parseToFen(tj.fen);
			}
		} else {

			playingBoard.startingPositions();
		}
		playingBoard.board.drawBoard();
		tj.initializeFrame();
		Thread t = new Thread(tj);
		log.info(t.getName() + " created!");
		t.start();
		Network.Server server = new Network.Server();
		server.start();
	}
	
	private void initializeFrame() {
		frame = new Desk();
		((Desk) frame).initializeBoardInGraphic(playingBoard.board);
		((Desk) frame).transformToArray();
		((Desk) frame).drawBoardGraphic();
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		try {
			
			Network.Connect conn = new Network.Connect();
			Socket socket;
			
			socket = new Socket(conn.getHostname(), conn.getPort());
//			Socket socket1 = new Socket(conn.getHostname(), conn.getPort());
			log.info(socket.toString() + " connected to server!");
			BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter srvout = null;
			
			try {
				srvout = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				log.log(Level.WARNING, srvout.getClass().getName(), e);
			}
			String in = null;
			boolean turn = true;
			while (true) {
				
				if (turn) {
					try {
						new Thread().sleep(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
					if (((Desk)frame).tmp != null) {
						srvout.println(((Desk)frame).tmp);
						((Desk)frame).tmp = null;
						turn = false;
					}
				} else {
					try {
					new Thread().sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if ((in = r.readLine()) != null) {
					
					int[] n = Network.IO.decodeString(in);
					playingBoard.board.MoveClone(n[0],n[1],n[2],n[3]);
					in = null;
					turn = true;
					
					
				}
				}
			}
		} catch (IOException e1) {
			log.log(Level.WARNING, Chess.class.getName() + " in user method", e1);
		}
	}
}
