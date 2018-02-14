package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import Network.*;
import chesspieces.*;


public class GameTest {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		IO io = new IO();
		History history = new History();
		Logic test = new Logic();
		TestMethods testM = new TestMethods();
		System.out.println("Initialization Started");
		Initialization playingBoard = new Initialization();
		playingBoard.startingPositions();

		playingBoard.board.drawBoard();
//		String encoded = IO.encodeString(4, 4, 3, 2);
//		IO.decodeString(encoded);
		Board board2 = playingBoard.board.clone(playingBoard.board);
		playingBoard.board.Move(0, 1, 2, 0);
		//playingBoard.board.drawBoard();
		playingBoard.board.Move(6, 4, 4, 4);
		playingBoard.board.Move(6, 3, 4, 3);
		playingBoard.board.Move(6, 2, 4, 2);
		playingBoard.board.Move(6, 1, 4, 1);
		playingBoard.board.drawBoard();
		
		//System.out.println(history.getDetailedHistory(3).startY);
		history.getHistory(1).drawBoard();
		//testM.drawBooleanArray(test.possibleBishopMoves(4, 4, true, playingBoard.board), playingBoard);

		
		TestMethods.gameStart(playingBoard.board);
	}
}

