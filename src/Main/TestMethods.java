package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Network.IO;
import chesspieces.*;
import graphic.Desk;

public class TestMethods {
	boolean[][] test = new boolean[8][8];

	public void drawBooleanArray(boolean[][] test) {
		// test = playingBoard.possiblePawnMoves(4, 4, true,
		// playingBoard.board);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (test[x][y] == true)
					System.out.print(" true ");
				if (test[x][y] == false)
					System.out.print(" false");
			}
			System.out.println();
		}
	}

	
	public static void singleGame(Board board ) throws IOException{
		String in=null;

		BufferedReader sysR = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
		
			if ((in = sysR.readLine()) != null){
				board.Move(IO.decodeString(in));
		}
	    }
	}
	

	public static void gameStart(Board board) throws UnknownHostException, IOException {

		Socket socket = new Socket("192.168.8.156", 8088);
		
		System.out.println("UserConnected!");
		PrintWriter srvout = null;
		try {
			srvout = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String in;
		String tmp="not null";
		BufferedReader r = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader sysR = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			tmp=null;
			in = null;
			if((tmp=sysR.readLine())!=null){
				srvout.println(tmp);
				System.out.println("Sending: "+tmp);
				board.Move(IO.decodeString(tmp));
				//Desk.drawBoardGraphic();
			}
			if ((in = r.readLine()) != null){
				board.Move(IO.decodeString(in));
				 IO.decodeString(in);
				System.out.println(in+" received");
				System.out.println("--------------------");
				//Desk.drawBoardGraphic();
				}
			System.out.println(tmp);
			System.out.println("--------------------");

		}
	}

	public static String coordinatesToLog(int xStart, int yStart, int xEnd, int yEnd) {
		
		return coordinatesToPosition(xStart,yStart)+ "--"+ coordinatesToPosition(xEnd,yEnd) ;
		
	}
	
	public static String coordinatesToPosition(int x, int y)
	{
		String start;
		switch(y){
		case 0: start = "A";
				break;

		case 1: start = "B";
				break;

		case 2: start = "C";
				break;

		case 3: start = "D";
				break;

		case 4: start = "E";
				break;

		case 5: start = "F";
				break;

		case 6: start = "G";
				break;

		case 7: start = "H";
				break;
		default: start = "error";
				break;
		}
		return start+(8-x);
	}

}
