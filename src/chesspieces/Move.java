package chesspieces;

public interface Move {
//	public void move(int x,int y);
	
    public void delete();
    public Piece getClone();
    public String getSign();
	
}
