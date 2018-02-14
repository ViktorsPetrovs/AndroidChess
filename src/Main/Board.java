package Main;

import chesspieces.*;

public class Board {
	static int countOfMoves;
	public Piece[][] board;

	public Board() {
		board = new Piece[8][8];
	}

	public Object[][] getBoard() {
		return board;
	}

	public void setCoordinates(int x, int y, Piece obj) {
		board[x][y] = obj;

	}
	
	public void clearCoordinates(int x, int y) {
		board[x][y] = null;

	}
	
	public void Move(int xStart, int yStart, int xEnd, int yEnd){
		this.board[xStart][yStart].setX(xEnd);
		this.board[xStart][yStart].setY(yEnd);
		this.setCoordinates(xEnd,yEnd,this.board[xStart][yStart]);
		this.clearCoordinates(xStart,yStart);
	}
	public void Move(int[] array){
		if(array.length!=4){
			System.out.println("Problem with moving input");
		}
		this.board[array[0]][array[1]].setX(array[2]);
		this.board[array[0]][array[1]].setY(array[3]);
		this.setCoordinates(array[2],array[3],this.board[array[0]][array[1]]);
		this.clearCoordinates(array[0],array[1]);
		this.drawBoard();
	}

	public void drawBoard() {
		System.out.println("Starting Draw");
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (this.board[x][y] != null)
					System.out.print(this.board[x][y].toString() + " ");
				if (this.board[x][y] == null)
					System.out.print(" null ");
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

		return board;
	}

}
