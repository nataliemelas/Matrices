package MatrixNMK;

import java.awt.Color;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.display.Trail;
import org.opensourcephysics.frames.PlotFrame;

import RiemannNMK.Riemann;
import RiemannNMK.SimpsonsRule;
import polyfun.*;

public abstract class VerticalDifferenceMethod extends Riemann {
	/**
	 * Your VDM method should take as input a polynomial and an x-coordinate.
	 * OUTPUT: slope of the tangent line && plot the derivative of the given
	 * polynomial ~polynomial interpolation~ print out the derivative
	 * 
	 * 
	 * @return the tangent line, the graph of the derivative of the given
	 *         polynomial, and the graph of the polynomial with the tangent line
	 * 
	 */
	public static void VDM(Polynomial poly, double x) {
		// creates a matrix with the dimensions of the number of coefficients of the
		// inputted polynomial
		Matrix FXmatrix = new Matrix(poly.getCoefficients().length, 1);
		double[] coeffs = new double[poly.getDegree() + 1];
		// fills that matrix
		for (int i = 0; i < poly.getCoefficients().length; i++) {
			coeffs[i] = poly.getCoefficient(i).getTerms()[0].getTermDouble();
			FXmatrix.m[i][0] = poly.getCoefficient(i).getTerms()[0].getTermDouble();
		}

		// creating the (x-a)^2 term
		Polynomial parens = new Polynomial(new double[] { -x, 1 }); // x minus inputed x coordinate
		Polynomial parensSquared = parens.to(2); // squared

		// creating Q(x)
		int numQ = 0;
		Atom q = new Atom('q', 0, 1); // char, subscript, power
		Polynomial QofX = new Polynomial(); // x minus inputed x coordinate
		Polynomial Qs = new Polynomial(new double[] { 0 }); // x minus inputed x coordinate
		for (int j = 0; j < poly.getDegree() - 1; j++) {
			q = new Atom('q', j, 1); // char, subscript, power
			QofX = new Polynomial(q, j);
			Qs = QofX.plus(Qs);
			numQ++;
		}

		// creating V(x)
		Polynomial VofX = Qs.times(parensSquared);

		// creating V(x)+mx+b = F(x)
		Polynomial FofX = VofX.addTangent();

		// creating coef of the coefficients of a specified term in F(X)
		Coef[] mRows = new Coef[FofX.getDegree() + +1];
		for (int i = 0; i < FofX.getDegree() + 1; i++) {
			mRows[i] = FofX.getCoefficient(i).simplify();
		}

		// takes the first coefficient and splits it
		// now takes the first term
		Matrix VXmatrix = new Matrix(2 + numQ, 2 + numQ);

		for (int j = 0; j < mRows.length; j++) {
			// j is the ROW NUMBER
			Term[] split = mRows[j].getTerms();

			// going through the specific coefficients of the degrees of x
			for (int i = 0; i < split.length; i++) {
				Atom[] variable = split[i].getTermAtoms();

				// seeing what the letter is
				if (variable[0].getLetter() == 'b') {
					VXmatrix.m[j][0] = split[i].getTermDouble();
				}
				if (variable[0].getLetter() == 'm') {
					VXmatrix.m[j][1] = split[i].getTermDouble();
				}
				if (variable[0].getLetter() == 'q') {
					VXmatrix.m[j][variable[0].getSubscript() + 2] = split[i].getTermDouble();
				}
			}

		}

		// finding the solutions by multiplying the coefficients of f(x) by the inverse
		// of the coeffs of v(x)
		Matrix Solutions = VXmatrix.invert().times(FXmatrix);
		double b = Solutions.m[0][0];
		double m = Solutions.m[1][0];
		System.out.print("The tangent line to ");
		poly.print();
		System.out.println(" at x = " + x + " is:  y = " + m + "x + " + b);

		// GRAPHING the polynomial and the tangent line
		PlotFrame pframe = new PlotFrame("x-axis", "y-axis",
				"Graph of Your Polynomial from " + (x - 50) + " to " + (x + 50) + " and the Tangent Line at x = " + x);
		pframe.setVisible(true); // can see it
		pframe.setSquareAspect(true); // making graph a square
		pframe.setDefaultCloseOperation(3); // allowing it to close easily

		// graph polynomial
		Trail polynomial = new Trail();
		for (double j = x - 50; j < x + 50; j = j + .01) {
			polynomial.addPoint(j, poly.evaluate(j).getTerms()[0].getTermDouble());
		}
		pframe.addDrawable(polynomial);

		// graph tangent line
		Trail tangent = new Trail();
		for (double j = x - 50; j < x + 50; j = j + .01) {
			tangent.addPoint(j, m * j + b);
		}
		tangent.color = Color.RED;
		pframe.addDrawable(tangent);

		// GRAPHING the derivative
		PlotFrame dframe = new PlotFrame("x-axis", "y-axis",
				"Graph of the Derivative of Your Polynomial from " + (x - 50) + " to " + (x + 50));
		dframe.setVisible(true); // can see it
		dframe.setSquareAspect(true); // making graph a square
		dframe.setDefaultCloseOperation(3); // allowing it to close easily

		// graphing derivative
		Trail derivative = new Trail();
		for (double j = x - 50; j < x + 50; j = j + .01) {
			derivative.addPoint(j, slopeAtPoint(poly, j));
		}
		derivative.color = Color.BLUE;
		dframe.addDrawable(derivative);
	}

	/**
	 * Takes in a polynomial and a point and returns the slope at that point
	 * 
	 * @param poly
	 * @param x
	 * @return the slope at that point
	 */
	public static double slopeAtPoint(Polynomial poly, double x) {
		Matrix FXmatrix = new Matrix(poly.getCoefficients().length, 1);
		double[] coeffs = new double[poly.getDegree() + 1];
		// creating a matrix of the coefficients of the inputed polynomial
		for (int i = 0; i < poly.getCoefficients().length; i++) {
			coeffs[i] = poly.getCoefficient(i).getTerms()[0].getTermDouble();
			FXmatrix.m[i][0] = poly.getCoefficient(i).getTerms()[0].getTermDouble();
		}

		// creating the (x-a)^2 term
		Polynomial parens = new Polynomial(new double[] { -x, 1 }); // x minus inputed x coordinate
		Polynomial parensSquared = parens.to(2); // squared

		// creating Q(x)
		int numQ = 0;
		Atom q = new Atom('q', 0, 1); // char, subscript, power
		Polynomial QofX = new Polynomial(); // x minus inputed x coordinate
		Polynomial Qs = new Polynomial(new double[] { 0 }); // x minus inputed x coordinate
		for (int j = 0; j < poly.getDegree() - 1; j++) {
			q = new Atom('q', j, 1); // char, subscript, power
			QofX = new Polynomial(q, j);
			Qs = QofX.plus(Qs);
			numQ++;
		}

		// creating V(x)
		Polynomial VofX = Qs.times(parensSquared);

		// creating V(x)+mx+b = F(x)
		Polynomial FofX = VofX.addTangent();

		// creating coef of the coefficients of a specified term in F(X)
		Coef[] mRows = new Coef[FofX.getDegree() + +1];
		for (int i = 0; i < FofX.getDegree() + 1; i++) {
			mRows[i] = FofX.getCoefficient(i).simplify();
		}

		// takes the first coefficient and splits it
		// now takes the first term

		// going through the degrees of x
		Matrix VXmatrix = new Matrix(2 + numQ, 2 + numQ);

		for (int j = 0; j < mRows.length; j++) {
			// j is the ROW NUMBER
			Term[] split = mRows[j].getTerms();

			// going through the specific coefficients of the degrees of x
			for (int i = 0; i < split.length; i++) {
				Atom[] variable = split[i].getTermAtoms();

				// seeing what the letter is
				if (variable[0].getLetter() == 'b') {
					VXmatrix.m[j][0] = split[i].getTermDouble();
				}
				if (variable[0].getLetter() == 'm') {
					VXmatrix.m[j][1] = split[i].getTermDouble();
				}
				if (variable[0].getLetter() == 'q') {
					VXmatrix.m[j][variable[0].getSubscript() + 2] = split[i].getTermDouble();
				}
			}

		}
		// multiplying the inverse of the v(x) coefficient matrix to the f(x)
		// coefficient matrix
		Matrix Solutions = VXmatrix.invert().times(FXmatrix);
		// the slope is always the second term in the one-column matrix
		double m = Solutions.m[1][0];

		return m;
	}

	/**
	 * Input a polynomial and return the derivative
	 * 
	 * @return the derivative of the inputted function
	 */
	public static Polynomial polyInterp(Polynomial poly) {
		Matrix slopes = new Matrix(poly.getDegree() + 1, 1);
		Matrix coefficients = new Matrix(poly.getDegree() + 1, poly.getDegree() + 1);

		for (int i = 0; i < poly.getDegree() + 1; i++) {
			slopes.setEntry(i, 0, slopeAtPoint(poly, i));
			for (int j = 0; j < poly.getDegree() + 1; j++) {
				coefficients.setEntry(i, j, Math.pow(i, j));
			}
		}
		double[] polyCoeff = new double[poly.getDegree() + 1];
		Matrix solutions = coefficients.invert().times(slopes);
		for (int i = 0; i < solutions.m.length; i++) {
			polyCoeff[i] = Math.round(solutions.m[i][0] * 1000d / 1000d);
		}
		Polynomial Fprime = new Polynomial(polyCoeff);
		// System.out.print("The derivative is: ");
		// Fprime.print();
		return Fprime;
	}

	/**
	 * Input three points and return the polynomial
	 * 
	 * @return the acc of the inputted function
	 */
	public static Polynomial AccpointInterp(Polynomial poly) {
		Matrix points = new Matrix(poly.getDegree() + 2, 1);
		Matrix coefficients = new Matrix(poly.getDegree() + 2, poly.getDegree() + 2);
		SimpsonsRule Simp = new SimpsonsRule(); // SimpsonsRule extends Riemann
		for (int i = 0; i < poly.getDegree() + 2; i++) {
			points.setEntry(i, 0, Simp.rs(poly, i, 0, 100));
			for (int j = 0; j < poly.getDegree() + 2; j++) {
				coefficients.setEntry(i, j, Math.pow(i, j));
			}
		}
		double[] polyCoeff = new double[poly.getDegree() + 2];
		Matrix solutions = coefficients.invert().times(points);
		for (int i = 0; i < solutions.m.length; i++) {
			polyCoeff[i] = -(double)  Math.round(solutions.m[i][0] * 100000d) / 100000d;

		}
		Polynomial Fprime = new Polynomial(polyCoeff);
		return Fprime;
	}

	/**
	 * Takes in a polynomial and outputs the accumulation function of the derivative
	 * and the derivative of the accumulation function
	 * 
	 * @param poly
	 */
	public static void InterpolationAcc(Polynomial poly) {
		PlotFrame PlotAcc = new PlotFrame("x-axis", "y-axis", "Acc Function of the Derivative");
		PlotAcc.setDefaultCloseOperation(3);
		PlotAcc.setVisible(true);
		PlotFrame PlotPoly = new PlotFrame("x-axis", "y-axis", "Derivative of the Acc Function");
		PlotPoly.setDefaultCloseOperation(3);
		PlotPoly.setVisible(true);
		PlotFrame OgPoly = new PlotFrame("x-axis", "y-axis", "Original Function");
		OgPoly.setDefaultCloseOperation(3);
		OgPoly.setVisible(true);
		SimpsonsRule Simp = new SimpsonsRule(); // SimpsonsRule extends Riemann

		// Differentiate then accumulate
		Polynomial diff = polyInterp(poly);
		Simp.rsAcc(PlotAcc, diff, 1, .01, -1.0); // plots the Simpsons rule acccumulation function of x^2+x, with base
		// x=-1;
		// getting accumulation function
		Polynomial DiffAcc = AccpointInterp(diff);
		
		// original polynomial
		Trail Og = new Trail();
		for (double i = -25; i < 25; i = i + .01) {
			Og.addPoint(i, poly.evaluate(i).getTerms()[0].getTermDouble());
		}
		OgPoly.addDrawable(Og);

		// Accumulate then differentiate
		Trail AccDiffPlot = new Trail();
		// getting accumulation function
		Polynomial Acc = AccpointInterp(poly);
		System.out.println("");
		System.out.print("Accumulation Function: ");
		Acc.print();
		Polynomial AccDiff = polyInterp(Acc);
		for (double i = -25; i < 25; i = i + .01) {
			AccDiffPlot.addPoint(i, AccDiff.evaluate(i).getTerms()[0].getTermDouble());
		}
		AccDiffPlot.color = Color.red;
		PlotPoly.addDrawable(AccDiffPlot);
		System.out.print("While the derivative of the accumulation function (in red) gets you: ");
		AccDiff.print();
		System.out.print("The accumulation function of the differentiated function (in black) gets you: ");
		DiffAcc.print();

	}
}
