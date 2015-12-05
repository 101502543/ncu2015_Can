package cof.yen.cdc;

import java.util.ArrayList;

public class ItemManager {
	private ArrayList<Item> myItems;
	
	public ItemManager(){
		myItems = new ArrayList<>();
	}
	
	public void addItem(String name, int index, boolean shared,int x, int y){
		myItems.add(new Item(name, index, shared, x, y));
	}
	
	public ArrayList<Item> getAllItems(){
		return myItems;
	}
	
	public ArrayList<Item> getItems(String name){
		ArrayList<Item> result= new ArrayList<>();
		for(int i=0 ; i<myItems.size() ; i++){
			if(myItems.get(i).getName().equals(name)){
				result.add(myItems.get(i));
			}
		}
		return result;
	}
	
	public Item getItems(int index){
		for(int i=0 ; i<myItems.size() ; i++){
			if(myItems.get(i).getIndex() == index)
				return myItems.get(i);
		}
		return null;
	}
}
