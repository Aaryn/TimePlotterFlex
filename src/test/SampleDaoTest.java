package test;

import static org.junit.Assert.*;

import model.dao.impl.SampleDao;

import org.junit.Before;
import org.junit.Test;

public class SampleDaoTest {
	SampleDao sampleDao;
	@Before
	public void setUp() throws Exception {
		sampleDao = new SampleDao();
	}

	@Test
	public void testGetLineData() {
		sampleDao.getLineData();
	}

	@Test
	public void testGetLineDataStringStringString() {
		sampleDao.getLineData("HSI", "2011-04-01", "2012-04-01");
	}

}
