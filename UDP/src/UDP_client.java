//FreeGeng since 2015/10/9
//encoding:UTF-8
import java.util.Scanner;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

public class UDP_client extends Thread {

	int port; // port
	InetAddress serverAddress; // represent server's ip address
	static String msg; // represent the message which we want to send
	static String ip1, ip2;// represent the ip address-1 and the ip address-2
	static int x = 0, y = 0;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) throws Exception {

		//System.out.println("Input IP address 1:");
		ip1 = scanner.nextLine();
		//System.out.println("Input IP address 2:");
		ip2 = scanner.nextLine();
		System.out.println("Sending Message...");
		Timer timer = new Timer();// set the time counter
		timer.schedule(new DateTask(), 2000, 2000);
		timer.schedule(new connect(), 0, 200);

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
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
					serverAddress, port);
			DatagramSocket socket = new DatagramSocket(); // build the server
			
			socket.send(packet);
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class DateTask extends TimerTask {
	UDP_client UDP_client;

	public void run() {
		if (UDP_client.x == 100) {
			UDP_client.x = -1;
			UDP_client.y = -1;
		}
		UDP_client.x = UDP_client.x + 1;
		UDP_client.y = UDP_client.y + 1;
	}
}

class connect extends TimerTask {
	UDP_client UDP_client;
	//String address = "127.0.0.1";

	public void run() {
		UDP_client client1;
		UDP_client client2;
		UDP_client.msg = "(" + UDP_client.x + "," + UDP_client.y + ")";
		try {
			client1 = new UDP_client(UDP_client.ip1, 8888, UDP_client.msg);
			client2 = new UDP_client(UDP_client.ip2, 8888, UDP_client.msg);
			client1.run(); // start UdpClient
			client2.run(); // start UdpClient
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}