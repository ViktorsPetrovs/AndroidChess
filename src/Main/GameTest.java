package Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JFrame;

import Network.*;
import chesspieces.*;
import graphic.Desk;


public class GameTest {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
//		IO io = new IO();
//		History history = new History();
		IO io = new IO();
		//mainG g = new mainG();
		History history = new History();
		Logic test = new Logic();
//		TestMethods testM = new TestMethods();
		System.out.println("Initialization Started");
		Initialization playingBoard = new Initialization();
		playingBoard.startingPositions();
//		playingBoard.board.drawBoard();
//		String encoded = IO.encodeString(4, 4, 3, 2);
//		IO.decodeString(encoded);
		Board board2 = playingBoard.board.clone(playingBoard.board);
		System.out.println(board2.parseToFen(board2));
//		playingBoard.board.Move(0, 1, 2, 0);
//		//playingBoard.board.drawBoard();
//		playingBoard.board.Move(6, 4, 4, 4);

//		playingBoard.board.Move(0, 1, 4, 5);
		System.out.println(board2.parseToFen(playingBoard.board));
//		playingBoard.board.Move(6, 3, 4, 3);
//		playingBoard.board.Move(6, 2, 4, 2);
//		playingBoard.board.Move(6, 1, 4, 1);
		Board bd = new Board();
		bd.drawBoard();
		bd.board = board2.parseToFen("1r2nb1k/1ppr1ppp/p2p2b1/3NP1B1/P7/1P3B1P/2PR1PP1/4R2K w - - 1 0");
//		bd.board = board2.parseToFen(board2.parseToFen(playingBoard.board));
		bd.drawBoard();
		//Board rotated = new Board();
		
		playingBoard.board.drawBoard();
		//playingBoard.board.Move(6,4,4,4);
//		playingBoard.board.board=playingBoard.board.parseToFen("7r/p1pk2pp/1rpb1pn1/4p3/Q1B1P3/1PR2N2/PKP2qPP/3R4 w - - 0 1");
		playingBoard.board.drawBoard();
//		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame frame = new Desk();
		((Desk) frame).initializeBoardInGraphic(playingBoard.board);
		((Desk) frame).transformToArray();
		((Desk) frame).drawBoardGraphic(); 
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		playingBoard.board.drawBoard();
//		//TestMethods.singleGame(playingBoard.board);
		//System.out.println(history.getDetailedHistory(3).startY);
//		history.getHistory(1).drawBoard();
		//testM.drawBooleanArray(test.possibleBishopMoves(4, 4, true, playingBoard.board), playingBoard);

		
//		TestMethods.gameStart(playingBoard.board);
		//history.getHistory(1).drawBoard();
//		testM.drawBooleanArray(test.possibleKnightMoves(5, 2, true, playingBoard.board), playingBoard);
		//g.graphic();
//		 System.out.println("7r/p1pk2pp/1rpb1pn1/4p3/Q1B1P3/1PR2N2/PKP2qPP/3R4 w - - 0 1");
//		 
//		 board2.board = board2.parseToFen("7r/p1pk2pp/1rpb1pn1/4p3/Q1B1P3/1PR2N2/PKP2qPP/3R4 w - - 0 1");
	//	TestMethods.gameStart(playingBoard.board);
		 // board2.drawBoard();
		
	}
}

