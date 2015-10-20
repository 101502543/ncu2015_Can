import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class ContentPanel extends JPanel{

	private BasicBlock[][] scene;
	private int X = 2500, Y = 1000;
	private boolean isbound;
	private int[] nextColumn = { 48, 32 }, nextRow = { -48, 32 };
	private int frameSizeX = 500, frameSizeY = 300;
	private int imageWidth = 132, imageHigh = 100;
	private int zeroX = (frameSizeX - 7 * 2) / 2 + 7 - imageWidth,
			zeroY = 30 - 3 * imageHigh;
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
				int oldX = X, oldY = Y;
				int go = 0;
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					go = 1;
					// zeroY -= 32;
					X += 100;
					Y += 100;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					go = 2;
					// zeroY += 25;
					X -= 100;
					Y -= 100;
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					go = 3;
					// zeroX -= 25;
					X -= 100;
					Y += 100;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					go = 4;
					// zeroX += 25;
					X += 100;
					Y -= 100;
				}
				getWindow();
				if (isbound) {
					isbound = false;
					System.out.println("Is bound!");
					X = oldX;
					Y = oldY;
				}
				getWindow();
				repaint();
			}
		});
	}
	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		super.update(g);
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		// ���ecolumn�A�erow
		super.paintComponent(g);
		getWindow();
		int theX = zeroX, theY = zeroY;
		for (int i = LcornerX; i < LcornerX + 15; i++) {
			for (int j = LcornerY; j < LcornerY + 15; j++) {
				System.out.println(i + " " + j);
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
		LcornerX = indexX - 7;
		LcornerY = indexY - 7;
		if (LcornerX < 0 || LcornerY < 0 || indexX + 7 >= 50 || indexY + 7 >= 20) {
			isbound = true;
		}
	}

	private void setMap() {
		scene = new BasicBlock[20][50];
		for (int y = 0; y < scene.length; y++) {
			for (int x = 0; x < scene[0].length; x++) {
				if (x == 49 || y == 19 || x == 0 || y == 0) {
					scene[y][x] = new BasicBlock(1);
				}else {
					scene[y][x] = new BasicBlock(0);
				}
//				scene[y][x] = new BasicBlock((int) (Math.random() * 2));
			}
		}
	}
}
