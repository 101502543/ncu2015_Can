package cof.yen.cdc;

public class Parameter {
	public static Parameter parameter = null;
	
	
	// Game
	final int MAX_PLAYER_NUM = 4;
	final int MAX_GAME_TIME = 20 * 60; //　(sec)....   20minutes
	final int UPDATE_MONEY_INTERVAL = 1;
	final int UPDATE_HP_INTERVAL = 1;
	final int END_SCORE_TOWER = 1000;
	
	
	
	// Coordinate
	final int MAP_SIZE = 5000 * 5000 ;	
	
	
	// Player
	final int INIT_MONEY = 1000;
	final int INIT_MONEY_UP_RATE = 15;
	final int MAX_HERO_NUM = 1;
	final int MAX_UNIT_NUM = 30;
	
		
	
	// Unit
	final int[] INIT_WARRIOR_HP = 	{-999, 50, 	70,  100}; 		// 3種等級   等級1~3   所以index0的地方不給初始值
	final int[] INIT_WARRIOR_ATK = 	{-999, 10, 	20,  30	}; 		
	final int[] INIT_WARRIOR_DEF = 	{-999, 10, 	20,	 30	}; 		
	final int[] INIT_WARRIOR_AGI = 	{-999, 20, 	20,	 15	}; 		
	final int[] INIT_WARRIOR_COST = {-999, 100, 150, 200}; 		
	final int[] INIT_WOLF_HP 	= {-999, 30,  50,	 50};
	final int[] INIT_WOLF_ATK 	= {-999, 10,  15,	 15};
	final int[] INIT_WOLF_DEF 	= {-999, 10,  15,	 15};
	final int[] INIT_WOLF_AGI 	= {-999, 30,  40,	 40};
	final int[] INIT_WOLF_COST 	= {-999, 100, 150,  250};	
	final int[] INIT_ORK_HP =	{-999, 80,	80,	 100};
	final int[] INIT_ORK_ATK = 	{-999,  5,	20,	 40	};
	final int[] INIT_ORK_DEF = 	{-999, 15,	30,	 40	};
	final int[] INIT_ORK_AGI = 	{-999, 10,	 5,	 25	};
	final int[] INIT_ORK_COST = {-999, 150,	200, 350};	
	final int[] INIT_HERO_HP = 	{200, 180, 	150,  180};		//4種英雄  但是沒有等級之分
	final int[] INIT_HERO_ATK = {50,   60, 	 60,   55};		// Hero 0~3   直接切割最後一個char來判斷要取哪一個初始值
	final int[] INIT_HERO_DEF = {56,   50, 	 35,   30};		// 若type是Hero的話  直接拿level這個屬性來記錄是選0~3之中的哪個英雄
	final int[] INIT_HERO_AGI = { 8, 	5, 	  7,    5};	
	final int[] INIT_HERO_COST= {600, 600, 	600,  600};	
	//final int[][] INIT_UNIT_POS = { 	{0,  0}, {0, 1},	// 4 player, 2 direction, 2 dimension pos
	//									{5,  5}, {5, 6},	// 要再改
	//									{1,  1}, {1, 2},
	//									{8,  8}, {8, 9}	}; 
	final int UNIT_MAX_LEVEL = 3;
	final String UNIT_TYPE_WARRIOS = "Warrior" ;
	final String UNIT_TYPE_WOLF = "Wolf" ;
	final String UNIT_TYPE_Ork = "Ork" ;
	final String[] UNIT_TYPE_HERO = {"Hero0", "Hero1", "Hero2", "Hero3"};
	final String UNIT_DIRECTION_RIGHT = "right"; 
	final String UNIT_DIRECTION_LEFT = "left"; 
	final String UNIT_ACTION_WALK = "walk";
	final String UNIT_ACTION_ATTACK = "attack";
	final String UNIT_ACTION_ATTACKTOWER = "attackTower";
	
	
	
	// Tower
	final int[][] INIT_TOWER_POS= {{0, 0}, {1, 1}, {2 ,2}, {3, 3}};   // 要改 暫時的 
	final int INIT_TOWER_HP = 1000;
	final int INIT_TOWER_RANGE = 20;
	final int INIT_TOWER_ATK = 30;
	final int AUTO_ADD_HP = 10;
	final int SKILL_MONEY_UP_FASTER = 5; 
	final int SKILL_HP_RECOVER = 500;
	final int SKILL_ATK_UP = 15;
	final String TOWER_TYPE = "Tower";
	
	
	
	// Capital City
	//final int[][] INIT_MSGER_POS = {{1, 1}, {2, 2}, {3, 3}, {4, 4}}; 
	final String CAPITAL_SKILL_CASTLE1 = "castle1";
	final String CAPITAL_SKILL_CASTLE2 = "castle2";
	final String CAPITAL_SKILL_CASTLE3 = "castle3";
	final String CAPITAL_SKILL_CHURCH1 = "church1";
	final String CAPITAL_SKILL_CHURCH2 = "church2";
	final String CAPITAL_SKILL_CHURCH3 = "church3";
	
	
	
	// Encoder
	final int NO_CONFIGURE_INT = -99999;
	final String NO_CONFIGURE = "NoConfigure";
	
	
	
	
	public static Parameter getInstance(){
		if(parameter == null)
			parameter = new Parameter();
		return parameter;
	}
}
