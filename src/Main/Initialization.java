package Main;

import chesspieces.*;

public class Initialization {

	
	Board board = new Board();
	
	
	public void setUp(){

			
	}
	
	public  void startingPositions(){
		
		System.out.println("Putting items in places");
		//White setUP
		
		for(int i = 0; i<8; i++)
		{
		board.setCoordinates(6, i, new Pawn(6,i,true));
		}
		board.setCoordinates(7, 0, new Rooks(7,0,true));
		board.setCoordinates(7, 1, new Knight(7,1,true));
		board.setCoordinates(7, 2, new Bishop(7,2,true));
		board.setCoordinates(7, 3, new Queen(7,3,true));
		board.setCoordinates(7, 4, new King(7,4,true));
		board.setCoordinates(7, 5, new Bishop(7,5,true));
		board.setCoordinates(7, 6, new Knight(7,6,true));
		board.setCoordinates(7, 7, new Rooks(7,7,true));
		
		
		
		
		// Black setUP
		for(int i = 0; i<8; i++)
		{
		board.setCoordinates(1, i, new Pawn(1,i,false));
		}
		board.setCoordinates(0, 0, new Rooks(0,0,true));
		board.setCoordinates(0, 1, new Knight(0,1,true));
		board.setCoordinates(0, 2, new Bishop(0,2,true));
		board.setCoordinates(0, 3, new Queen(0,3,true));
		board.setCoordinates(0, 4, new King(0,4,true));
		board.setCoordinates(0, 5, new Bishop(0,5,true));
		board.setCoordinates(0, 6, new Knight(0,6,true));
		board.setCoordinates(0, 7, new Rooks(0,7,true));
		
		
	}
	
	
	
	
}
