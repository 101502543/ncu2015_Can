package cof.can.udpbc.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cof.can.udpbc.TCP_Server_module;

import java.util.Vector;

public class testTCPSM {
	TCP_Server_module TCPSM = new TCP_Server_module();

	// test the vector include client ip table is null or not
	@Test
	public void testTCPSM() {
		Vector msgTCPSM = TCPSM.getClientIPTable();
		System.out.println(msgTCPSM);
		assertNotNull(msgTCPSM);
	}

}
