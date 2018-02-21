package chesspieces;

public class King extends Piece implements Move {

	public King(int x, int y, boolean isWhite) {
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
		return new King(this.getX(), this.getY(), this.isWhite);
	}
	@Override
	public String getSign() {
		if(this.isWhite) return "\u2654";
		return "\u265A";
	}

}
