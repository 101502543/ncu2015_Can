package cof.can.udpbc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cof.can.udpbc.UDP_Broadcast_Client_Module;
import cof.can.udpus.UDP_Update_Server_Module;

public class testUDPBC {

	UDP_Update_Server_Module server = new UDP_Update_Server_Module(8888);
	UDP_Broadcast_Client_Module UDPBC = new UDP_Broadcast_Client_Module();
	@Test
	public void test() throws Exception {
		server.initUDPServer();
		UDPBC.startUDPBroadCast();
	}

}
