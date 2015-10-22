import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main extends JFrame {

	private int frameSizeX = 500, frameSizeY = 300;
	private int imageWidth = 132, imageHigh = 100;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Main main = new Main();
			}
		});
	}

	public Main() {
		// TODO Auto-generated constructor stub
		ContentPanel cp = new ContentPanel();
		add(cp);
		cp.setFocusable(true);

		setTitle("Clash of Tower"); // 設定標題
		setSize(frameSizeX, frameSizeY); // 設定大小
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定右上角關閉鍵結束程式
		setVisible(true);
	}

}
