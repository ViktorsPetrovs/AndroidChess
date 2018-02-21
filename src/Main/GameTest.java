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
		IO io = new IO();
		//mainG g = new mainG();
		History history = new History();
		Logic test = new Logic();
		TestMethods testM = new TestMethods();
		System.out.println("Initialization Started");
		Initialization playingBoard = new Initialization();
		playingBoard.startingPositions();
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

