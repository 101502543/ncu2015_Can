//FreeGeng since 2015/10/9
//encoding:UTF-8
import java.util.Scanner;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.support.igd.PortMappingListener;
import org.fourthline.cling.support.model.PortMapping;

public class UDP_client extends Thread {

	int port; // port
	InetAddress serverAddress; // represent server's ip address
	private String msg; // represent the message which we want to send
	private String ip1, ip2;// represent the ip address-1 and the ip address-2
	private int x = 0, y = 0;//the corodinate
	
	public static void main(String args[]) throws Exception {
		UDP_client udp = new UDP_client();//the start point
		
		//-----
		//PortMapping desiredMapping = new PortMapping(8888, "192.168.0.101", PortMapping.Protocol.UDP,
			//	"My Port Mapping");
		//UpnpService upnpService = new UpnpServiceImpl(new PortMappingListener(desiredMapping));
		//upnpService.getControlPoint().search();
		//-----
		//-----
		//String address = InetAddress.getLocalHost().getHostAddress();
		//System.out.println(address);
	}

	public UDP_client(){
		int startTime1=2000,startTime2=0,duration1=2000,duration2=200;
		Scanner scanner = new Scanner(System.in);
		ip1 = scanner.nextLine();//get server1's ip
		ip2 = scanner.nextLine();//get server2's ip
		scanner.close();	
		Timer timer = new Timer();// set the time counter
		//"this" means udp(line 17)
		timer.schedule(new DateTask(this), startTime1, duration1);
		timer.schedule(new connect(this), startTime2, duration2);
	}
	
	public UDP_client(String openServer, int openPortNum, String sendMsg)
			throws Exception {
		port = openPortNum; // set the port
		serverAddress = InetAddress.getByName(openServer); // transfer
		msg = sendMsg; // set the message which we want to send
	}

	public void run() {
		try {
			byte buffer[] = msg.getBytes(); // transfer msg to byte
			// build the packet to DatagramPacket
			// set the destination
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,serverAddress, port);
			DatagramSocket socket = new DatagramSocket(); // build the server
			
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX( int x ){
		this.x = x;
	}
	
	public void setY( int y ){
		this.y = y;
	}
	
	public String getIPOne(){
		return ip1;
	}
	
	public String getIPTwo(){
		return ip2;
	}
	
	public String getMsg(){
		return msg;
	}
}

class DateTask extends TimerTask {
	UDP_client udp;
	
	//let this udp be a pointer that point to line17's udp 
	public DateTask( UDP_client x ){
		udp = x;
	}
	
	
	public void run() {
		if (udp.getX() == 100) {
			udp.setX(-1);
			udp.setY(-1);
		}
		udp.setX(udp.getX() + 1);
		udp.setY(udp.getY() + 1);
	}
}

class connect extends TimerTask {
	
	UDP_client u;
	//let this udp be a pointer that point to line17's udp 
	public connect(UDP_client u){
		this.u = u;
	}
	
	public void run() {
		UDP_client client1;
		UDP_client client2;
		
		String msg = "(" + u.getX() + "," + u.getY() + ")";
		try {
			client1 = new UDP_client(u.getIPOne(), 8888, msg);
			client2 = new UDP_client(u.getIPTwo(), 8888, msg);
			client1.run(); // start UdpClient
			client2.run(); // start UdpClient
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}