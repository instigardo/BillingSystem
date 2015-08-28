package com.vz.bs.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SQLHelperTest {
    SQLHelper pst;
	@Before
	public void setUp() throws Exception {
	pst = new SQLHelper();
	}

	@After
	public void tearDown() throws Exception {
	pst.DELETE("payments_summary", "account_number = 201");
	}

	@Test
	public void testUPDATE() {
		pst.INSERT("payments_summary","'201','25-Aug-2015','502','502','22-dec-2015'");
		pst.UPDATE("payments_summary", "billed_amount = 504", "account_number = 201");
	    assertEquals(1, pst.r);
	}

	@Test
	public void testINSERT() {
		pst.INSERT("payments_summary","'201','25-Aug-2015','502','502','22-dec-2015'");
	    assertEquals(1,pst.r);
	}

	@Test
	public void testDELETE() {
		pst.INSERT("payments_summary","'201','25-Aug-2015','502','502','22-dec-2015'");
		pst.DELETE("payments_summary", "account_number = 201");
		assertEquals(1,pst.r);
	}

}
