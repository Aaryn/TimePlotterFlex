package test;

import static org.junit.Assert.*;

import model.dao.impl.volumeDao;

import org.junit.Before;
import org.junit.Test;

public class volumeDaoTest {
	volumeDao volumeDao;

	@Before
	public void setUp() throws Exception {
		volumeDao = new volumeDao();
	}

	@Test
	public void testGetVolumeData() {
		volumeDao.getVolumeData();
	}

	@Test
	public void testGetVolumeDataStringStringString() {
		volumeDao.getVolumeData("HSI", "2011-04-01", "2012-04-01");
	}

}
