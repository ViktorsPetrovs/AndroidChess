package chesspieces;

public class Knight extends Piece implements Move {

	public Knight(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public void delete() {
		
	}
	@Override
	public Piece getClone() {
		return new Knight(this.getX(), this.getY(), this.isWhite);
	}
	
	@Override
	public String getSign() {
		if(this.isWhite) return "\u2658";
		return "\u265E";
	}

}
