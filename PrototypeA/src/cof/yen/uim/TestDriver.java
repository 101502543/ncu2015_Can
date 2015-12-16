package cof.yen.uim;

public class TestDriver {
	public int x, y; 
	public int itemX, itemY;
	public static TestDriver myDriver = null;
	
	
	public static TestDriver getInstance(){
		if(myDriver == null)
			myDriver = new TestDriver();
		return myDriver;
	}
	
	public TestDriver(){
		this.x = 0;
		this.y = 0;
		this.itemX = 3;
		this.itemY = 3;
		printPosition();
		System.out.println("There is a item at position (3, 3).");
	} 
	
	public void moveNorth(){
		TestTCPCM.getInstance().inputMoves(4);
		y++;
		printPosition();
	}
	
	public void moveEast(){
		TestTCPCM.getInstance().inputMoves(1);
		x++;
		printPosition();
	}
	
	public void moveSouth(){
		TestTCPCM.getInstance().inputMoves(2);
		y--;
		printPosition();
	}
	
	public void moveWest(){
		TestTCPCM.getInstance().inputMoves(3);
		x--;
		printPosition();
	}
	
	public void grab(){
		System.out.println("Character wants to grab something.");
		TestDOM.getInstance().keyGETPressed();
	}
	
	public void printPosition(){
		System.out.println("Character is now at position ("+x+", "+y+").");
	}
}
