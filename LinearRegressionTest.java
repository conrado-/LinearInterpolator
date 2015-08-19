package com.pac.fw.cache.util.interpolator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Miguel Rodriguez
 */
public class LinearRegressionTest {

	private LinearRegression linearInterpolator;

	@Before
	public void setUp() throws Exception {
		double[] x = { 2, 1, 2, 2, 3, 3, 4 };
		double[] y = { 1902, 4364, 6865, 9381, 14404, 19434, 29402 };
		linearInterpolator = new LinearRegression(x, y);
	}

	// @Test
	public void testIntercept() {
		double doubleIntercept = linearInterpolator.intercept();
		int intercept = (int) (100 * doubleIntercept);
		assertEquals(-964874, intercept);
	}

	// @Test
	public void testSlope() {
		double doubleSlope = linearInterpolator.slope();
		int slope = (int) (100 * doubleSlope);
		assertEquals(901724, slope);
	}

	@Test
	public void testPredict() {
		double doublePredict = linearInterpolator.predict(0);
		int predict = (int) (100 * doublePredict);
		assertEquals(-964874, predict);
	}

}
