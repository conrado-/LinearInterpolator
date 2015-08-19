package com.pac.fw.cache.util.interpolator;

/**
 * @author Miguel Rodriguez
 */

public class LinearRegression {
	private final int length;
	private final double alpha, slope;
	private final double R2;
	private final double svar, svar0, svar1;

	/**
	 * Performs a linear regression on the data points <tt>(y[i], x[i])</tt>.
	 * 
	 * @param x
	 *            the values of the predictor variable
	 * @param y
	 *            the corresponding values of the response variable
	 * @throws java.lang.IllegalArgumentException
	 *             if the lengths of the two arrays are not equal
	 */
	public LinearRegression(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("array lengths are not equal");
		}
		length = x.length;

		// first pass
		double sumX = 0.0, sumY = 0.0;
		for (int i = 0; i < length; i++)
			sumX += x[i];

		for (int i = 0; i < length; i++)
			sumY += y[i];

		double meanX = sumX / length;
		double meanY = sumY / length;
		// System.out.println("length: " + length + " sumX: " + sumX + " sumY: "
		// + sumY + " meanX: " + meanX + " meanY: " + meanY);

		// second pass: compute summary statistics
		double varianceX = 0.0, varianceY = 0.0, covarianceXY = 0.0;
		for (int i = 0; i < length; i++) {
			double deltaX = x[i] - meanX;
			varianceX += deltaX * deltaX;
			double deltaY = y[i] - meanY;
			varianceY += deltaY * deltaY;
			covarianceXY += deltaX * deltaY;

			System.out.println(" x[i]: " + x[i] + " meanX: " + meanX
					+ " deltaX: " + deltaX + " deltaY: " + deltaY
					+ " covarianceXY: " + covarianceXY + " varianceX: "
					+ varianceX);
		}
		slope = covarianceXY / varianceX;
		alpha = meanY - slope * meanX;

		System.out.println(" slope: " + slope);

		// more statistical analysis
		double rss = 0.0; // residual sum of squares
		double ssr = 0.0; // regression sum of squares
		for (int i = 0; i < length; i++) {
			double fit = slope * x[i] + alpha;
			rss += (fit - y[i]) * (fit - y[i]);
			ssr += (fit - meanY) * (fit - meanY);
		}

		int degreesOfFreedom = length - 2;
		R2 = ssr / varianceY;
		svar = rss / degreesOfFreedom;
		svar1 = svar / varianceX;
		svar0 = svar / length + meanX * meanX * svar1;
	}

	/**
	 * Returns the <em>y</em>-intercept &alpha; of the best of the best-fit line
	 * <em>y</em> = &alpha; + &beta; <em>x</em>.
	 * 
	 * @return the <em>y</em>-intercept &alpha; of the best-fit line
	 *         <em>y = &alpha; + &beta; x</em>
	 */
	public double intercept() {
		return alpha;
	}

	/**
	 * Returns the slope &beta; of the best of the best-fit line <em>y</em> =
	 * &alpha; + &beta; <em>x</em>.
	 * 
	 * @return the slope &beta; of the best-fit line <em>y</em> = &alpha; +
	 *         &beta; <em>x</em>
	 */
	public double slope() {
		return slope;
	}

	/**
	 * Returns the coefficient of determination <em>R</em><sup>2</sup>.
	 * 
	 * @return the coefficient of determination <em>R</em><sup>2</sup>, which is
	 *         a real number between 0 and 1
	 */
	public double R2() {
		return R2;
	}

	/**
	 * Returns the standard error of the estimate for the intercept.
	 * 
	 * @return the standard error of the estimate for the intercept
	 */
	public double interceptStdErr() {
		return Math.sqrt(svar0);
	}

	/**
	 * Returns the standard error of the estimate for the slope.
	 * 
	 * @return the standard error of the estimate for the slope
	 */
	public double slopeStdErr() {
		return Math.sqrt(svar1);
	}

	/**
	 * Returns the expected response <tt>y</tt> given the value of the predictor
	 * variable <tt>x</tt>.
	 * 
	 * @param x
	 *            the value of the predictor variable
	 * @return the expected response <tt>y</tt> given the value of the predictor
	 *         variable <tt>x</tt>
	 */
	public double predict(double x) {
		return slope * x + alpha;
	}

	/**
	 * Returns a string representation of the simple linear regression model.
	 * 
	 * @return a string representation of the simple linear regression model,
	 *         including the best-fit line and the coefficient of determination
	 *         <em>R</em><sup>2</sup>
	 */
	public String toString() {
		String s = "";
		s += String.format("%.2f N + %.2f", slope(), intercept());
		return s + "  (R^2 = " + String.format("%.3f", R2()) + ")";
	}

}
