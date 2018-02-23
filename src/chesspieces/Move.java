package chesspieces;

public interface Move {
	
    public void delete();
    public Piece getClone();
    public String getSign();
	
}
