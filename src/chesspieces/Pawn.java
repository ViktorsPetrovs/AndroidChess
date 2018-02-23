package chesspieces;

import  Main.*;

public class Pawn extends Piece implements Move {

	public Pawn(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
	}

	@Override
	public void delete() {		
	}
	
	@Override
	public Piece getClone() {
		return new Pawn(this.getX(), this.getY(), this.isWhite);
	}
	
	@Override
	public String getSign() {
		if(this.isWhite) return "\u2659";
		return "\u265F";
	}
	
}
