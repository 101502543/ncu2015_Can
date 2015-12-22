package cof.yen.cdc;

public class Player {
	private int clientNo,  moneyUpRate, money, towerDestroyNum, deadUnitNum, heroQuantity, unitQuantity;
	private Parameter parameter;
	private PositionHelper ph = null;
	private SerialNumGenerator sng = null;
	public UnitManager um = null;
	public TowerManager tm = null;
	
		
	/*public Player() {
		um = new UnitManager();
		tm = new TowerManager();
	}*/
	
	public Player(int clientNo, int moneyUpRate, int money) {
		parameter = Parameter.getInstance();
		ph = PositionHelper.getInstance();
		sng = SerialNumGenerator.getInstance();
		um = new UnitManager();
		tm = new TowerManager();
		
		this.clientNo = clientNo;
		this.moneyUpRate = moneyUpRate;
		this.money = money;
		this.towerDestroyNum = 0;
		this.deadUnitNum = 0;
		this.heroQuantity = 0;
		this.unitQuantity = 0;		
		
		initTower();
	}
	
	private void initTower(){
		int initPos[] = ph.getTowerPos(this.clientNo);
		Tower myTower = new Tower(initPos[0], initPos[1], sng.getNextSN(), this.clientNo, parameter.INIT_TOWER_ATK, parameter.INIT_TOWER_RANGE, parameter.INIT_TOWER_HP);
		this.tm.add(myTower);
	}
	
	public void destroyOneTower(){
		this.towerDestroyNum++;
	}
	
	public void addOneUnit(){
		assert (unitQuantity < parameter.MAX_UNIT_NUM) : "### Player ERROR： unitQuantity is over limit. ###"; 
		if(unitQuantity >= parameter.MAX_UNIT_NUM){
			System.out.println("### Player ERROR： unitQuantity is over limit. ###");
			return;
		}			
		unitQuantity++;
	}
	
	public void removeOneUnit(){
		unitQuantity--;
		deadUnitNum++;
		assert unitQuantity>=0;
		if(unitQuantity < 0)
			unitQuantity = 0;
	}
	
	public void addOneHero(){
		assert (unitQuantity < parameter.MAX_UNIT_NUM && heroQuantity < parameter.MAX_HERO_NUM) : "### Player ERROR： unitQuantity or heroQuantity is over limit. ###"; 
		if(unitQuantity >= parameter.MAX_UNIT_NUM || heroQuantity >= parameter.MAX_HERO_NUM){
			System.out.println("### Player ERROR： unitQuantity or heroQuantity is over limit. ###");
			return;
		}			
		heroQuantity++;
		unitQuantity++;
	}

	public void removeOneHero(){
		heroQuantity--;
		unitQuantity--;
		deadUnitNum++;
		assert unitQuantity>=0 && heroQuantity>=0;
		if(unitQuantity < 0)
			unitQuantity = 0;
		if(heroQuantity < 0)
			heroQuantity = 0;
	}
	
	public void autoAddMoney(){		// CDC 的 updateThread每單位時間 會呼叫這個方法
		money += moneyUpRate;		// 每個單位時間  玩家會新增moneyUpRate這個數量的金錢   故moneyUpRate越大意謂金錢增加越快
	}

	public void moneyUpFaster(){
		moneyUpRate += Parameter.getInstance().SKILL_MONEY_UP_FASTER;			//	呼叫Tower的moneyUpFaster後  就會呼叫這個方法，所以每單位時間所增加的金錢會增加
	}
	

/*---------------------------------------------------------------------------*/	
	
	public int getMoneyUpRate() {
		return moneyUpRate;
	}

	public void setMoneyUpRate(int moneyUpRate) {
		this.moneyUpRate = moneyUpRate;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public int getDeadUnitNum() {
		return deadUnitNum;
	}

	public void setDeadUnitNum(int deadUnitNum) {
		this.deadUnitNum = deadUnitNum;
	}

	public int getHeroQuantity() {
		return heroQuantity;
	}

	public void setHeroQuantity(int heroQuantity) {
		this.heroQuantity = heroQuantity;
	}

	public int getUnitQuantity() {
		return unitQuantity;
	}

	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
	
	public int getTowerDestroyNum() {
		return towerDestroyNum;
	}

	public void setTowerDestroyNum(int towerDestroyNum) {
		this.towerDestroyNum = towerDestroyNum;
	}


}
