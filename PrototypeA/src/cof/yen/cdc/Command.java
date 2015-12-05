package cof.yen.cdc;

public class Command {
	private String type;  // ADD or UPDATE
	private String content;
	
	public Command(String type, String content){
		assert (!type.equals("ADD") || !type.equals("UPDATE"));
		this.type = type;
		this.content = content;
	}
	
	public String toString(){
		String result = "¡iType¡j\n" + type + "\n¡iContent¡j\n"+content+"\n---------------------------\n"; 
		return result;		
	}
}
