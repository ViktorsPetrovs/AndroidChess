
public class Board {
	private Object[][] board;
	
	public Board() {
		board = new Object[8][8];
	}

	public Object[][] getBoard() {
		return board;
	}
	
	public void setCoordinates(int x, int y, Object obj) {
		board[x][y] = obj;
	}
}
