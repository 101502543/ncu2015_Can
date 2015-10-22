import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {

	private BasicBlock[][] scene;
	private int X = 2500, Y = 1000;
	private int oldX, oldY;
	private int sceneSizeX = 50, sceneSizeY = 20;
	private int[] nextColumn = { 48, 32 }, nextRow = { -48, 32 };
	private int frameSizeX = 500, frameSizeY = 300;
	private int imageWidth = 100, imageHigh = 100;
	private int zeroX, zeroY;
	private final int coordinateX = frameSizeX / 2 - imageWidth,
			coordinateY = 0 - frameSizeY;
	private int LcornerX, LcornerY;

	public ContentPanel() {

		setMap();
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
				getWindow();
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		// ���ecolumn�A�erow
		super.paintComponent(g);
		getWindow();
		int theX = zeroX, theY = zeroY;
		for (int i = LcornerY; i < LcornerY + 15; i++) {
			for (int j = LcornerX; j < LcornerX + 15; j++) {
				ImageIcon test = new ImageIcon(scene[i][j].getType());
				Image img = test.getImage();
				g.drawImage(img, theX + (j - LcornerX) * nextColumn[0], theY
						+ (j - LcornerX) * nextColumn[1], imageWidth,
						imageHigh, this);
			}
			theX = zeroX + (i - LcornerY) * nextRow[0];
			theY = zeroY + (i - LcornerY) * nextRow[1];
		}

	}

	private void getWindow() {
		oldX = X;
		oldY = Y;
		// Y < 1200 && Y > 700
//		if (X < 4200 && X > 700) {
//			if (Math.abs(zeroX - coordinateX) == 100) {
//				if (zeroX > coordinateX) {
//					X -= 100;
//					Y += 100;
//				} else {
//					X += 100;
//					Y -= 100;
//				}
//				zeroX = coordinateX;
//			}
//		} else {
//			if (zeroX < coordinateX - 7 * imageWidth) {
//				zeroX = coordinateX - 7 * imageWidth;
//			}else if (zeroX > coordinateX + 7 * imageWidth) {
//				zeroX = coordinateX + 5 * imageWidth;
//			}
//		}
		if (Math.abs(zeroX - coordinateX) == 100) {
			if (zeroX > coordinateX) {
				X -= 100;
				Y += 100;
			} else {
				X += 100;
				Y -= 100;
			}
			zeroX = coordinateX;
		}
		if (Math.abs(zeroY - coordinateY) == 75) {
			if (zeroY > coordinateY) {
				X -= 100;
				Y -= 100;
			} else {
				X += 100;
				Y += 100;
			}
			zeroY = coordinateY;
		}
		int indexX = X / 100;
		int indexY = Y / 100;
		LcornerX = indexX - 7;
		LcornerY = indexY - 7;
		if (LcornerX < 0 || LcornerY < 0 || indexX + 7 >= sceneSizeX
				|| indexY + 7 >= sceneSizeY) {
			System.out.println("Is bound!");
			System.out.println(oldX + " " + oldY);
			X = oldX;
			Y = oldY;
		}
	}

	private void setMap() {
		zeroX = coordinateX;
		zeroY = coordinateY;
		scene = new BasicBlock[sceneSizeY][sceneSizeX];
		for (int y = 0; y < sceneSizeY; y++) {
			for (int x = 0; x < sceneSizeX; x++) {
				if (x == sceneSizeX - 1 || y == sceneSizeY - 1 || x == 0
						|| y == 0 || x == X / 100 && y == Y / 100) {
					scene[y][x] = new BasicBlock(1);
				} else {
					scene[y][x] = new BasicBlock(0);
				}
				// scene[y][x] = new BasicBlock((int) (Math.random() * 2));
			}
		}
	}
}
