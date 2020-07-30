package MatrixNMK;

import java.util.Random;

import MatrixNMK.Matrix;

public class InvertTest4 {

	public static void main(String[] args) {
		Random gen = new Random();

		for (int k = 0; k < 5; k++) {
			int dimensions = gen.nextInt(15);
			if (dimensions == 0 || dimensions == 1) {
				dimensions = gen.nextInt(20);
				if (dimensions == 0 || dimensions == 1) {
					dimensions = gen.nextInt(10);
				}
			}

			Matrix matt = new Matrix(dimensions, dimensions); // matt is invertible
			for (int i = 0; i < dimensions; i++) // Filling matt
			{
				for (int j = 0; j < dimensions; j++) {
					matt.setEntry(i, j, gen.nextInt(50));
				}
			}

//			matt.print(); // printing matt
//
//			System.out.println("\n");
//
//			matt.invert().print(); // printing the inverse of matt
//
//			System.out.println("\n");

			matt.times(matt.invert()).print(); // printing the product of matt and his inverse

			System.out.println("\n");

		}
	}
}
