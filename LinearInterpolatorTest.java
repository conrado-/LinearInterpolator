package com.pac.fw.cache.util.interpolator;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Miguel Rodriguez
 */
public class LinearInterpolatorTest {

	private LinearInterpolator linearInterpolator;

	@Before
	public void setUp() throws Exception {
		Point[] points = { new Point(1, 1), new Point(2, 2), new Point(3, 3) };
		linearInterpolator = new LinearInterpolator(points);
	}

	@Test
	public void testIntercept() {
		int intercept = (int) linearInterpolator.intercept();
		assertEquals(0, intercept);
	}

	@Test
	public void testSlope() {
		int slope = (int) linearInterpolator.slope();
		assertEquals(1, slope);
	}

}
