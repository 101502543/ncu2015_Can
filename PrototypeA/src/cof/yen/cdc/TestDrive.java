package cof.yen.cdc;

import java.util.Vector;

public class TestDrive {
	public static void main(String args[]){
		DataCenter dc = new DataCenter();
		Vector<Command> updateInfo;

		System.out.println("¡iSenario¡j¡GHere comes a new client 1");
		dc.addVirtualCharacter(1);
		dc.myCharacters.get(1).setVelocity(1);
		System.out.println("¡iSenario¡j¡GHere comes a new client 2");
		dc.addVirtualCharacter(2);
		dc.myCharacters.get(2).setVelocity(2);
		System.out.println("¡iSenario¡j¡GHere comes a new client 3");
		dc.addVirtualCharacter(3);
		dc.myCharacters.get(3).setVelocity(3);
		System.out.println("¡iSenario¡j¡GThe client 3 wants to join the game again.");
		dc.addVirtualCharacter(3);
		System.out.println("¡iSenario¡j¡GHere comes a new client 4");
		dc.addVirtualCharacter(4);
		dc.myCharacters.get(4).setVelocity(4);
		System.out.println("¡iSenario¡j¡GHere comes a new client 5, but there has already been 4 players so far. ");
		dc.addVirtualCharacter(5);
		System.out.println();
		

		System.out.println("¡iSenario¡j¡GAdd a new item1001(ºj) to the map, and the position of the item is (0, 5).");
		dc.addItem("ºj", 1001, true  , 0, 5);		
		System.out.println("¡iSenario¡j¡GAdd a new item1007(´Ò) to the map, and the position of the item is (0, 5).");
		dc.addItem("´Ò", 1007, true  , 0, 5);
		System.out.println("¡iSenario¡j¡GAdd a new item1002(¤M) to the map, and the position of the item is (3, 2).");
		dc.addItem("¤M", 1002, false , 3, 2);
		System.out.println("¡iSenario¡j¡GAdd a new item1002(¤M) to the map, and the position of the item is (7, 7).");
		dc.addItem("¤M", 1002, false , 7, 7);
		System.out.println("¡iSenario¡j¡GOK... add a new item(¤M) with another index(1005) to the map.");
		dc.addItem("¤M", 1005, false , 7, 7);
		System.out.println();
		

		System.out.println("¡iSenario¡j¡GClient 1 changes his direction to movecode 4.");
		dc.updateDirection(1, 4);
		System.out.println("¡iSenario¡j¡GClient 2 changes his direction to movecode 2.");
		dc.updateDirection(2, 2);
		System.out.println("¡iSenario¡j¡GClient 3 changes his direction to movecode 95.");
		dc.updateDirection(3, 95);
		System.out.println("¡iSenario¡j¡GClient 3 changes his direction to movecode 1.");
		dc.updateDirection(3, 1);
		System.out.println("¡iSenario¡j¡GClient 10 changes his direction to movecode 2.");
		dc.updateDirection(10, 2);
		System.out.println("¡iSenario¡j¡GClient 4 changes his direction to movecode 2.");
		dc.updateDirection(4, 2);
		System.out.println();

		System.out.println("¡iSenario¡j¡GClient 1 is at position (1,1), and he wants to pick up something.");
		dc.getItem(1);
		System.out.println("¡iSenario¡j¡GClient 1 moves to (0, 5), and he wants to pick up something.");
		dc.myCharacters.get(1).setPosition(0, 5);
		dc.getItem(1);
		System.out.println("¡iSenario¡j¡GClient 2 moves to (0, 5) too, and he wants to pick up something.");
		dc.myCharacters.get(2).setPosition(0, 5);
		dc.getItem(2);
		System.out.println("¡iSenario¡j¡GClient 1 moves to (3, 2), and he wants to pick up something.");
		dc.myCharacters.get(1).setPosition(3, 2);
		dc.getItem(1);
		System.out.println("¡iSenario¡j¡GClient 2 moves to (3, 2) too, and he wants to pick up something.");
		dc.myCharacters.get(2).setPosition(3, 2);
		dc.getItem(2);
		System.out.println();
		
		System.out.println("¡iUDPBC¡j¡GGet all the references to the dynamic objects which has just been updated recently.");
		updateInfo = dc.getUpdateInfo();
		for(int i=0 ; i<updateInfo.size() ; i++)
			System.out.print(updateInfo.get(i).toString());
		System.out.println();
		

		System.out.println("¡iTCPSM¡j¡GCall startUpdateThread() method to start thread to update each virtual character¡¦s x,y");
		dc.startUpdatingThread();
		
		
		/*System.out.println("¡iUDPBC¡j¡GGet all the references to the dynamic objects which has just been updated recently.");
		updateInfo = dc.getUpdateInfo();
		for(int i=0 ; i<updateInfo.size() ; i++)
			System.out.print(updateInfo.get(i).toString());*/
		
	}
}
