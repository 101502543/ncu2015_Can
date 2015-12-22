package cof.yen.cdc;

import java.util.HashMap;

public class PlayerManager extends HashMap<Integer, Player>{

	public PlayerManager() {
		
	}

	public boolean isPlayerExist(int clientNo){
		if(this.get(clientNo) != null)
			return true;
		return false;
	}

	@Override
	public Player put(Integer key, Player value){
		if(!isPlayerExist(key))
			return super.put(key, value);
		else{
			assert false : "This client"+ key +" has already in the game.";
			return null;
		}
	} 
}
