package Main;
import Main.History.DetailedHistory;
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
	
	public boolean [][] possibleMoves(int y, int x, Board board) {
		boolean [][] result = new boolean [8][8]; 
		
		if (board == null) {
			System.out.println("The bord is null!");
			return result;
		}
		
		if (board.board[y][x] == null) { 
			System.out.println("The piece on " + y +"," + x + "is null!");
			return result;
		}
			
		boolean white = board.board[y][x].isWhite();
		
		if (board.board[y][x] instanceof King)
			result = possibleKingMoves(y,x,white,board,true);
		
		if (board.board[y][x] instanceof Pawn) {
			if (playerColor() == board.board[y][x].isWhite()) {
				result = possiblePawnMoves(y,x,white,board,true);
			}
			else
				result = possiblePawnMovesOther(y,x,white,board,true);
		
		}
		if (board.board[y][x] instanceof Knight)
			result = possibleKnightMoves(y,x,white,board,true);
		
		if (board.board[y][x] instanceof Bishop)
			result = possibleBishopMoves(y,x,white,board,true);
		
		if (board.board[y][x] instanceof Rooks)
			result = possibleRooksMoves(y,x,white,board,true);

		if (board.board[y][x] instanceof Queen)
			result = possibleQueenMoves(y,x,white,board,true);

		return result;
	}
	
	boolean [][] possibleKingMoves(int y,int x, boolean white, Board board,boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		
		
		System.out.println("Start !!!!!!From check king -  " + y +","+x);
	
		if (y+1 < 8)
			result[y+1][x] = checkPieceAndWhile(y+1,x,white,board);
		if (y-1 > -1)
			result[y-1][x] = checkPieceAndWhile(y-1,x,white,board);
		if (x+1 < 8)
			result[y][x+1] = checkPieceAndWhile(y,x+1,white,board);
		if (x-1 > -1)
			result[y][x-1] = checkPieceAndWhile(y,x-1,white,board);
		if ((y + 1 < 8) && (x + 1 < 8))
			result[y+1][x+1] = checkPieceAndWhile(y+1,x+1,white,board);
		if ((y + 1 < 8) && (x - 1 > -1))
			result[y+1][x-1] = checkPieceAndWhile(y+1,x-1,white,board);
		if ((y-1 > -1) && (x+1 < 8))
			result[y-1][x+1] = checkPieceAndWhile(y-1,x+1,white,board);
		if (y-1 > -1 && (x-1 > -1))
			result[y-1][x-1] = checkPieceAndWhile(y-1,x-1,white,board);
		
		for (int i = 0; i<8; i++){
			for (int j = 0; j < 8; j++) {
				if (result[i][j]) {
					boardTmp = board.clone(board);
					boardTmp.MoveClone(y, x, i, j);
					result[i][j] = !isCheck(white,boardTmp);
				}
			}
		}
		
		
		// check Castling

		if (checkCastling(board,white,0,x)) { 
			result[7][x - 2] = true;
				
		}
		if (checkCastling(board,white,7,x)) {
			result[7][x + 2] = true;
		}
		return result;
	}
	
	
	
	boolean [][] possiblePawnMoves(int y,int x, boolean white, Board board, boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		
		
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
		
		if (toCheckCheh) {
			for(int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (result[i][j]) {
						boardTmp = board.clone(board);
						boardTmp.MoveClone(y, x, i, j);
						result[i][j] = !isCheck(white,boardTmp);
					}
				}
			}
		}
		 if ((y == 3) && toCheckCheh && (board.countOfMoves > 0)){
			 History history = new History();
			 
			 DetailedHistory detailedHistory = history.getDetailedHistory(board.countOfMoves - 1);
			 
			 if (getTypeOfPieceFromString(detailedHistory.getType()) instanceof Pawn) {
				 
				 if ((detailedHistory.startY == 1) && (detailedHistory.endY == 3)) {
					 if (detailedHistory.startX == x - 1) {
							boardTmp = board.clone(board);
							boardTmp.MoveClone(3, x, 3, x -1);
							boardTmp.MoveClone(3, x-1, 2, x -1);
							result[2][x-1] = !isCheck(white,boardTmp);
					 }
					 if (detailedHistory.startX == x + 1) {
							boardTmp = board.clone(board);
							boardTmp.MoveClone(3, x, 3, x + 1);
							boardTmp.MoveClone(3, x + 1, 2, x + 1);
							result[2][x+1] = !isCheck(white,boardTmp);
					 }
				 }
			 }
			 
		 }
		return result;
	}
	

	boolean [][] possiblePawnMovesOther(int y,int x, boolean white, Board board, boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		
		
		if ((y < 1) || (y > 6)) {
			System.out.println("Wrong position for pawn!");
			return result;
		}
			
		if ((y == 1) 
			&& (board.board[y + 1][x] == null)
			&& (board.board[y + 2][x] == null))
				result[3][x] = true;
		
		if (board.board[y + 1][x] == null)
			result[y+1][x] = true;
		
		if ((x > 0) 
			&& (board.board[y + 1][x - 1] != null) 
			&& (board.board[y + 1][x - 1].isWhite() != white)) {
				result[y + 1][x - 1] = true;
				
		}
		if ((x < 7) 
				&& (board.board[y + 1][x + 1] != null) 
				&& (board.board[y + 1][x + 1].isWhite() != white))
					result[y + 1][x + 1] = true;
		 
		
		if (toCheckCheh) {
			for(int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (result[i][j]) {
						boardTmp = board.clone(board);
						
						boardTmp.MoveClone(y, x, i, j);
						result[i][j] = !isCheck(white,boardTmp);
					}
				}
			}
		}

		 // To check en passant. If function are called
		// checkCheh - no need.
		 if ((y == 4) && toCheckCheh && (board.countOfMoves > 0)){
			 History history = new History();
			 
			 DetailedHistory detailedHistory = history.getDetailedHistory(board.countOfMoves-1);
			 
			 if (getTypeOfPieceFromString(detailedHistory.getType()) instanceof Pawn) {
				 
				 System.out.println( " history:   "+detailedHistory.isWhite 
			 		+ "  " + detailedHistory.startY 
			 		+ " ," + detailedHistory.startX
			 		+ "  " + detailedHistory.endY 
			 		+ " ," + detailedHistory.endX
			 
			 );				 

				 
				 if ((detailedHistory.startY == 1) && (detailedHistory.endY == 3)) {
					 if (detailedHistory.startX == x - 1) {
							boardTmp = board.clone(board);
							boardTmp.MoveClone(3, x, 3, x -1);
							boardTmp.MoveClone(3, x-1, 2, x -1);
							result[2][x-1] = !isCheck(white,boardTmp);
					 }
					 if (detailedHistory.startX == x + 1) {
							boardTmp = board.clone(board);
							boardTmp.MoveClone(3, x, 3, x + 1);
							boardTmp.MoveClone(3, x + 1, 2, x + 1);
							result[2][x+1] = !isCheck(white,boardTmp);
					 }
				 }
			 }
			 
		 }
		return result;
	}

	
	
	boolean [][] possibleKnightMoves(int y,int x, boolean white, Board board, boolean toCheckCheh) {
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
			result[y-1][x+2] = checkPieceAndWhile(y-1,x+2,white,board) ;
		
		if ((y -1 > -1) && (x - 2 > -1))
			result[y-1][x-2] = checkPieceAndWhile(y-1,x-2,white,board) ;
		
		if (toCheckCheh) {
			for(int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					if (result[i][j]) {
						Board boardTmp = board.clone(board);
						boardTmp.MoveClone(y, x, i, j);
						result[i][j] = !isCheck(white,boardTmp);
					}
				}
			}
		}
		return result;
	}
	
	
	boolean [][] possibleBishopMoves(int y,int x, boolean white, Board board, boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		boolean tmpBool;
	
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
			
				if (result[j][x_start] && toCheckCheh) {
					boardTmp = board.clone(board);
					boardTmp.MoveClone(y, x, j, x_start);
					tmpBool = isCheck(white,boardTmp);
					result[j][x_start] = !tmpBool;
					boardTmp.MoveClone(j, x_start, y, x);	
				}
				
				if (board.board[j][x_start] != null)
					break;
				
				x_start = x_start + x_step;
			}
		}
		return result;
	}
	
	
	boolean [][] possibleRooksMoves(int y,int x, boolean white, Board board,boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		Board boardTmp;
		boolean tmpBool;
		
		for(int i = x -1; i > -1; i --) {
			result[y][i] = checkPieceAndWhile(y,i,white,board); 
			
			if (result[y][i] && toCheckCheh) { 
				boardTmp = board.clone(board);
				boardTmp.MoveClone(y, x, y, i);
				tmpBool = isCheck(white,boardTmp);
				
				result[y][i] = !tmpBool;
							boardTmp.MoveClone(y, i, y, x);
			}
			if (board.board[y][i] != null)
				break;
		}
		
		
		for(int i = x + 1; i < 8; i ++) {
			result[y][i] = checkPieceAndWhile(y,i,white,board) ;
			
			if (result[y][i]  && toCheckCheh) {
				boardTmp = board.clone(board);
				boardTmp.MoveClone(y, x, y, i);
				tmpBool = isCheck(white,boardTmp);
				result[y][i] = !tmpBool;
				boardTmp.MoveClone(y, i, y, x);
			}
			
			if (board.board[y][i] != null)
				break;
		}

		for(int i = y + 1; i < 8; i ++) {
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			
			if (result[i][x] && toCheckCheh) {
				boardTmp = board.clone(board);
				boardTmp.MoveClone(y, x, i, x);
				tmpBool = isCheck(white,boardTmp);
				result[i][x] = !tmpBool;
				boardTmp.MoveClone(i, x, y, x);
			}

			if (board.board[i][x] != null)
				break;
		}

		for(int i = y - 1; i > -1; i --) {
			
			result[i][x] = checkPieceAndWhile(i,x,white,board) ;
			
			if (result[i][x] && toCheckCheh) {
				boardTmp = board.clone(board);
				boardTmp.MoveClone(y, x, i, x);
				tmpBool = isCheck(white,boardTmp);
				result[i][x] = !tmpBool;

				boardTmp.MoveClone(i, x, y, x);
			}
			
			if (board.board[i][x] != null)
				break;
		}
              
		return result;
	}

	boolean [][] possibleQueenMoves(int y,int x, boolean white, Board board,boolean toCheckCheh) {
		boolean [][] result = new boolean [8][8];
		
		boolean [][][] results = new boolean [2][8][8]; 
		results[0] =  possibleRooksMoves(y,x, white, board,toCheckCheh);
		results[1] =  possibleBishopMoves(y,x, white, board,toCheckCheh);

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
		
		
		for (int i = 0; i < results.length; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					result[j][k] = result[j][k] || results[i][j][k];
				}
			}
		}
		return result;
	}		
	
	  public boolean[][] isUnderAttack(boolean[][] possibleMoves, boolean white, Board board) {

	        boolean [][] result = new boolean [8][8];
	        boolean [][] result1 = new boolean [8][8];
	        boolean [][][] results = new boolean [15][8][8]; 
	        int index = 0;

	        int yKing = 99 ,xKing = 99;


	        for(int j=0; j < 8; j++) {
	            for(int i=0; i < 8; i++ ) {
	                if ((board.board[j][i] != null)
	                        && (board.board[j][i].isWhite() == white)
	                        && (board.board[j][i] instanceof King)) {
	                    yKing = j; xKing = i;
	                }
	            }
	        }

	        Board boardTmp = null;
	        for(int j=0; j < 8; j++) {
	            for(int i=0; i < 8; i++ ) {
	                if (possibleMoves[j][i]) {
	                    boardTmp = board.clone(board);
	                    boardTmp.MoveClone(yKing, xKing, j, i);
	                    result[j][i] = isCheck(white,boardTmp);
	                }
	            }
	        }

	        return result;
	    }

	
	public Piece getTypeOfPieceFromString(String stringType) {
		
		Piece piece = null;
		if (stringType.equals("Pawn"))  piece = new Pawn(-1,-1,true);
		if (stringType.equals("Bishop")) piece = new Bishop(-1,-1,true);
		if (stringType.equals("King")) piece = new King(-1,-1,true);
		if (stringType.equals("Knight")) piece = new Knight(-1,-1,true);
		if (stringType.equals("Rooks")) piece = new Rooks(-1,-1,true);
		if (stringType.equals("Queen")) piece = new Queen(-1,-1,true);
		
		return piece;
	}
	
	boolean checkCastling(Board board, boolean white, int rooksPosition, int kingPosition) {
		
		if ((board.board[7][kingPosition] == null) || (!(board.board[7][kingPosition] instanceof King))
			&& (board.board[7][rooksPosition] == null) && (!(board.board[7][rooksPosition] instanceof Rooks))) 
			   return false;

		if ((kingPosition != 3) && (kingPosition != 4))
			return false;
		
		if ((rooksPosition != 0) && (rooksPosition != 7))
			return false;
		
		if (rooksPosition == 0) {
			for (int i = rooksPosition + 1; i < kingPosition; i++) {
				if (board.board[7][i] != null)
					return false;
			}

			Board boardTmp = board.clone(board);
			boardTmp.MoveClone(7, kingPosition, 7, kingPosition - 1);
			if (isCheck(white,boardTmp))
				return false;
			
			boardTmp.MoveClone(7, kingPosition - 1, 7, kingPosition - 2);
			if (isCheck(white,boardTmp))
				return false;
			
		}
		else
		{
			for (int i = rooksPosition - 1; i > kingPosition; i--) {
				if (board.board[7][i] != null)
					return false;
			}

			Board boardTmp = board.clone(board);
			boardTmp.MoveClone(7, kingPosition, 7, kingPosition + 1);
			if (isCheck(white,boardTmp))
				return false;
			
			boardTmp.MoveClone(7, kingPosition + 1, 7, kingPosition + 2);
			if (isCheck(white,boardTmp))
				return false;
		}	
			
		
		if (isMoves(board, white, rooksPosition))
			return false;
		 
		return true;
	}
	
	boolean isMoves (Board board,boolean white, int rooksPosition) {
		 History history = new History(); 
		 DetailedHistory detailedHistory;
		 for(int i = 6; i < board.countOfMoves ; i++) {
			detailedHistory = history.getDetailedHistory(i);
			if (detailedHistory.isWhite() == white) { 
				
				if (rooksPosition == 0) {
					for (int j=board.countOfMoves;j>board.countOfMoves-6;j--){
						detailedHistory = history.getDetailedHistory(j);
						
						System.out.println("History Castling: " +
								detailedHistory.isWhite()
								+" "+ detailedHistory.getType()
								+" "+ detailedHistory.getStartY()
								+", "+ detailedHistory.getStartX()
								+" "+ detailedHistory.getEndY()
								+" "+ detailedHistory.getEndX()
								+" "+ j
								);
					}
					
				}
				
				
				if (getTypeOfPieceFromString(detailedHistory.getType()) instanceof King)
					return true;
				if ((getTypeOfPieceFromString(detailedHistory.getType()) instanceof Rooks) &&
				(detailedHistory.getStartX() == rooksPosition))
					return true;
			}
		}
		
	return false;	
	}
	
	boolean playerColor() {
		 History history = new History();
		 
		 DetailedHistory detailedHistory = history.getDetailedHistory(0);
		 
		 if (detailedHistory == null)
			 return(true);
		 
		 return (detailedHistory.startY > 5);
		 
	}
	
	public int [][] boolResultToInt (boolean boolResult[][]) {
		int index = 0;
		for (int i = 0; i <8; i++) {
			for (int j = 0; j <8; j++) {
				if (boolResult[i][j]) {
					index++;
				}
			}
		}
		
		int [][] result = new int [index][2];
		index = 0;
		for (int i = 0; i <8; i++) {
			for (int j = 0; j <8; j++) {
				if (boolResult[i][j]) {
					result[index][0] = j;
					result[index][1] = i;
					index++;
				}
			}
		}
		return result;
	}
	
	boolean wasMove(int yStart, int xStart,int yEnd,int xEnd) {
		boolean result = true;
		if ((yStart - yEnd) ==0 && (xStart - xEnd ==0))
			result = false;
		return result;
		
	}
	
	boolean isCheck(boolean white, Board board) {
		
		boolean [][] result = new boolean [8][8];
		boolean [][] result1 = new boolean [8][8];
		boolean [][][] results = new boolean [15][8][8]; 
		
		
		int yKing = 99 ,xKing = 99, index =0;
		
		
		for(int j=0; j < 8; j++) {
			for(int i=0; i < 8; i++ ) {
				if ((board.board[j][i] != null)
						&& (board.board[j][i].isWhite() == white)
						&& (board.board[j][i] instanceof King)) {
					yKing = j; xKing = i;
				}
			}
		}	
		
		
		
		
		for(int i=0; i < 8; i++) {
			for(int j=0; j < 8; j++ ) {
				
						
				if (board.board[j][i] == null)
					continue;
				if ((board.board[j][i].isWhite() == white)
						&& (board.board[j][i] instanceof King)) {
					yKing = j; xKing = i;
					
				}
				else if (board.board[j][i].isWhite() != white) {
					
					if (board.board[j][i] instanceof Pawn) { 
						if (playerColor() == board.board[j][i].isWhite())
							result = possiblePawnMoves(j,i,board.board[j][i].isWhite(),board,false);
						else
							result = possiblePawnMovesOther(j,i,board.board[j][i].isWhite(),board,false);
						
						
						
					}  
					if (board.board[j][i] instanceof Knight)
						result = possibleKnightMoves(j,i,board.board[j][i].isWhite(),board,false);
					
					if (board.board[j][i] instanceof Bishop)
						result = possibleBishopMoves(j,i,board.board[j][i].isWhite(),board,false);
					
					if (board.board[j][i] instanceof Rooks)
						result = possibleRooksMoves(j,i,board.board[j][i].isWhite(),board,false);

					if (board.board[j][i] instanceof Queen) {
						result = possibleBishopMoves(j,i,board.board[j][i].isWhite(),board,false);
						result1 = possibleRooksMoves(j,i,board.board[j][i].isWhite(),board,false);
						
						for(int k=0; k < 8; k++) {
							for(int n=0; n < 8; n++) {
								result[k][n] = result[k][n] || result1[k][n];
								
							}
						}
					}	
					if (!(board.board[j][i] instanceof King)) {
						results[index] = result;
						index ++;
					}
				}
				
			}
		}
		result = joinResults(results);
		return result[yKing][xKing];
	}

	
	
	
}
