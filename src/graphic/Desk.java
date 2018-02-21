package graphic;

import Main.*;
import Network.IO;
import chesspieces.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.*;

public class Desk extends JFrame implements MouseListener, MouseMotionListener {
	JLayeredPane layeredPane;
	JPanel chessBoard;
	JLabel chessPiece;
	int xAdjustment;
	int yAdjustment;
	public static int xStart, yStart;
	public static JPanel[][] squareArray = new JPanel[8][8];
	public static Board board;
	TestMethods testM = new TestMethods();
	public static Container previvous;
	static String pawnW = "/home/student/workspace/AndroidChess/src/resources/Wpawn.png";
	static String pawnB = "/home/student/workspace/AndroidChess/src/resources/Bpawn.png";
	static String rookW = "/home/student/workspace/AndroidChess/src/resources/Wrook.png";
	static String rookB = "/home/student/workspace/AndroidChess/src/resources/Brook.png";
	static String bishopW = "/home/student/workspace/AndroidChess/src/resources/Wbishop.png";
	static String bishopB = "/home/student/workspace/AndroidChess/src/resources/Bbishop.png";
	static String kingW = "/home/student/workspace/AndroidChess/src/resources/Wking.png";
	static String kingB = "/home/student/workspace/AndroidChess/src/resources/Bking.png";
	static String knightW = "/home/student/workspace/AndroidChess/src/resources/Wknight.png";
	static String knightB = "/home/student/workspace/AndroidChess/src/resources/Bknight.png";
	static String queenW = "/home/student/workspace/AndroidChess/src/resources/Wqueen.png";
	static String queenB = "/home/student/workspace/AndroidChess/src/resources/Bqueen.png";

	Logic test = new Logic();

	public Desk() throws IOException {

		Dimension boardSize = new Dimension(800, 800);

		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 8));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		// JPanel piece;
		// JLabel PawnW = new JLabel(new
		// ImageIcon("/home/student/workspace/AndroidChess/src/resources/PawnW.png"));
		// piece.add(PawnW);

		for (int i = 0; i < 64; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);

			int row = (i / 8) % 2;
			if (row == 0)
				if (i % 2 == 0) {
					square.setBackground(Color.white);
				} else {
					square.setBackground(Color.gray);
				}

			else if (i % 2 == 0) {

				square.setBackground(Color.gray);
			} else {
				square.setBackground(Color.white);
			}

			// System.out.println(chessBoard.size());
			// square=(JPanel) chessBoard.getComponent(4);
			// square.add(PawnW);

		}

	}

	public void initializeBoardInGraphic(Board a) {
		this.board = a;
	}

	public void transformToArray() {
		int i = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				squareArray[x][y] = (JPanel) chessBoard.getComponent(i);
				i++;

			}
		}
	}

	public static void drawBoardGraphic() {
		int i = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				squareArray[x][y].removeAll();
			}
		}
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (board.board[x][y] != null) {
					if (board.board[x][y] instanceof Pawn) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(pawnW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(pawnB)));
					}
					if (board.board[x][y] instanceof King) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(kingW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(kingB)));
					}
					if (board.board[x][y] instanceof Rooks) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(rookW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(rookB)));
					}
					if (board.board[x][y] instanceof Knight) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(knightW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(knightB)));
					}
					if (board.board[x][y] instanceof Queen) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(queenW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(queenB)));
					}
					if (board.board[x][y] instanceof Bishop) {
						if (board.board[x][y].isWhite())
							squareArray[x][y].add(new JLabel(new ImageIcon(bishopW)));
						else
							squareArray[x][y].add(new JLabel(new ImageIcon(bishopB)));
					}
				}
			}
		}
		// boardUpdate();
		System.out.println("GraphicDone");
		board.drawBoard();

	}

	public void boardUpdate() {
		for (int i = 0; i < 64; i++) {

			int row = (i / 8) % 2;
			if (row == 0)
				if (i % 2 == 0) {
					chessBoard.getComponent(i).setBackground(Color.white);
				} else {
					chessBoard.getComponent(i).setBackground(Color.gray);
				}

			else if (i % 2 == 0) {

				chessBoard.getComponent(i).setBackground(Color.gray);
			} else {
				chessBoard.getComponent(i).setBackground(Color.white);
			}

		}

	}

	public void HighlightOn(int x, int y) {
		int[][] s;
		testM.drawBooleanArray(test.possibleMoves(x, y, board));
		s = test.boolResultToInt(test.possibleMoves(x, y, board));
		int ix = 0;
		System.out.println(s.length);
		System.out.println("[" + s[ix][0] + "," + s[ix][1] + "]");
		while ((s[ix][0]) != 0 || (s[ix][1]) != 0) {
			System.out.println("HighLightLoop entered");
			System.out.println(s[ix][1]);
			squareArray[s[ix][1]][s[ix][0]].setBackground(Color.YELLOW);
			ix++;
		}
	}

	public void HighlightOff() {

		for (int i = 0; i < 64; i++) {
			JPanel square;
			square = (JPanel) chessBoard.getComponent(i);

			int row = (i / 8) % 2;
			if (row == 0)
				if (i % 2 == 0) {
					square.setBackground(Color.white);
				} else {
					square.setBackground(Color.gray);
				}

			else if (i % 2 == 0) {
				square.setBackground(Color.gray);
			} else {
				square.setBackground(Color.white);
			}
		}

	}

	public void mouseClicked(MouseEvent e) {
	}

	public int transformXY(int xAdjustment) {
		int x = 0;
		if ((-xAdjustment) > 0 && (-xAdjustment) < 100) {
			x = 0;
		}
		if ((-xAdjustment) > 100 && (-xAdjustment) < 200) {
			x = 1;
		}
		if ((-xAdjustment) > 200 && (-xAdjustment) < 300) {
			x = 2;
		}
		if ((-xAdjustment) > 300 && (-xAdjustment) < 400) {
			x = 3;
		}
		if ((-xAdjustment) > 400 && (-xAdjustment) < 500) {
			x = 4;
		}
		if ((-xAdjustment) > 500 && (-xAdjustment) < 600) {
			x = 5;
		}
		if ((-xAdjustment) > 600 && (-xAdjustment) < 700) {
			x = 6;
		}
		if ((-xAdjustment) > 700 && (-xAdjustment) < 800) {
			x = 7;
		}
		return x;

	}

	@Override
	public void mouseDragged(MouseEvent mde) {
		if (chessPiece == null)
			return;
		chessPiece.setLocation(mde.getX() - 50, mde.getY() - 50);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		chessPiece = null;
		int x = -1;
		int y = -1;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());
		if (c instanceof JLabel) {
			previvous = (Container) c;
			chessPiece = (JLabel) c;
			chessPiece.setLocation(e.getX() - 50, e.getY() - 50);
			layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
		}
		// chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
		if (c instanceof JLabel) {
			previvous = c.getParent();
			c = c.getParent();
			c.setBackground(Color.blue);
		}

		Point parentLocation = c.getParent().getLocation();
		xAdjustment = parentLocation.x - e.getX();
		yAdjustment = parentLocation.y - e.getY();

		x = transformXY(xAdjustment);
		y = transformXY(yAdjustment);
		System.out.println("Coordinates: [" + x + "," + y + "]");

		xStart = x;
		yStart = y;
		HighlightOn(y, x);

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (chessPiece == null)
			return;
		chessPiece.setVisible(false);
		int x = -1;
		int y = -1;
		Component c = chessBoard.findComponentAt(e.getX(), e.getY());

		Point parentLocation = c.getParent().getLocation();
		if ((c instanceof JLabel)) {
			xAdjustment = e.getX();
			yAdjustment = e.getY();
			x = transformXY(-xAdjustment);
			y = transformXY(-yAdjustment);
		} else {
			xAdjustment = parentLocation.x - e.getX();
			yAdjustment = parentLocation.y - e.getY();
			x = transformXY(xAdjustment);
			y = transformXY(yAdjustment);
		}
		System.out.println("xAdjust;" + xAdjustment);

		System.out.println("yAdjust;" + yAdjustment);
		if (c instanceof JLabel) {
			Container parent = c.getParent();
			parent.remove(0);
			parent.add(chessPiece);
		} else {
			Container parent = (Container) c;
			parent.add(chessPiece);
		}

		System.out.println("Move: [" + yStart + "," + xStart + "]-[" + y + "," + x + "]");

		//if (test.possibleMoves(xStart, yStart, board)[x][y]) {
			if (board.board[yStart][xStart] != null) {
				System.out.println("InRElease:" + x + "," + y);
				if ((yStart - y != 0) || (xStart - x != 0))
					board.Move(yStart, xStart, y, x);
			}
		//}
		HighlightOff();

		drawBoardGraphic();
		// Container square = chessPiece.getParent();
		// square.remove(chessPiece);
		// square.validate();
		// square.repaint();

	}

}