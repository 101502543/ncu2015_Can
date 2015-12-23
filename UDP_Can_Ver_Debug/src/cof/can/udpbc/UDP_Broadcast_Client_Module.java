package cof.can.udpbc;

//FreeGeng 2015/12/20
//encoding UTTF-8

import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


import cof.server.interfaces.CentralDataCenter;
import cof.server.interfaces.TcpServerModule;


public class UDP_Broadcast_Client_Module extends Thread {

	int port; // port
	InetAddress serverAddress; // represent server's ip address
	private String msg; // represent the message which we want to send
	private String ip1, ip2, ip3, ip4;// represent the ip address1 and the ip
										// address2
	private ArrayList<String> clientTable;

	/*public void startUDPBroadCast() throws Exception {
		UDP_Broadcast_Client_Module udp = new UDP_Broadcast_Client_Module();
	}*/

	public UDP_Broadcast_Client_Module(CentralDataCenter cdc) {
		int startTime1 = 0, duration1 = 200;
		//clientTable = TCPSM.getInstance().getIPTable();
		ip1 = clientTable.get(0).toString();
		ip2 = clientTable.get(1).toString();
		ip3 = clientTable.get(2).toString();
		ip4 = clientTable.get(3).toString();

		Timer timer = new Timer();// set the time counter
		timer.schedule(new connect(this, cdc), startTime1, duration1);

	}

	public UDP_Broadcast_Client_Module(String openServer, int openPortNum,
			String sendMsg) throws Exception {
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

	public String getIPOne() {
		return ip1;
	}

	public String getIPTwo() {
		return ip2;
	}

	public String getIPThree() {
		return ip3;
	}

	public String getIPFour() {
		return ip4;
	}

}

// sent msg
class connect extends TimerTask {

	UDP_Broadcast_Client_Module u;
	CentralDataCenter CDC;
    ArrayList<String> updateInfo = new ArrayList<String>();
	StringBuilder stringTemp = new StringBuilder();
	// let this udp be a pointer that point to line17's udp
	public connect(UDP_Broadcast_Client_Module u, CentralDataCenter CDC) {
		this.u = u;
		this.CDC = CDC;	
	}

	public void run() {
		updateInfo = CDC.getUpdateInfo();	
		UDP_Broadcast_Client_Module client1;
		UDP_Broadcast_Client_Module client2;
		UDP_Broadcast_Client_Module client3;
		UDP_Broadcast_Client_Module client4;

		
		for (String o : updateInfo) {
			stringTemp.append(o);
			stringTemp.append(";");
		}
		String msg = stringTemp.toString();
        stringTemp.delete(0, stringTemp.length());
		try {

			client1 = new UDP_Broadcast_Client_Module(u.getIPOne(), 8888, msg);
			client2 = new UDP_Broadcast_Client_Module(u.getIPTwo(), 8888, msg);
			client3 = new UDP_Broadcast_Client_Module(u.getIPThree(), 8888, msg);
			client4 = new UDP_Broadcast_Client_Module(u.getIPFour(), 8888, msg);

			client1.run(); // start UdpClient
			client2.run(); // start UdpClient
			client3.run(); // start UdpClient
			client4.run(); // start UdpClient

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
