package portotype_e;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cot.qin.sdm.*;

public class ContentPanel extends JPanel {

	private BasicBlock[][] scene;
	private int X = 0, Y = 0; // window index
	private int sceneSizeX = 50, sceneSizeY = 50;
	private int panelSizeX = 1000, panelSizeY = 450;
	private int windowSize = 21;
	private int imageWidth = 100, imageHigh = 100;
	private int[] nextColumn = { 48, 32 }, nextRow = { -48, 32 }; // inportant
	private int zeroX, zeroY; // window position
	private final int coordinateX = panelSizeX / 2 - imageWidth,
			coordinateY = 0 - panelSizeY;
	private int xInPress, yInPress, xInDrag, yInDrag, old_zeroX, old_zeroY;

	public ContentPanel() {

		setMap(readMap());
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				xInDrag = e.getX();
				yInDrag = e.getY();
				zeroX = old_zeroX + xInDrag - xInPress;
				zeroY = old_zeroY + yInDrag - yInPress;
				repaint();
			}
		});
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				xInPress = e.getX();
				yInPress = e.getY();
				old_zeroX = zeroX;
				old_zeroY = zeroY;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					zeroY -= 25;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					zeroY += 25;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					zeroX -= 25;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					zeroX += 25;
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		getWindow();
		int theX = zeroX, theY = zeroY;
		for (int i = Y; i < Y + windowSize; i++) {
			for (int j = X; j < X + windowSize; j++) {
				ImageIcon test = new ImageIcon(scene[i][j].getTypeImage());
				Image img = test.getImage();
				g.drawImage(img, theX + (j - X) * nextColumn[0], theY + (j - X)
						* nextColumn[1], imageWidth, imageHigh, this);
			}
			theX = zeroX + (i - Y) * nextRow[0];
			theY = zeroY + (i - Y) * nextRow[1];
		}

	}

	private void getWindow() {
		int oldX = X;
		int oldY = Y;
		isbound();
		resetXY();
		if (X < 0 || Y < 0 || X + windowSize - 1 == sceneSizeX
				|| Y + windowSize - 1 == sceneSizeY) {
			X = oldX;
			Y = oldY;
		}
	}

	private void isbound() {
		if (X == 0) {
			if (zeroX > coordinateX) {
				zeroX = coordinateX;
			}
			if (zeroY > coordinateY) {
				zeroY = coordinateY;
			}
		}
		if (Y == 0) {
			if (zeroX < coordinateX) {
				zeroX = coordinateX;
			}
			if (zeroY > coordinateY) {
				zeroY = coordinateY;
			}
		}
		if (X + windowSize - 1 == sceneSizeX - 1) {
			if (zeroX < coordinateX) {
				zeroX = coordinateX;
			}
			if (zeroY < coordinateY) {
				zeroY = coordinateY;
			}
		}
		if (Y + windowSize - 1 == sceneSizeY - 1) {
			if (zeroX > coordinateX) {
				zeroX = coordinateX;
			}
			if (zeroY < coordinateY) {
				zeroY = coordinateY;
			}
		}
	}

	private void resetXY() {
		if (Math.abs(zeroX - coordinateX) >= 100) {
			if (zeroX > coordinateX) {
				X -= 1;
				Y += 1;
				zeroX -= 100;
			} else {
				X += 1;
				Y -= 1;
				zeroX += 100;
			}
			xInPress = xInDrag;
		}
		if (Math.abs(zeroY - coordinateY) >= 75) {
			if (zeroY > coordinateY) {
				X -= 1;
				Y -= 1;
				zeroY -= 75;
			} else {
				X += 1;
				Y += 1;
				zeroY += 75;
			}
			yInPress = yInDrag;
		}
	}

	// SDM
	private void setMap(int[][] map) {
		zeroX = coordinateX;
		zeroY = coordinateY;
		scene = new BasicBlock[sceneSizeY][sceneSizeX];
		for (int y = 0; y < sceneSizeY; y++) {
			for (int x = 0; x < sceneSizeX; x++) {
				scene[y][x] = new BasicBlock(map[y][x]);
			}
		}
	}

	// SDM
	private int[][] readMap() {
		int[][] map = new int[50][50];
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					"mapfile.txt"));
			for (int i = 0; i < map.length; i++) {
				String input = bufferedReader.readLine();
				String[] strings = input.split(" ");
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = Integer.parseInt(strings[j]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
}
