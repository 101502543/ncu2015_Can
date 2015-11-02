import java.io.*;
import java.net.*;
import java.util.Timer;

public class server {
    
	public final static int DEFAULT_PORT = 3456;
	public final static int limit = 5;
	
	public static void main(String[] args) throws IOException{
		ServerSocket listen_Socket = null;
		OutputStreamWriter osw = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		int port_num = DEFAULT_PORT;
		int clientNO = 0;
		try{
			listen_Socket = new ServerSocket(port_num,limit);
			System.out.println("ServerSocket~~~");
		}catch(BindException e){
			System.out.println("shut down~~~");
			System.exit(1);
		}
		Timer timer = new Timer();
		timer.schedule(new data(),0,3000);
		while(true){
			System.out.println("in while true~~~");
			Socket clientSocket = null;
			clientSocket = listen_Socket.accept();
			++clientNO;
			
			new HandleClient(clientSocket,clientNO).start();
		}//while
	}//main
}
