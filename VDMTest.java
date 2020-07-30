package MatrixNMK;

import polyfun.*;

import java.util.Random;

import org.opensourcephysics.frames.PlotFrame;

import MatrixNMK.Matrix;

public class VDMTest extends VerticalDifferenceMethod {
	public static void main(String[] args) {
		Random gen = new Random();
		// Polynomial p = new Polynomial(new double[] { 5, 3, 0, 2, 5}); // p = 2x^3 +
		// 3x
		// Polynomial p = new Polynomial(new double[] { 1001, 0, 3, 0, 65, 0, 0, 0, 4});
		// // p = 2x^3 + 3x
		// Polynomial p = new Polynomial(new double[] { 0, 1, 0, -4, 3}); // p = 2x^3 +
		// 3x
		// polyInterp(p);
		// pointInterp(-2.0, 4.0, 1.0, 1.0, 2.0, 4.0);
		Polynomial poly = new Polynomial(new double[] { 0, 0, 0, 0, 1 }); // p = 2x^3 + 3x
		// InterpolationAcc(poly);
		// Polynomial nom = new Polynomial(new double[] { 0, 5, 4, 1}); // p = 2x^3 + 3x
		// InterpolationAcc(nom);
		poly.print();
		AccpointInterp(poly).print();

		// VDM(p, 1);
		// System.out.println(slopeAtPoint(p,1));
		// slopeFunction(p);

		// Polynomial p = new Polynomial(new double[] { -57, 0, 3, 0, 0, -5, 3 }); // p
		// = 4x^3 + 3x^2 - 6x + 3
		// VDM(p, 9);
		// System.out.println(slopeAtPoint(p,9));

		// Polynomial poly = new Polynomial(new double[] { gen.nextInt(10),
		// gen.nextInt(10), gen.nextInt(10),
		// gen.nextInt(10), gen.nextInt(10), gen.nextInt(10), gen.nextInt(10),
		// gen.nextInt(10), gen.nextInt(10),
		// gen.nextInt(10), gen.nextInt(10), gen.nextInt(10), gen.nextInt(10) }); // p =
		// 2x^3 + 3x
		// VDM(poly, gen.nextInt(10));
		// Polynomial p = new Polynomial(
		// new double[] { gen.nextInt(10), gen.nextInt(10), gen.nextInt(10),
		// gen.nextInt(10), gen.nextInt(10) }); // p
		// =
		// 2x^3 // +
		// // 3x
		// VDM(p, 9);
		// System.out.println("The slope at 9 is " + slopeAtPoint(p,9));

	}

	@Override
	public double slice(Polynomial poly, double sleft, double sright) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void slicePlot(PlotFrame pframe, Polynomial poly, double sleft, double sright) {
		// TODO Auto-generated method stub

	}
}
