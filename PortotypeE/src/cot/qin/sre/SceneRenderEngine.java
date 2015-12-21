package cot.qin.sre;

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
		int[] information = sre.getWindowInf();
		// int[] information = { zeroX, zeroY, X, Y, windowSize, nextColumn[0],
		// nextColumn[1], nextRow[0], nextRow[1], imageSize };
		if (inWindow(xy[0], xy[1])) {
			int windowX = xy[0];
			int windowY = xy[1];
			if (information[2] != 0)
				windowX = windowX % information[2];
			if (information[3] != 0)
				windowY = windowY % information[3];
			int finalX = 400 + windowX * 4 / 5 - windowY * 4 / 5;
			int finalY = 100 + windowX * 3 / 5 + windowY * 3 / 5;
			return new int[] { finalX, finalY };
		} else {
			return null;
		}
	}
}
