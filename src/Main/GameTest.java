package Main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import Network.*;
import chesspieces.*;

public class GameTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Logic test = new Logic();
		TestMethods testM = new TestMethods();
		System.out.println("Initialization Started");
		Initialization playingBoard = new Initialization();
		playingBoard.startingPositions();
		String encoded = IO.encodeString(4, 4, 3, 2);
		IO.decodeString(encoded);
		Board board2 = playingBoard.board.clone(playingBoard.board);

		playingBoard.board.drawBoard();
		playingBoard.board.Move(1, 4, 1, 4);
		playingBoard.board.Move(6, 0, 2, 2);
		playingBoard.board.drawBoard();
//		 Socket socket = new Socket("192.168.8.156", 8088);
//		 Socket s = new Socket();
//		 System.out.println("UserConnected!");
//		
		testM.drawBooleanArray(test.possibleBishopMoves(4, 4, true, playingBoard.board), playingBoard);
		

	}
}
