package chesspieces;

public abstract class Piece implements Move{

 int x;
 int y;
 boolean isWhite;
 boolean isAlive;
 
 public Piece(int x, int y, boolean isWhite){
	 System.out.print("Object created");
	 this.x = x;
	 this.y =y;
	 this.isWhite=isWhite;
	 this.isAlive=true;
 }
 
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public boolean isWhite() {
	return isWhite;
}
public void setWhite(boolean isWhite) {
	this.isWhite = isWhite;
}
public boolean isAlive() {
	return isAlive;
}
public void setAlive(boolean isAlive) {
	this.isAlive = isAlive;
}


public String toString(){
	return this.getClass().getSimpleName();
}
	
}
