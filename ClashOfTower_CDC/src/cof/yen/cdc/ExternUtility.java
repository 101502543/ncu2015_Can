package cof.yen.cdc;

import java.util.ArrayList;

public class ExternUtility {
	private CDC cdc;
	private Parameter parameter = null;
	private SerialNumGenerator sng = null; 
	private PositionHelper ph = null;
	
	public ExternUtility(CDC cdc){
		this.cdc = cdc;
		this.parameter = Parameter.getInstance();
		this.sng = SerialNumGenerator.getInstance();
		this.ph = PositionHelper.getInstance();
	}
	
	public void addPlayer(int clientNo) {
		assert cdc.getCurPlayerNum() < parameter.MAX_PLAYER_NUM : "greater than "+parameter.MAX_PLAYER_NUM;
		ph.add(clientNo);
		cdc.addNewPlayerNum();
		cdc.pm.put(clientNo, new Player(clientNo, parameter.INIT_MONEY_UP_RATE, parameter.INIT_MONEY));
	}

	public void addUnit(String type, int level, int clientno, int aisle) {
		//cdc.pm.get(clientno).um.add(new Unit(type, direction, action, host, hp, atk, def, level, serialNumber, aisle, x, y, agi, breakValue))
	}

	public void addMessenger(int clientNo) {
		int initPos[] = ph.getTowerPos(clientNo);
		cdc.cc.put(clientNo, new Messenger(initPos[0], initPos[1], clientNo, sng.getNextSN()));
	}

	public void startGame() {	// 	還要實作
		//cdc.startUpdatingThread();
	}

	public void callCapitalCitySkill(int clientNo, String skillName) {
		cdc.cc.skill(clientNo, skillName);
	}

	public void callTowerSkill(int clientNo) {
		//cdc.pm.get(clientNo).tm.get(0);
	}

	public void callTowerUpgrade(int clientNo, String upgradeName) {
		TowerManager myTm = cdc.pm.get(clientNo).tm;
		if(!myTm.isEmpty())
			myTm.get(0).upgrade(upgradeName);
	}

	public ArrayList<String> getUpdateInfo() {
		return cdc.updateInfo;
	}
}
