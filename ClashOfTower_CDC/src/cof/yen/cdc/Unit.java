package cof.yen.cdc;

public class Unit {
	private String type, direction, action;
	private int  host, hp, atk, def, level, serialNumber, aisle, x, y, agi, breakValue; 
	
	/*public Unit(){
		// Do nothing
	}*/
	
	public Unit(String type, String direction, String action, int host, int hp,	int atk, int def, int level,
				int serialNumber, int aisle, int x,	int y, int agi, int breakValue) {
		this.type = type;
		this.direction = direction;
		this.action = action;
		this.host = host;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.level = level;
		this.serialNumber = serialNumber;
		this.aisle = aisle;
		this.x = x;
		this.y = y;
		this.agi = agi;
		this.breakValue = def;
		// coordinate 那邊的vector要加入這個unit
	}

	public void hurt(int rivalAtk){
		hp = hp - rivalAtk;
		breakValue = breakValue - rivalAtk;
		
		if(hp < 0)
			hp = 0;
		if( breakValue < 0 )
			breakValue = 0;
	}
	
	public boolean isBreak(){
		if(breakValue <= 0)
			return true;
		return false;
	}
	
	public void goBreaked(){
		breakValue = def;
	}
		
	public boolean testCollision(){			//	還沒實作
		return false;
	}

/*---------------------------------------------------------------------------*/	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
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

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public int getBreakValue() {
		return breakValue;
	}

	public void setBreakValue(int breakValue) {
		this.breakValue = breakValue;
	}
}
