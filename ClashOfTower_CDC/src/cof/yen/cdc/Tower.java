package cof.yen.cdc;

public class Tower {
	private String type;
	private int x, y, serialNumber, host, atk, range, hp;
	private CDC cdc;
	private Parameter parameter;
	
	/*public Tower() {
		cdc = CDC.getInstance();
		parameter = Parameter.getInstance();
	}*/

	public Tower(int x, int y, int serialNumber, int host, int atk, int range, int hp) {
		cdc = CDC.getInstance();
		parameter = Parameter.getInstance();
		
		this.type = parameter.TOWER_TYPE;
		this.x = x;
		this.y = y;
		this.serialNumber = serialNumber;
		this.host = host;
		this.atk = atk;
		this.range = range;
		this.hp = hp;		
	}
	
	public void upgrade(String upgradeName){
		switch(upgradeName){
		case "hpRecover":
			hpRecover();
			break;
		case "moneyUpFaster":
			moneyUpFaster();
			break;
		case "towerAtkUp":
			towerAtkUp();
			break;
		default:
			assert false : "There is no such upgrade name.";
		}
	}
	
	public void autoAddHp(){
		hp += parameter.AUTO_ADD_HP;
		if( hp > parameter.INIT_TOWER_HP )
			hp = parameter.INIT_TOWER_HP;		
	}
	
	public void hurt(int rivalAtk){
		hp = hp - rivalAtk;
		if( hp < 0 )
			hp = 0;
	}	
	
	public void changeHost(int newHost){
		cdc.pm.get(newHost).destroyOneTower();
		cdc.pm.get(host).tm.removeBySerialNum(this.serialNumber);
		this.host = newHost;
	}
	
	private void hpRecover(){
		hp += parameter.SKILL_HP_RECOVER;	
		if( hp > parameter.INIT_TOWER_HP )
			hp = parameter.INIT_TOWER_HP;
	}
	
	private void moneyUpFaster(){
		Player myHost = cdc.pm.get(host);
		myHost.moneyUpFaster();
	}
	
	private void towerAtkUp(){
		this.atk += parameter.SKILL_ATK_UP;
	}
/*---------------------------------------------------------------------------*/	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}	
}
