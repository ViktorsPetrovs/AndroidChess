package chesspieces;

public class Rooks extends Piece implements Move {

	public Rooks(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public void delete() {

	}

	@Override
	public Piece getClone() {
		return new Rooks(this.getX(), this.getY(), this.isWhite);
	}

	@Override
	public String getSign() {
		if (this.isWhite)
			return "\u2656";
		return "\u265C";
	}

}
