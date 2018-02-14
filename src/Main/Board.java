package Main;

import chesspieces.*;

public class Board {
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
		
		if (this.board[xStart][yStart]==null){System.out.println("Cell is null with coordinates: "+xStart+","+yStart+","+ xEnd + "," +yEnd);}
		Piece cell = this.board[xStart][yStart];
		cell.setX(xEnd);
		cell.setY(yEnd);
		this.setCoordinates(xEnd,yEnd,cell);
		this.clearCoordinates(xStart,yStart);
		this.countOfMoves++;
		System.out.println("Move:");
		System.out.println(TestMethods.coordinatesToLog(xStart,yStart,xEnd,yEnd));
		
		History.save(this, xStart, yStart, xEnd, yEnd, cell.toString(), cell.isWhite());
	}
	
	
	public void Move(int[] array){
		if(array.length!=4){
			System.out.println("Problem with moving input");
		}
		Piece cell=this.board[array[0]][array[1]];
		cell.setX(array[2]);
		cell.setY(array[3]);
		this.setCoordinates(array[2],array[3],cell);
		this.clearCoordinates(array[0],array[1]);
		this.drawBoard();
		this.countOfMoves++;
		//History.save(this);
		//history.save();
	}

	public void drawBoard() {
		System.out.println("Starting Draw");
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.board[x][y] != null)
					System.out.print(this.board[x][y].toString() + " ");
				if (this.board[x][y] == null)
					System.out.print("null ");
			}
			System.out.println();

		}
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
