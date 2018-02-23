package Tests;

import static org.junit.Assert.*;
import graphic.Desk;

import javax.swing.JFrame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import Main.Board;
import Main.History;
import Main.History.DetailedHistory;
import Main.Initialization;
import Main.Logic;

import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Knight;
import chesspieces.Pawn;
import chesspieces.Piece;
import chesspieces.Queen;
import chesspieces.Rooks;

public class PiecesUnitTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBishopCreate() {
		Bishop bishop = new Bishop(1,2,true);
		assertEquals(bishop.getY(),2);
		assertEquals(bishop.getX(),1);
		assertEquals(bishop.isWhite(),true);
	}
	
	@Test
	public void testPawnCreate() {
		Pawn pawn = new Pawn(1,2,true);
		assertEquals(pawn.getY(),2);
		assertEquals(pawn.getX(),1);
		assertEquals(pawn.isWhite(),true);
	}	

	@Test
	public void testKingCreate() {
		King king = new King(1,2,true);
		assertEquals(king.getY(),2);
		assertEquals(king.getX(),1);
		assertEquals(king.isWhite(),true);
	}
	
	@Test
	public void testKnightCreate() {
		Knight knight = new Knight(1,2,true);
		assertEquals(knight.getY(),2);
		assertEquals(knight.getX(),1);
		assertEquals(knight.isWhite(),true);
	}
	
	@Test
	public void testQueenCreate() {
		Queen queen = new Queen(1,2,true);
		assertEquals(queen.getY(),2);
		assertEquals(queen.getX(),1);
		assertEquals(queen.isWhite(),true);
	}

	@Test
	public void testRooksCreate() {
		Rooks rooks = new Rooks(1,2,true);
		assertEquals(rooks.getY(),2);
		assertEquals(rooks.getX(),1);
		assertEquals(rooks.isWhite(),true);
		rooks.setX(3);
		assertEquals(rooks.getX(),3);
		rooks.setY(6);
		assertEquals(rooks.getY(),6);
		rooks.setWhite(false);
		assertEquals(rooks.isWhite(),false);
		rooks.setAlive(false);
		assertEquals(rooks.isAlive(),false);
		assertEquals(rooks.toString(), rooks.getClass().getSimpleName());
	}
	
	
	@Test
	public void boardCreation() {
		Board board = new Board();
		assertEquals(board.board[0][0], null);
		assertEquals(board.board[7][7], null);
		Object[][] board1 = board.getBoard();
		assertNull(board1[0][0]);
		assertNull(board1[7][7]);
	}

	@Test
	public void boardCoordinatesMove() {
		Board board = new Board();
		Pawn pawn = new Pawn(2,3,true);
		board.setCoordinates(4, 5, pawn);
		assertTrue(board.board[4][5] instanceof Pawn);
		board.Move(4,5,4,6);
		assertNull(board.board[4][5]);
		assertTrue(board.board[4][6] instanceof Pawn);
		int [] tmpInt = new int[4];
		tmpInt[0] = 4; tmpInt[1] = 6; tmpInt[2] = 4; tmpInt[3] = 7;
		board.Move(tmpInt);
		Board boardTmp = board.clone(board);
		assertTrue(board.board[4][7] instanceof Pawn);
		boardTmp.MoveClone(4, 7, 3, 7);
		assertTrue(boardTmp.board[3][7] instanceof Pawn);
		board.clearCoordinates(4, 7);
		assertNull(board.board[4][7]);
	}	
	@Test
	public void testInitializationAndFen() {
		Initialization playingBoard = new Initialization();
		playingBoard.startingPositions();
		assertTrue(playingBoard.board.board[6][0] instanceof Pawn);
		assertTrue(playingBoard.board.board[6][0].isWhite());
		assertTrue(playingBoard.board.board[1][7] instanceof Pawn);
		assertTrue(!playingBoard.board.board[1][7].isWhite());
		History history = new History();
		String fen = playingBoard.board.parseToFen(playingBoard.board);
		assertEquals(fen, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
		playingBoard.board.Move(6,4,4,4);
		DetailedHistory detailedHistory = history.getDetailedHistory(1);
		Logic logic = new Logic();
		assertTrue(logic.getTypeOfPieceFromString(detailedHistory.getType()) instanceof Pawn);
		assertTrue(detailedHistory.isWhite());
		fen = playingBoard.board.parseToFen(playingBoard.board);
		assertEquals(fen, "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR");
		Board boardTmp = playingBoard.board.parseToFen("rnbqkbnr/pppppppp/8/8/3P4/8/PPPP1PPP/RNBQKBNR");
		assertTrue(boardTmp.board[4][3] instanceof Pawn);
	}

	@Test
	public void testBoardGraphic() {
		
		Initialization playingBoard = new Initialization();
		JFrame frame = new Desk();
		playingBoard.startingPositions();
		((Desk) frame).initializeBoardInGraphic(playingBoard.board); 
		((Desk) frame).transformToArray();
		((Desk) frame).drawBoardGraphic(); 
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
	}	
}
