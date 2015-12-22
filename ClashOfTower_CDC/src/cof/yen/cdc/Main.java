package cof.yen.cdc;

import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {		// ¥ýnew CDC ¡÷ new TCP ¡÷ new UDP

		CDC cdc = CDC.getInstance();
		
		
		
		/*
		cdc.addPlayer(26);
		cdc.addPlayer(33);
		cdc.addPlayer(7);
		int x = cdc.pm.get(26).tm.get(0).getX();
		int y = cdc.pm.get(26).tm.get(0).getY();
		System.out.println(x +" "+ y);
		x = cdc.pm.get(33).tm.get(0).getX();
		y = cdc.pm.get(33).tm.get(0).getY();
		System.out.println(x +" "+ y);
		x = cdc.pm.get(7).tm.get(0).getX();
		y = cdc.pm.get(7).tm.get(0).getY();
		System.out.println(x +" "+ y);
		*/
		
				
		/*
		cdc.addPlayer(0);
		cdc.addPlayer(1);
		cdc.addPlayer(2);
		cdc.addPlayer(3);
		System.out.println(PositionHelper.getInstance().getTowerPos(0)[0] + " " +PositionHelper.getInstance().getTowerPos(0)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(1)[0] + " " +PositionHelper.getInstance().getTowerPos(1)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(2)[0] + " " +PositionHelper.getInstance().getTowerPos(2)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(3)[0] + " " +PositionHelper.getInstance().getTowerPos(3)[1] );
		*/
		
		
		/*
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		*/
		
		
		/*	CDC cdc = CDC.getInstance();
		cdc.addPlayer(0);
		cdc.addPlayer(1);
		cdc.addPlayer(2);
		cdc.addPlayer(3);
		System.out.println(cdc.getCurPlayerNum());*/
		
		
	/*	CDC cdc = CDC.getInstance();
		for(int i=0;i<5;i++){
			int[] result = cdc.alg.getNextTowerPos();
			System.out.println(result[0] +" "+ result[1]);
		}*/
		
		
		/*CDC cdc = CDC.getInstance();
		PlayerManager pm = cdc.pm;
		pm.put(0, new Player(0, 10, 1000));
		pm.put(1, new Player(1, 10, 1000));
		pm.put(2, new Player(2, 10, 1000));
		pm.put(3, new Player(3, 10, 1000));		
		pm.get(0).tm.add(new Tower(0, 0, 1000, 0, 0, 0, 0));
		pm.get(1).tm.add(new Tower(0, 0, 1001, 1, 0, 0, 0));
		pm.get(2).tm.add(new Tower(0, 0, 1002, 2, 0, 0, 0));
		pm.get(3).tm.add(new Tower(0, 0, 1003, 3, 0, 0, 0));		
		pm.get(0).setDeadUnitNum(15);;
		pm.get(1).setDeadUnitNum(100);;
		pm.get(2).setDeadUnitNum(32);;
		pm.get(3).setDeadUnitNum(99);;	
		pm.get(1).tm.get(0).changeHost(3);;
		pm.get(2).tm.get(0).changeHost(3);
		pm.get(3).tm.get(0).changeHost(0);		
		ArrayList<EndScore> arrayList = cdc.getEndScore();
		for(int i=0;i<arrayList.size();i++)
			System.out.println(arrayList.get(i).getClientNo() +": "+arrayList.get(i).getScore());*/
		
		
		/*PlayerManager pm = new PlayerManager();
		pm.put(0, new Player(0, 10, 1000));
		pm.remove(0);
		System.out.println(pm.size());
		System.out.println(pm.get(0).getClientNo());*/
		
		
		/*CapitalCity a = new CapitalCity();
		a.put(0, new Messenger(0, 0, 0, 1001));
		System.out.println(a.size());
		a.remove(0);
		System.out.println(a.size());
		System.out.println(a.get(0));*/
		
		
/*		CDC cdc = CDC.getInstance();
		Tower t = new Tower();
		cdc.pm.put(0, new Player(0, 5, 1000));
		cdc.pm.put(1, new Player(1, 5, 1000));
		cdc.pm.get(0).tm.add(t);
		System.out.println(t);
		System.out.println(t.getHost());
		System.out.println(cdc.pm.get(0).tm.size());
		System.out.println(cdc.pm.get(1).tm.size());
		System.out.println(cdc.pm.get(0).tm.get(0));
		t.changeHost(1);
		System.out.println(t);
		System.out.println(t.getHost());
		System.out.println(cdc.pm.get(0).tm.size());
		System.out.println(cdc.pm.get(1).tm.size());
		System.out.println(cdc.pm.get(1).tm.get(0));*/
		
	}
}