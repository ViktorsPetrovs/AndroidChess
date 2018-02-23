package Main;

import java.util.*;

import chesspieces.Piece;

public class History {
	
	static Map<Integer,Board> boardHistory = new HashMap<>();
	static Map<Integer,DetailedHistory> boardDetailedHistory = new HashMap<>();
	public static class DetailedHistory{
			public int getStartX() {
			return startX;
		}

		public void setStartX(int startX) {
			this.startX = startX;
		}

		public int getStartY() {
			return startY;
		}

		public void setStartY(int startY) {
			this.startY = startY;
		}

		public int getEndX() {
			return endX;
		}

		public void setEndX(int endX) {
			this.endX = endX;
		}

		public int getEndY() {
			return endY;
		}

		public void setEndY(int endY) {
			this.endY = endY;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public boolean isWhite() {
			return isWhite;
		}

		public void setWhite(boolean isWhite) {
			this.isWhite = isWhite;
		}

			int startX,startY,endX,endY;
			String type = "";
			boolean isWhite = false;
		
		public DetailedHistory(int sx, int sy, int ex, int ey, String t, boolean color){
			this.startX = sy;
			this.startY = sx;
			this.endX = ey;
			this.endY = ex;
			this.isWhite = color;
			this.type=t;			
		}		
	}
	
	public static void save(Board board, int sx, int sy, int ex, int ey, String t, boolean color){
		
		boardHistory.put(Board.countOfMoves , board.clone(board));
		boardDetailedHistory.put(Board.countOfMoves, new DetailedHistory(sx,sy,ex,ey,t,color));
				
	}

  public static Board getHistory(int moveNumber)
  {
	  
	  return boardHistory.get(moveNumber);
  }
  
  public static DetailedHistory getDetailedHistory(int moveNumber)
  {
	  
	  return boardDetailedHistory.get(moveNumber);
  }
}