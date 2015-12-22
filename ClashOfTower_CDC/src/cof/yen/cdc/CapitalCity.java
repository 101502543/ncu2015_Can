package cof.yen.cdc;

import java.util.HashMap;

public class CapitalCity extends HashMap<Integer, Messenger>{
	
	public CapitalCity() {		// 如果場上已經有使者，就不能再送addMsger指令過來  要在client判斷
		
	}
	
	public void skill(int clientNo, String skillName){		// 還要實作六種方法
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
