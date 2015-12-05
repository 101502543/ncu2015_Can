package cof.yen.cdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DataCenter {
	final int DIMENSION = 2;
	final int MAX_PLAYER = 4;
	//final int MAP_SIZE = XXX;
	//final int MAP_MAX_EAST = XXX;
	//final int MAP_MAX_NORTH = XXX;
	//final int MAP_MAX_SOUTH = XXX;
	//final int MAP_MAX_WEST = XXX;	
	final int MAX_CHARACTER_VELOCITY = 10;	
	final int[] DIRECTION = {1, 2, 3, 4};
	
	private int curClientNum = 0;
	private int curItemNum = 0;
	private final int[][] initCharacterPos = {{1, 1}, {2, 2}, {3, 3}, {4, 4}};
	private int[][] movement = {{0, 0}, {1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	public Map<Integer, Character> myCharacters;
	private ItemManager myItemManager;
	private Vector<Command> updateInfo;
		
	public DataCenter(){
		myCharacters = new HashMap<>();
		myItemManager = new ItemManager();
		updateInfo = new Vector<Command>();
	}
	
	private boolean isCharacterExist(int clientno){
		for(int key:myCharacters.keySet()){
			if(key == clientno){
				return true;
			}
		}	
		return false;
	}
	
	public void addVirtualCharacter(int clientno){
		if(curClientNum >= MAX_PLAYER){
			System.out.println("Client "+clientno+" isn't allowed to join the game, coz the player number is over "+MAX_PLAYER+"...");
			return;
		}
		if(isCharacterExist(clientno)){
			System.out.println("The ClientNo "+clientno+" has already been in the game.");
			return;
		}	
		myCharacters.put(clientno, new Character(initCharacterPos[curClientNum]));
		updateInfo.add(new Command("ADD", myCharacters.get(clientno).getAllProperty()));
		System.out.println("Client "+clientno+" joins the game.");
		curClientNum ++ ;		
	}
	
	public void addItem(String name, int index, boolean shared,int x, int y){
		if(myItemManager.getItems(index) != null){
			System.out.println("The index("+index+") of the item has already been used.");
			return;
		}
		myItemManager.addItem(name, index, shared, x, y);
		updateInfo.add(new Command("ADD", myItemManager.getItems(index).getAllProperty()));
		System.out.println("The item"+index+"("+name+") is added to the map.");
		curItemNum++;
	}

	
	/*
     TURNEAST   1
	 TURNSOUTH  2
	 TURNWEST   3
	 TURNNORTH  4
	 */
	public void updateDirection(int clientno, int MoveCode) {
		if(!isCharacterExist(clientno)){
			System.out.println("The ClientNo "+clientno+" isn't in the game.");
			return;
		}	
		if(MoveCode>4 || MoveCode<0){
			System.out.println("The MoveCode "+MoveCode+" is undefined.");
			return;
		}
		myCharacters.get(clientno).setDirection(MoveCode);
		updateInfo.add(new Command("UPDATE", "ClientNo¡G"+clientno+"\nDirection¡G"+myCharacters.get(clientno).getDirection()));
		System.out.println("Client "+clientno+" change his direction to the movecode "+MoveCode+".");
	}

	public void getItem(int clientno){
		boolean isGetItem = false;
		int posX = myCharacters.get(clientno).getPosition()[0];
		int posY = myCharacters.get(clientno).getPosition()[1];
		ArrayList<Item> allItems = myItemManager.getAllItems();
		for(int i=0 ; i<allItems.size() ; i++){
			Item thisItem = allItems.get(i);
			if((thisItem.getPosX() == posX) && (thisItem.getPosY() == posY)){
				if(thisItem.getShared()==true){
					if(thisItem.getOwnClient() == -1){
						isGetItem = true;
						thisItem.setOwnClient(clientno);
						System.out.println("Client "+clientno+" gets item"+thisItem.getIndex()+"("+thisItem.getName()+").");
						updateInfo.add(new Command("UPDATE", "ItemNo¡G"+thisItem.getIndex()+"\nOwner¡G"+thisItem.getOwnClient()));
					}else{
						//System.out.println("This item is already been taken by client"+thisItem.getOwnClient()+".");
					}
				}else{
					isGetItem = true;
					thisItem.setOwnClient(clientno);
					System.out.println("Client " + clientno +" gets item"+thisItem.getIndex()+"("+thisItem.getName()+").");
					System.out.println("And, the item"+thisItem.getIndex()+"("+thisItem.getName()+") reapeared again.");
					updateInfo.add(new Command("UPDATE", "ItemNo¡G"+thisItem.getIndex()+"\nOwner¡G"+thisItem.getOwnClient()));
				}
			}
		}
		if(!isGetItem)
			System.out.println("There is no item at position ("+posX +", "+ posY+").");
	}
	
	public Vector<Command> getUpdateInfo(){
		Vector<Command> result = (Vector<Command>) updateInfo.clone();
		updateInfo.clear();
		return result;		
	}
	
	/*
	 NONE		0
    TURNEAST   1
	 TURNSOUTH  2
	 TURNWEST   3
	 TURNNORTH  4
	 */
	public void autoMovePosition(){
		for(int key : myCharacters.keySet()){
			Character thisCharacter = myCharacters.get(key);
			int laterPosX = thisCharacter.getPosition()[0] + movement[thisCharacter.getDirection()][0] * thisCharacter.getVelocity();
			int laterPosY = thisCharacter.getPosition()[1] + movement[thisCharacter.getDirection()][1] * thisCharacter.getVelocity();
			thisCharacter.setPosition( laterPosX, laterPosY );
			System.out.println("Client "+key+" change his position to ("+laterPosX+", "+laterPosY+").");
			updateInfo.add(new Command("UPDATE", "ClientNo¡G"+key+"\nPosition¡G("+laterPosX+", "+laterPosY+")"));
		}
		System.out.println("---------------------------");
	}

	public void startUpdatingThread(){
		new Thread(new UpdateThread(this)).start();		
	}	
}
