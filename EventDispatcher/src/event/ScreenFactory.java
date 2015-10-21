package event;

import java.awt.Color;

public class ScreenFactory {
	public Screen[] createScreen(int amount, int[][] screenSize, Color[] screenColor){
		Screen[] product = new Screen[amount];
		
		for(int i=0; i<amount; i++){
			product[i] = new Screen(screenSize[i][0], screenSize[i][1], screenColor[i]);
			product[i].setName("screen "+ (i+1) );
			product[i].setLabelName(product[i].getName());
		}
		
		return product;
	}
}
