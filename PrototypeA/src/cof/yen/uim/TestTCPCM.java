package cof.yen.uim;

public class TestTCPCM {
	//void inputMoves(int MoveCode) ;
		// called by UIM or dynamic object module
		// recall that in this design, the client machine is treated as a input
		// processing machine. UIM processes input from keyboard and mouse
		// and then translate the event into a MoveCode and pass to TCP client
		// Currently, you only need to implement the following MoveCode
		//  TURNEAST   - (passed by UIM) the main character turn east
		//  TURNSOUTH - ..
		//  TURNNORTH
		//  TURNWEST
		//  GET  - (passed by dynamic object module) 
		//	         the virtual character is near an item and decide to grab it
		//	         DOM decide the grab action is possible 
		//	         and then send the movecode  ¡§GET¡¨ to TCP client
		// Once TCP client module receives a message, it wraps it into a message and
		// transmit to TCP server module via the established connection
	public static TestTCPCM myTcpcm= null;  
	
	public static TestTCPCM getInstance(){
		if(myTcpcm == null)
			myTcpcm = new TestTCPCM();
		return myTcpcm;
	}
	
	private String movecode[] = {"NONE", "TURNEAST", "TURNSOUTH", "TURNWEST", "TURNNORTH", "GET"}; 
	public void inputMoves(int MoveCode){
		assert (MoveCode > 5);
		System.out.println("¡iTCPCM¡j¡GinputMoves() is called, and the movecode is \""+movecode[MoveCode]+"\".");
	}
}
