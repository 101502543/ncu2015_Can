package cot.qin.sre;

import java.awt.Image;

import javax.swing.ImageIcon;

import cot.qin.sdm.SceneDataModule;

public class SceneRenderEngine {
	private static SceneDataModule sdm = null;
	private static DOMmok domMok = null;
	private int X = 0, Y = 0;
	private int windowSize = 21;
	private int panelSizeX = 1000, panelSizeY = 450;
	private int imageSizeX = 100, imageSizeY = 114;
	private int zeroX, zeroY; // window position
	private int[] nextColumn = { 48, 36 }, nextRow = { -48, 36 };
	private final int coordinateX = panelSizeX / 2 - imageSizeX,
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
	}

	public int[] getWindowInf() {

		getWindow();
		int[] information = { zeroX, zeroY, X, Y, windowSize, nextColumn[0],
				nextColumn[1], nextRow[0], nextRow[1], imageSizeX, imageSizeY };
		// int theX = zeroX, theY = zeroY;
		// for (int y = Y; y < Y + windowSize; y++) {
		// for (int x = X; x < X + windowSize; x++) {
		// ImageIcon test = new ImageIcon(map.getBlockImage(x, y));
		// Image img = test.getImage();
		// g.drawImage(img, theX + (x - X) * nextColumn[0], theY + (x - X)
		// * nextColumn[1], imageSize, imageSize, panel);
		// }
		// theX = zeroX + (y - Y) * nextRow[0];
		// theY = zeroY + (y - Y) * nextRow[1];
		// }
		return information;
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

	public void setZeroX(int x) {
		zeroX += x;
	}

	public void setZeroY(int y) {
		zeroY += y;
	}

	public boolean inWindow(int x, int y) {
		if (x > X * 100 && x < (X + 21) * 100 && y > Y * 100
				&& y < (Y + 21) * 100) {
			return true;
		} else {
			return false;
		}
	}

	public int[] changeXY(int[] xy) {
		int objectX = 0, objectY = -36;
		int[] information = sre.getWindowInf();
		// int[] information = { zeroX, zeroY, X, Y, windowSize, nextColumn[0],
		// nextColumn[1], nextRow[0], nextRow[1], imageSize };
		if (inWindow(xy[0], xy[1])) {
			double blockX = xy[0] / 100;
			double blockY = xy[1] / 100;
			double blockX_tran = information[0] + (blockY - 1 - information[3])
					* information[7] + (blockX - information[2])
					* information[5];
			double blockY_tran = information[1] + (blockY - 1 - information[3])
					* information[8] + (blockX - information[2])
					* information[6];
			blockX = (xy[0] - blockX * 100);
			blockY = (xy[1] - blockY * 100);
			blockX_tran = blockX_tran + (blockX * 4 / 5 - blockY * 4 / 5) * 0.6;
			blockY_tran = blockY_tran + (blockX * 3 / 5 + blockY * 3 / 5) * 0.6;
			// if (information[2] != 0)
			// windowX = windowX % information[2];
			// if (information[3] != 0)
			// windowY = windowY % information[3];
			// int finalX = information[0]+windowX * 4 / 5 - windowY * 4 / 5;
			// int finalY = information[1]+windowX * 3 / 5 + windowY * 3 / 5;
			return new int[] { objectX + (int) blockX_tran,
					objectY + (int) blockY_tran };
		} else {
			return null;
		}
	}
}
