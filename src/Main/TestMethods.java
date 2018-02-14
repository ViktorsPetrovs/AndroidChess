package Main;

import chesspieces.*;

public class TestMethods {
	boolean[][] test = new boolean[8][8];

	public void drawBooleanArray(boolean [][] test, Initialization playingBoard) {
		//test = playingBoard.possiblePawnMoves(4, 4, true, playingBoard.board);
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
	
	public void parseString(String s){
		Board parsed = new Board();
		int i = 0;
		if (s.length()!=64){System.out.println("parsed string not 64 ");}
			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {//TODO
//		   switch (s.charAt(i))
//		   {
//		   case "W": parsed.board[x][y]=new Pawn(x,y,true);
//		   			break;
//		   
//		   }
		   i++;
				}
			}
		}
		
	}

