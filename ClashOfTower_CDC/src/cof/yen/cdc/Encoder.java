package cof.yen.cdc;

import java.util.ArrayList;
import java.util.HashMap;


public class Encoder {
	private RemoveUnit removeUnit;
	private AddUnit addUnit;
	private SetUnitProperty setUnitProperty;
	private AddMessenger addMessenger;
	private RemoveMessenger removeMessenger;
	private SetMessengerProperty setMessengerProperty;
	private SentMessengerArriveMsg sentMessengerArriveMsg;
	private SetPlayerMoney setPlayerMoney;
	private SetTowerHp setTowerHp;
	private MakeTowerFiring makeTowerFiring;
	private SetGameTime setGameTime;
}

class InitTowerPosition{
	private HashMap<Integer, Integer[]> position;		// 也可以用int[4][3]

	public InitTowerPosition(HashMap<Integer, Integer[]> position) {
		this.position = position;
	}
}

class RemoveUnit{
	private int clientNo,serialNumber;
	
	public RemoveUnit(int clientNo, int serialNumber){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
	}
}
class AddUnit{
	private int clientNo,serialNumber,level,x,y;
	private String type,action,direction;
	
	public AddUnit(int clientNo,int serialNumber,String type,int level,int x,int y,String action,String direction){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.type = type;
		this.level = level;
		this.x = x;
		this.y = y;
		this.action = action;
		this.direction = direction;
	}
}
class SetUnitProperty{
	private int clientNo, serialNumber, x, y; 		// 若有數值為int -99999 或 String NO_CONFIGURE就是不設定此屬性 
	private String direction, action;
	
	public SetUnitProperty(int clientNo, int serialNumber, int x, int y,
			String direction, String action) {
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.action = action;
	}
}
class AddMessenger{
	private int serialNumber,x,y;
	
	public AddMessenger(int serialNumber, int x, int y){
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
	}
}
class RemoveMessenger{
	private int serialNumber;
	
	public RemoveMessenger(int serialNumber){
		this.serialNumber = serialNumber;
	}
}
class SetMessengerProperty{			// 若有任何一個數值為-999999(NO_CONFIGURE_INT)  代表不設定這個屬性
	private int clientNo, serialNumber, x, y;

	public SetMessengerProperty(int clientNo, int serialNumber, int x, int y) {
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
	}
}
class SentMessengerArriveMsg{
	private int clientNo;
	
	public SentMessengerArriveMsg(int clientNo){
		this.clientNo = clientNo;
	}
}

class sentEndScore{
	private ArrayList<EndScore> endScore;

	public sentEndScore(ArrayList<EndScore> endScore) {
		this.endScore = endScore;
	}
}

class SetPlayerMoney{
	private int clientNo,money;
	
	public SetPlayerMoney(int clientNo, int money){
		this.clientNo = clientNo;
		this.money = money;
	}
}
class SetTowerHp{
	private int clientNo, serialNumber, hp;
	
	public SetTowerHp(int clientNo, int serialNumber, int hp){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.hp = hp;
	}
}
class MakeTowerFiring{
	private int x,y;
	
	public MakeTowerFiring(int x , int y){
		this.x = x;
		this.y = y;
	}
}
class SetGameTime{
	private int time;
	
	public SetGameTime(int time){
		this.time = time; 
	}
}