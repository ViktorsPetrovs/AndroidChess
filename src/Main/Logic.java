package Main;
import chesspieces.*; 

public class Logic {

	
	int peaceExists(int y, int x) {

		/* fill boadr - tmp 
		Board board = new Board();
		boolean tmp;
		for (int i = 0; i<8; i++){
			tmp = (i>3);
			for (int j = 0; j<7; j++){
				board.board[i][j] = new Pawn(i,j,true);
			}
		}
		 end fill board tmp */
		
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
		
		if (board.board[y][x] instanceof Bishop)
			result = possibleBishopMoves(y,x,white,board);
		
		if (board.board[y][x] instanceof Rooks)
			result = possibleRooksMoves(y,x,white,board);

		if (board.board[y][x] instanceof Queen)
			result = possibleQueenMoves(y,x,white,board);

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
				
				x_start = x_start + x_step;
			}
		}
		return result;
	}
	
	
	boolean [][] possibleRooksMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		//Board boardTmp = board.clone(board);
		
		
		for(int i = x -1; i > -1; i --) {
			result[y][i] = checkPieceAndWhile(y,i,white,board); 
			
//			System.out.println("1:  "+y+ "  "+ x+ "  " +y + "  "+ i);
			if (result[y][i]) { 
				boardTmp = board.clone(board);
				boardTmp.Move(y, x, y, i);
				
				System.out.println("=====================================");
				board.drawBoard();
				boardTmp.drawBoard();
				System.out.println("=====================================");
			
				result[y][i] = result[y][i] && !isCheck(white,boardTmp);
				boardTmp.Move(y, i, y, x);
			}
			if (board.board[y][i] != null)
				break;
		}
		
		
		for(int i = x + 1; i < 8; i ++) {
			result[y][i] = checkPieceAndWhile(y,i,white,board) ;
			
//			System.out.println("2:  "+y+ "  "+ x+ "  " +y + "  "+ i);
			boardTmp = board.clone(board);
			
			boardTmp.Move(y, x, y, i);
			
	//		result[y][i] = result[y][i] && !isCheck(white,boardTmp);
			boardTmp.Move(y, i, y, x);
			
			if (board.board[y][i] != null)
				break;
		}

//		board.drawBoard();
		for(int i = y + 1; i < 8; i ++) {
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			
//			System.out.println("3:  "+y+ "  "+ x+ "  " +i + "  "+ x);

			if (result[i][x]) {
				boardTmp = board.clone(board);
				boardTmp.Move(y, x, i, x);
//				result[y][i] = result[y][i] && !isCheck(white,boardTmp);
				boardTmp.Move(i, x, y, x);
			}

			if (board.board[i][x] != null)
				break;
		}

		for(int i = y - 1; i > -1; i --) {
			
//			board.drawBoard();
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			
//			System.out.println("4:  "+y+ "  "+ x+ "  " +i + "  "+ x);

//			board.drawBoard();
			
//			if (result[i][x]) {
//				boardTmp = board.clone(board);
//				boardTmp.Move(y, x, i, x);
//				result[i][x] = result[i][x] && !isCheck(white,boardTmp);
//				boardTmp.Move(i, x, y, x);
//			}
			
			if (board.board[i][x] != null)
				break;
		}

		return result;
	}

	boolean [][] possibleQueenMoves(int y,int x, boolean white, Board board) {
		boolean [][] result = new boolean [8][8];
		
		boolean [][][] results = new boolean [2][8][8]; 
		results[0] =  possibleRooksMoves(y,x, white, board);
		results[1] =  possibleBishopMoves(y,x, white, board);

		result = joinResults(results);
		
		return result;
	}
	
	boolean checkPieceAndWhile(int y, int x, boolean white, Board board) {
		if ((y < 0) || (y > 7) || (x < 0) || (x>7))
			return false;
		if (board.board[y][x] == null) {
					
			return(true);
		}
		if ((board.board[y][x] != null) && (board.board[y][x].isWhite() != white))
			return true;
		
		return false;
	}
	
	
	boolean [][] joinResults (boolean results[][][]) {
		boolean [][] result = new boolean [8][8];
		
//		System.out.println(results.length);
		
		for (int i = 0; i < results.length; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
//					System.out.println(i + " , " + j + " , " + k);
					result[j][k] = result[j][k] || results[i][j][k];
				}
			}
		}
		return result;
	}		
	
	// returns true if there is chsck to while (parameter)
	boolean isCheck(boolean white, Board board) {
		
		boolean [][] result = new boolean [8][8];
		boolean [][][] results = new boolean [15][8][8]; 
		
		board.drawBoard();
		
		int yKing = 99 ,xKing = 99, index =0;
		
		System.out.println(white + "   Start!!! " +  board.board[2][4].isWhite());
		
		for(int i=0; i < 8; i++) {
			for(int j=0; j < 8; j++ ) {
				
			
//				for(int i=6; i < 8; i++) {
//					for(int j=0; j < 8; j++ ) {

//				System.out.println(i + "  " + j + board.board[i][j].toString() + board.board[i][j].isWhite() +"-"+white);
//				System.out.println(i + "  " + j + "-"+white);

						
				if (board.board[i][j] == null)
					continue;
				if ((board.board[i][j].isWhite() == white)
						&& (board.board[i][j] instanceof King)) {
					yKing = i; xKing = j;
				}
				else if (board.board[i][j].isWhite() != white) {
					
					if (board.board[i][j] instanceof Pawn) 
						result = possiblePawnMoves(i,j,board.board[i][j].isWhite(),board);
					  
					if (board.board[i][j] instanceof Knight)
						result = possibleKnightMoves(i,j,board.board[i][j].isWhite(),board);
					
					if (board.board[i][j] instanceof Bishop)
						result = possibleBishopMoves(i,j,board.board[i][j].isWhite(),board);
					
					if (board.board[i][j] instanceof Rooks)
						result = possibleRooksMoves(i,j,board.board[i][j].isWhite(),board);

					if (board.board[i][j] instanceof Queen)
						result = possibleQueenMoves(i,j,board.board[i][j].isWhite(),board);
					
					results[index] = result;
					
					
					index ++;
					
				}
				
				if((i==2) && (j==4)) {
					System.out.println(i + "  " + j + board.board[i][j].toString() + board.board[i][j].isWhite() +"-"+white);
					System.out.println("rrrrrrrrrrrrrr  " + result[5][4]);
				}	

				
//				System.out.println(i + "  " + j + board.board[i][j].toString());
//				TestMethods testM = new TestMethods();
//				testM.drawBooleanArray(result,null);
				
				
			}
		}
		result = joinResults(results);
	//	System.out.println("check  " + white + "  " +  yKing + "," +xKing);
	//	TestMethods testM = new TestMethods();
	//	testM.drawBooleanArray(result,null);
		
		return !result[yKing][xKing];
//		return false;
	}
	
	
	
}
