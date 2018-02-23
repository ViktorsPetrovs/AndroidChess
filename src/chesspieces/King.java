package chesspieces;

public class King extends Piece implements Move {

	public King(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public void delete() {
		
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
