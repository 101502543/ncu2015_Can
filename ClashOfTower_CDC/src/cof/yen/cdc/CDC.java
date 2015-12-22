package cof.yen.cdc;

import java.util.ArrayList;

import cof.server.interfaces.CentralDataCenter;

public class CDC implements CentralDataCenter{
	//public:
	public static CDC cdc = null;
	public PlayerManager pm = null;
	public CapitalCity cc = null; 
	public ArrayList<String> updateInfo = new ArrayList<String>(); 
	
	//private:
	private ExternUtility utility = null;
	private Algorithm alg = null;  
	private Thread updThread = null; 
	private int curPlayerNum = 0;
	
/*---------------------------------------------------------------------------*/
	public CDC(){
		pm = new PlayerManager();
		utility = new ExternUtility(this);
		alg = new Algorithm(this);
		cc  = new CapitalCity();
	}
	
	public static CDC getInstance(){
		if(cdc == null)
			cdc = new CDC();
		return cdc;
	}
	
	

	public void startUpdatingThread() {
		updThread = new Thread(new UpdateThread(this));
		updThread.start();
	}

	public ArrayList<EndScore> getEndScore() {
		return alg.getEndScore();
	}
	

/*---------------------------------------------------------------------------*/
	
	public int getCurPlayerNum() {
		return curPlayerNum;
	}

	public void addNewPlayerNum() {
		this.curPlayerNum++;
	}
	
/*---------------------------------------------------------------------------*/
	
	@Override
	public void addPlayer(int clientNo) {
		utility.addPlayer(clientNo);
	}

	@Override
	public void addUnit(String type, int level, int clientno, int aisle) {
		utility.addUnit(type, level, clientno, aisle);
	}
	@Override
	public void addMessenger(int clientNo) {
		utility.addMessenger(clientNo);
	}
	@Override
	public void startGame() {
		utility.startGame();
	}
	@Override
	public void callCapitalCitySkill(int clientNo, String skillName) {
		utility.callCapitalCitySkill(clientNo, skillName);
	}
	@Override
	public void callTowerSkill(int clientNo) {
		utility.callTowerSkill(clientNo);
	}
	@Override
	public void callTowerUpgrade(int clientNo, String updateName) {
		utility.callTowerUpgrade(clientNo, updateName);
	}
	@Override
	public ArrayList<String> getUpdateInfo() {
		return utility.getUpdateInfo();
	}
}
