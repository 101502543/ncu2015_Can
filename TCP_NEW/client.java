import java.io.*;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class client {
    static Socket socket_1;
	static int a = 0;
	public void client(){ }
	
	static String[] ans = {"A","B","C"};
	static boolean[] if_get = {false,false,false};
	static int sec[] = {0,0,0};
	
	static int temp_sec ;
	
	static int start[] = {0,0,0};
	static Timer timer = new Timer();
	static Timer new_timer = new Timer();
	static Socket echoSocket ;
	static PrintWriter out ;
	static BufferedReader in ;
	
	
	
	public static void main(String[] args)throws IOException {
		
		String host = "127.0.0.1";
		try{
			echoSocket = new Socket(host,3456);
			out = new PrintWriter(echoSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
		}
		catch(UnknownHostException e){
			System.err.println("Don't know hist named:" + host);
			System.exit(1);
		}
		catch(IOException e){
			System.err.println("couldn't get I/O for" + "the connection to:" + host);
			System.exit(1);
		}
		
		BufferedReader stdIn = new BufferedReader(
				new InputStreamReader(System.in));
		String userInput = null;
		
		Thread th1 = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					
					try {
						Thread.sleep(3000);
						System.out.print(ans[0] + " ");
						if(if_get[0] == true){
							System.out.println("YES " + (5-(temp_sec-start[0])));
						}else{
							System.out.println("NO ");
						}
						System.out.print(ans[1] + " ");
						if(if_get[1] == true){
							System.out.println("YES " + (5-(temp_sec-start[1])));
						}else{
							System.out.println("NO ");
						}
						System.out.print(ans[2] + " ");
						if(if_get[2] == true){
							System.out.println("YES " + (5-(temp_sec-start[2])));
						}else{
							System.out.println("NO ");
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		});
		th1.start();
		int index = 0;
			try{
				
				temp_sec = 0;
				while(true){
					System.out.println(temp_sec + " sec ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					if(if_get[index] == false){
						System.out.println("GET " + ans[index]);
						System.out.flush();
						userInput = "GET "+ans[index];
						out.println(userInput);
						String tt = in.readLine();
						System.out.println(tt);
						return_ans(tt,temp_sec);
					}
					
					index++;
					if(index%3 == 0){
						index = 0;
					}
					Thread.sleep(1000);	
					
					temp_sec++;
				}
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}	
		
		
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}//main
	
	
	public static void return_ans(String input,int time){
		if(input.split(" ")[0].equals("YES")){
			int temp = find(input.split(" ")[1]);
			if_get[temp] = true;
			start[temp] = time;
			timer.schedule(new tt(ans[temp],temp),5000);
			
		}
	}//return_ans
	
	public static int find(String s){
		int temp = 0;
		for(int i = 0;i<ans.length;i++){
			if(ans[i].equals(s)){
				temp = i;
			}
		}
		return temp;
	}
}

class tt extends TimerTask{
	
	Socket c_socket;
	PrintWriter out_p;
	String rel;
	BufferedReader inn;
	int index = 0;
	public tt(String s,int temp){
		this.rel = s;
		this.index = temp;
	}
	
	@Override
	public void run() {

		c_socket = client.echoSocket;
		out_p = client.out;
		inn = client.in;
		
		out_p.println("RELEASE " + rel);
		String tt;
		try {
			tt = inn.readLine();
			System.out.println(tt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		client.if_get[index] = false;
		client.sec[index] = 0;
		System.out.println("RELEASE " + rel);
		System.out.flush();
		
		
	}
	
}
