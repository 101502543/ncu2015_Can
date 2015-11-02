public class DataTask {
	static String ans[] = {"A","B","C"};
	static int answer[] = {0,0,0};
	public synchronized void set(String s,int tag){
		int temp = 0;
		temp = find(s);
		answer[temp] = tag;
		//display();
	}
	
	public String available(String s){
		int temp = 0;
		temp = find(s);
		if(answer[temp] != 0){
			return "NO";
		}
		else{
			return "YES";
		}
	}
	
	public void release(String s){
		int temp = 0;
		temp = find(s);
		answer[temp] = 0;
		//display();
	}
	
	
	public int find(String s){
		int temp = 0;
		for(int i = 0;i<3;i++){
			if(ans[i].equals(s)){
				temp = i;
			}
		}
		return temp;
	}
	
	public void display(){
		for(int i = 0;i<3;i++){
			System.out.print(answer[i] + " ");
		}
		System.out.println();
	}

}