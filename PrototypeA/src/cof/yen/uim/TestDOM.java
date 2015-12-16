package cof.yen.uim;

public class TestDOM {
	//void keyGETPressed() ;
	// called by UIM
	// When UIM accepts an keyboard input and it is a GET key
	// it calls this method.
	// This method should determine if the GET action is possible
	// by comparing the virtual character¡¦s position and any
	// item nearby. If the GET action is possible, it should call 
	// inputMoves(GET) of TCPCM 
	public static TestDOM myDom = null;
	
	public static TestDOM getInstance(){
		if(myDom == null)
			myDom = new TestDOM();
		return myDom;
	}
	
	public void keyGETPressed(){		
		System.out.println("¡iDOM¡j¡GkeyGETPressed() method is call by UIM.");
		TestTCPCM.getInstance().inputMoves(5);
		if(TestDriver.getInstance().x == TestDriver.getInstance().itemX && TestDriver.getInstance().y == TestDriver.getInstance().itemY)
			System.out.println("Character gets a item¡I¡I¡I");
	}
}
