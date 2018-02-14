package Main;
import chesspieces.*; 

public class Logic {

	
	int peaceExists(int y, int x) {
		
		Board board = new Board();
		
		if (board.getBoard() == null ) 
			return(0);
		
		if (board.board[1][1].isWhite())
			return 2;
		
		return 1;
	}
	
	boolean [][] possibleMoves(int y, int x, Board board) {
		boolean [][] result = new boolean [8][8]; 
		boolean white = board.board[y][x].isWhite();
		
		if (board.board[y][x] instanceof Pawn)
			result = possiblePawnMoves(y,x,white,board);
		  
		if (board.board[y][x] instanceof Knight)
			result = possibleKnightMoves(y,x,white,board);
		
		
		
		return result;
	}
	
	boolean [][] possiblePawnMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
		
		if ((y > 6) || (y < 1)) {
			System.out.println("Wrong position for pawn!");
			return result;
		}
			
		if ((y == 6) 
			&& (board.board[y - 1][x] == null)
			&& (board.board[y - 2][x] == null))
				result[4][x] = true;
		
		if (board.board[y - 1][x] == null)
			result[y-1][x] = true;
		
		if ((x > 0) 
			&& (board.board[y - 1][x - 1] != null) 
			&& (board.board[y - 1][x - 1].isWhite() != white))
				result[y - 1][x - 1] = true;

		if ((x < 7) 
				&& (board.board[y - 1][x + 1] != null) 
				&& (board.board[y - 1][x + 1].isWhite() != white))
					result[y - 1][x + 1] = true;

		
		return result;
	}
	
	
	boolean [][] possibleKnightMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
		
		if ((y - 2 > -1) && (x - 1 > -1))
			result[y-2][x-1] = checkPieceAndWhile(y-2,x-1,white,board) ;

		if ((y - 2 > -1) && (x + 1 < 8))
			result[y-2][x+1] = checkPieceAndWhile(y-2,x+1,white,board) ;
		
		if ((y + 2 < 8) && (x + 1 < 8))
			result[y+2][x+1] = checkPieceAndWhile(y+2,x+1,white,board) ;
		
		if ((y + 2 < 8) && (x - 1 > -1))
			result[y+2][x-1] = checkPieceAndWhile(y+2,x-1,white,board) ;
		
		if ((y + 1 < 8) && (x -2 > -1))
			result[y+1][x-2] = checkPieceAndWhile(y+1,x-2,white,board) ;
		
		if ((y + 1  < 8) && (x +2 < 8))
			result[y+1][x+2] = checkPieceAndWhile(y+1,x+2,white,board) ;
		
		if ((y -1 > -1) && (x + 2 < 8))
			result[x-1][y+2] = checkPieceAndWhile(x-1,y+2,white,board) ;
		
		if ((y -1 > -1) && (x - 2 > -1))
			result[y-1][x-2] = checkPieceAndWhile(y-1,x-2,white,board) ;
		
		return result;
	}
	
	
	boolean [][] possibleBishopMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
	
		int y_start =99, x_start=99, y_step=99, x_step=99, x_marging=99, y_marging=99;
		for(int i = 1; i < 5; i++){
			if (i == 1) {
				y_start = y - 1; y_step = -1; y_marging = -1;
				x_start = x - 1; x_step = -1; x_marging = -1;
			}
			else if (i == 2) {
				y_start = y - 1; y_step = -1; y_marging = -1;
				x_start = x + 1; x_step = 1; x_marging = 8;
			}
			else if (i == 3) {
				y_start = y +1; y_step = 1; y_marging = 8;
				x_start = x + 1; x_step = 1; x_marging = 8;
			}
			else if (i == 4) {
				y_start = y +1; y_step = 1; y_marging = 8;
				x_start = x - 1; x_step = -1; x_marging = -1;
			}
			
			for (int j = y_start; j != y_marging; j = j + y_step) {
				
				if (x_start == x_marging)
					break;
				
				result[j][x_start] = checkPieceAndWhile(j,x_start,white,board) ;
				if (board.board[j][x_start] != null)
					break;
			x_start=x_start+x_step;
			}
		}
		
		return result;
	}
	
	
	boolean [][] possibleRooksMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
	
	
		for(int i = x -1; i > -1; i --) {
			result[y][i] = checkPieceAndWhile(y,i,white,board) ;
			if (board.board[y][i] != null)
				break;
		}
		
		for(int i = x + 1; i < 8; i ++) {
			result[y][i] = checkPieceAndWhile(y,i,white,board) ;
			if (board.board[y][i] != null)
				break;
		}

		for(int i = y + 1; i < 8; i ++) {
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			if (board.board[i][x] != null)
				break;
		}

		for(int i = y - 1; i > -1; i --) {
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			if (board.board[i][x] != null)
				break;
		}

		return result;
	}
	
	boolean checkPieceAndWhile(int y, int x, boolean white, Board board) {
		if ((y < 0) || (y > 7) || (x < 0) || (x>7))
			return false;
		if (board.board[y][x] == null)
			return(true);
		if ((board.board[y][x] != null) && (board.board[y][x].isWhite() != white))
			return true;
		
		return false;
	}
	

	
	
}
