package cot.qin.sdm;

import static org.junit.Assert.*;

import org.junit.Test;

public class SDMTest {
	@Test
	public void testLoadMap() {
		//setup
		SceneDataModule sdm = SceneDataModule.getInstance();
		//exercise
		sdm.loadMap("mapfile");
		//assert
		assertEquals(false, sdm.imageTypeErr);
		assertEquals(false, sdm.notFound);
		assertNotNull(sdm.getMap().getScene());
		//exercise
		sdm.loadMap("notfound");
		//assert
		assertEquals(true, sdm.notFound);
		assertNull(sdm.getMap().getScene());
		//exercise
		sdm.loadMap("mapfile_err");
		//assert
		assertEquals(true, sdm.imageTypeErr);
		assertNull(sdm.getMap().getScene());
	}

}
