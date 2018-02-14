package chesspieces;

public class Queen extends Piece implements Move {

	public Queen(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
		// TODO Auto-generated constructor stub
	}

//	@Override
//public void move(int x,int y) {
//		
//	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Piece getClone() {
		return new Queen(this.getX(), this.getY(), this.isWhite);
	}

}
