package cof.can.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cof.can.udpbc.TCP_Server_module;

import java.util.Vector;

public class testTCPSM {
	TCP_Server_module TCPSM;

	@Before
	public void setUp() throws Exception{
		TCPSM = new TCPSMstub();
	}
	
	// test the vector include client ip table is null or not
	@Test
	public final void testTCPSM() {
		Vector msgTCPSM = TCPSM.getClientIPTable();
		assertNotNull(msgTCPSM);
	}

}

class TCPSMstub implements TCP_Server_module{
	@Override
	public Vector getClientIPTable() {
		Vector ClientIPTable = new Vector();
		ClientIPTable.add("127.0.0.1");
		ClientIPTable.add("140.115.50.2");
		return ClientIPTable;
	}
}
