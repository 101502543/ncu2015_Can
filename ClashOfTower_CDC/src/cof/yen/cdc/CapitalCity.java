package cof.yen.cdc;

import java.util.HashMap;

public class CapitalCity extends HashMap<Integer, Messenger>{
	
	public CapitalCity() {		// �p�G���W�w�g���Ϫ̡A�N����A�eaddMsger���O�L��  �n�bclient�P�_
		
	}
	
	public void skill(int clientNo, String skillName){		// �٭n��@���ؤ�k
		switch (skillName) {
		case "castle1":
			break;

		case "castle2":
			
			break;
			
		case "castle3":
			
			break;
			
		case "church1":
			
			break;
			
		case "church2":
			
			break;
			
		case "church3":
			
			break;						
		default:
			assert false : "Undefined skill name.";
			break;
		}
	}
	
	
	public boolean isMessengerExist(int clientNo){
		if(this.get(clientNo) != null)
			return true;
		return false;
	}

	@Override
	public Messenger put(Integer key, Messenger value){
		if(!isMessengerExist(key))
			return super.put(key, value);
		else{
			assert false : "This client has already send a messenger.";
			return null;
		}
	} 
}
