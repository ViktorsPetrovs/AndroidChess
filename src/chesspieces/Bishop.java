package chesspieces;

public class Bishop extends Piece implements Move {

	public Bishop(int x, int y, boolean isWhite) {
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
		return new Bishop(this.getX(), this.getY(), this.isWhite);
	}

	@Override
	public String getSign() {
		if(this.isWhite) return "\u2657";
		return "\u265D";
	}

}
