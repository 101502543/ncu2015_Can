import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main extends JFrame {
	private BasicBlock[][] scene;
	private int X = 2500, Y = 1000;

	private int[] nextColumn = { 48, 32 }, nextRow = { -48, 32 };
	private int frameSizeX = 500, frameSizeY = 300;
	private int imageWidth = 132, imageHigh = 100;
	private int zeroX = (frameSizeX - 7 * 2) / 2 + 7 - imageWidth,
			zeroY = 30 - 3 * imageHigh;
	private int LcornerX, LcornerY;

	public static void main(String[] args) {
		Main main = new Main();
	}

	public Main() {
		// TODO Auto-generated constructor stub
		setMap();
		setTitle("Clash of Tower"); // 設定標題
		setSize(frameSizeX, frameSizeY); // 設定大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定右上角關閉鍵結束程式
		setVisible(true);
		addKeyListener(new KeyListener() {

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
					Y -= 25;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					zeroY += 25;
					Y += 25;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					zeroX -= 25;
					X -= 25;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					zeroX += 25;
					X += 25;
				}
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// 先畫column再畫row
		super.paint(g);
		getWindow();
		int theX = zeroX, theY = zeroY;
		for (int i = LcornerX; i < LcornerX + 15; i++) {
			for (int j = LcornerY; j < LcornerY + 15; j++) {
				ImageIcon test = new ImageIcon(scene[j][i].getType());
				Image img = test.getImage();
				g.drawImage(img, 
						theX + (j - LcornerY) * nextColumn[0], 
						theY + (j - LcornerY) * nextColumn[1],
						100,
						100,
						this);
			} 
			theX = zeroX + (i - LcornerX) * nextRow[0];
			theY = zeroY + (i - LcornerX) * nextRow[1];
		}

	}

	private void getWindow() {
		int indexX = X / 100;
		int indexY = Y / 100;
//		if (X - indexX * 100 == 0) {
//			zeroX = (frameSizeX - 7 * 2) / 2 + 7 - imageWidth;
//		}
//		if (Y - indexY * 100 == 0) {
//			zeroY = 30 - 3 * imageHigh;
//		}
		LcornerX = indexX - 7;
		LcornerY = indexY - 7;
	}

	private void setMap() {
		scene = new BasicBlock[20][50];
		for (int y = 0; y < scene.length; y++) {
			for (int x = 0; x < scene[0].length; x++) {
				if (x == 25 && y == 10) {
					scene[y][x] = new BasicBlock(0);
					continue;
				}
				scene[y][x] = new BasicBlock((int) (Math.random() * 2));
			}
		}
	}

}
