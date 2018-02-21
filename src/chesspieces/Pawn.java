package chesspieces;

import  Main.*;

public class Pawn extends Piece implements Move {

	public Pawn(int x, int y, boolean isWhite) {
		super(x, y, isWhite);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void move(int x,int y) {
//		
//	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
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
