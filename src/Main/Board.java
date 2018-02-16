package Main;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import Network.Test;
import chesspieces.*;

public class Board {
	private static Logger log = Logger.getLogger(Board.class.getName());
	static int  countOfMoves = 0;
	
	public Piece[][] board;

	public Board() {
		board = new Piece[8][8];
		int countOfMoves = 0;
	}

	public Object[][] getBoard() {
		return board;
	}

	public void setCoordinates(int x, int y, Piece obj) {
		board[x][y] = obj;

	}
	
	public void clearCoordinates(int x, int y) {
		this.board[x][y] = null;

	}
	
	public void Move(int xStart, int yStart, int xEnd, int yEnd){
		
		try {
		if (this.board[xStart][yStart]==null){
			System.out.println("Cell is null with coordinates: "+xStart+","+yStart+","+ xEnd + "," +yEnd);
		}
		Piece cell = this.board[xStart][yStart];
		cell.setX(xEnd);
		cell.setY(yEnd);
		this.setCoordinates(xEnd,yEnd,cell);
		this.clearCoordinates(xStart,yStart);
		this.countOfMoves++;
		System.out.println("Move:");
		System.out.println(TestMethods.coordinatesToLog(xStart,yStart,xEnd,yEnd));
		
		History.save(this, xStart, yStart, xEnd, yEnd, cell.toString(), cell.isWhite());
		} catch (Exception e) {
			log.log(Level.WARNING, Board.class.getName() + " Move(int,int,int,int) ", e);
		}
	}
	
	
	public void Move(int[] array){
		if(array.length!=4){
//			System.out.println("Problem with moving input");
			log.log(Level.WARNING, this.getClass().getName() + " Move(int[])");
		}
		Piece cell=this.board[array[0]][array[1]];
		cell.setX(array[2]);
		cell.setY(array[3]);
		this.setCoordinates(array[2],array[3],cell);
		this.clearCoordinates(array[0],array[1]);
		log.info(this.getClass().getName() + " Move(int[]) VALUES : " + array[0] + " " + array[1] +
				" " + array[2] + " " + array[3]);
		this.drawBoard();
		this.countOfMoves++;
		//History.save(this);
		//history.save();
	}

	public void drawBoard() {
		System.out.println("Starting Draw");
		String str = "";
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.board[x][y] != null) {
					str += this.board[x][y].toString() + " ";}
				if (this.board[x][y] == null){
					str += "null ";
				}
			}
			
//			System.out.println();
			str += "\n";
		}
		log.info(Board.class.getName() + " drawBoard()\n" + str);
	}

	public Piece[][] parseToFen(String str) {
		Board fenBoard = new Board();
		
		StringTokenizer st = new StringTokenizer(str);
		int x = 0, y = 0;
		while (st.hasMoreTokens()) {
			y = 0;
			String token = st.nextToken("/");
			while (y < 8) {
				if (token.substring(0, 1).matches("[1-9]")) {
					fenBoard.board[x][y] = null;
					if (Integer.valueOf(token.substring(0, 1)) > 1)
						token = new String((Integer.valueOf(token.substring(0, 1)) - 1) + token.substring(1, token.length()));
					else
						token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("b")) {
					fenBoard.board[x][y] = new Bishop(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("B")) {
					fenBoard.board[x][y] = new Bishop(x, y, true);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("k")) {
					fenBoard.board[x][y] = new King(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("K")) {
					fenBoard.board[x][y] = new King(x, y, true);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("n")) {
					fenBoard.board[x][y] = new Knight(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("N")) {
					fenBoard.board[x][y] = new Knight(x, y, true);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("p")) {
					fenBoard.board[x][y] = new Pawn(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("P")) {
					fenBoard.board[x][y] = new Pawn(x, y, true);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("q")) {
					fenBoard.board[x][y] = new Queen(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("Q")) {
					fenBoard.board[x][y] = new Queen(x, y, true);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("r")) {
					fenBoard.board[x][y] = new Rooks(x, y, false);
					token = new String(token.substring(1, token.length()));
				} else if (token.substring(0, 1).equals("R")) {
					fenBoard.board[x][y] = new Rooks(x, y, true);
					token = new String(token.substring(1, token.length()));
				} 
				y++;
			}
			x++;
		}
		
		return fenBoard.board;
	}
	public String parseToFen(Board board) {
		String str = "";
		for (int x = 0; x < 8; x++) {
			int count = 0;
			for (int y = 0; y < 8; y++) {
				if (board.board[x][y] == null) {
					count++;
				} else if (board.board[x][y].getClass() == Bishop.class) {
					if (board.board[x][y].isWhite())
						str += "B";
					else
						str += "b";
					count = 0;
				} else if (board.board[x][y].getClass() == King.class) {
					if (board.board[x][y].isWhite())
						str += "K";
					else
						str += "k";
					count = 0;
				} else if (board.board[x][y].getClass() == Knight.class) {
					if (board.board[x][y].isWhite())
						str += "N";
					else
						str += "n";
					count = 0;
				} else if (board.board[x][y].getClass() == Pawn.class) {
					if (board.board[x][y].isWhite())
						str += "P";
					else
						str += "p";
					count = 0;
				} else if (board.board[x][y].getClass() == Queen.class) {
					if (board.board[x][y].isWhite())
						str += "Q";
					else
						str += "q";
					count = 0;
				} else if (board.board[x][y].getClass() == Rooks.class) {
					if (board.board[x][y].isWhite())
						str += "R";
					else
						str += "r";
					count = 0;
				}
				if (count != 0)
					if (str.substring(str.length() - 1).matches("[1-9]")) {
						String str1 = new String(str.substring(0, str.length() - 1));
						str1 += (Integer.valueOf(str.substring(str.length() - 1)) + 1);
						str = str1;
						count = 0;
					} else
						str += count;
			}
			if (count != 0)
				str += count;
			str += "/";
		}
		return str.substring(0, str.length() - 1);
	}
	
	public Board clone(Board board) {
		Board cloned = new Board();
		cloned.countOfMoves= board.countOfMoves;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				if (board.board[x][y] != null) {
					cloned.board[x][y] = board.board[x][y].getClone();
				}

			}
			
		}

		return cloned;
	}

}
