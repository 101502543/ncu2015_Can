package cot.qin.sre;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cot.qin.sdm.*;

public class SceneRenderEngine extends JPanel {
	private static SceneDataModule sdm = null;
	private static DOMmok domMok = null;
	private Map map;
	private int X = 0, Y = 0;
	private int windowSize = 21;
	private int panelSizeX = 1000, panelSizeY = 450;
	private int imageSize = 100;
	private int zeroX, zeroY; // window position
	private int[] nextColumn = { 48, 32 }, nextRow = { -48, 32 };
	private final int coordinateX = panelSizeX / 2 - imageSize,
			coordinateY = 0 - panelSizeY;
	public boolean outOfBoundXY = false;
	// private int xInPress, yInPress, xInDrag, yInDrag, old_zeroX, old_zeroY;
	public static SceneRenderEngine sre = null;

	public static SceneRenderEngine getInstance() {
		if (sre == null)
			sre = new SceneRenderEngine();
		return sre;
	}

	public SceneRenderEngine() {
		// TODO Auto-generated constructor stub
		sdm = SceneDataModule.getInstance();
		sdm.loadMap("mapfile");
		map = sdm.getMap();
		domMok = DOMmok.getInstance();
	}

	public void renderScene() {
		int oldx = X, oldy = Y;
		int[] XY = domMok.getVirtualCharacterXY();
		X = XY[0];
		Y = XY[1];
		if (X < 0 || Y < 0 || X + 20 > 49 || Y + 20 > 49) {
			X = oldx;
			Y = oldy;
			outOfBoundXY = true;
			return;
		}
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		getWindow();
		int theX = zeroX, theY = zeroY;
		for (int y = Y; y < Y + windowSize; y++) {
			for (int x = X; x < X + windowSize; x++) {
				ImageIcon test = new ImageIcon(map.getBlockImage(x, y));
				Image img = test.getImage();
				g.drawImage(img, theX + (x - X) * nextColumn[0], theY + (x - X)
						* nextColumn[1], imageSize, imageSize, this);
			}
			theX = zeroX + (y - Y) * nextRow[0];
			theY = zeroY + (y - Y) * nextRow[1];
		}
	}

	private void getWindow() {
		int oldX = X;
		int oldY = Y;
		isbound();
		resetXY();
		if (X < 0 || Y < 0 || X + windowSize - 1 == 50
				|| Y + windowSize - 1 == 50) {
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
		if (X + windowSize - 1 == 49) {
			if (zeroX < coordinateX) {
				zeroX = coordinateX;
			}
			if (zeroY < coordinateY) {
				zeroY = coordinateY;
			}
		}
		if (Y + windowSize - 1 == 49) {
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
			// xInPress = xInDrag;
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
			// yInPress = yInDrag;
		}
	}
}
