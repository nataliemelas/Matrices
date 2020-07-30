package MatrixNMK;

import MatrixNMK.Matrix;

public class InvertTest3 {

	public static void main(String[] args) {
		Matrix matt = new Matrix(7, 7); // matt is invertible

		int counter = 0;

		for (int i = 0; i < 7; i++) // Filling matt
		{
			for (int j = 0; j < 7; j++) {
				counter++;
				if (i + j == 6) {
					matt.setEntry(i, j, 4.0);
				}
				if (i + j == 7) {
					matt.setEntry(i, j, 2.0);
				}
				if (i + j == 10) {
					matt.setEntry(i, j, 17.0);
				} else {
					matt.setEntry(i, j, counter);
				}
			}
		}

		matt.setEntry(1, 1, 9);
		matt.setEntry(2, 1, 0);
		matt.setEntry(5, 0, 0);
		matt.setEntry(6, 1, 0);
		matt.setEntry(3, 2, 0);

		matt.print(); // printing matt

		System.out.println("\n");

		matt.invert().print(); // printing the inverse of matt

		System.out.println("\n");

		matt.times(matt.invert()).print(); // printing the product of matt and his inverse

		System.out.println("\n");

		Matrix trixie = new Matrix(7, 7); // trixie is invertible

		int counterr = 0;

		for (int i = 0; i < 7; i++) // Filling matt
		{
			for (int j = 0; j < 7; j++) {
				counterr = counterr + 1;
				if (i + j == 6) {
					trixie.setEntry(i, j, 4.0);
				}
				if (i + j == 7) {
					trixie.setEntry(i, j, 2.0);
				}
				if (i + j == 10) {
					trixie.setEntry(i, j, 17.0);
				} else {
					trixie.setEntry(i, j, counterr);
					counterr = counterr - 1;
				}
			}
		}

		trixie.setEntry(1, 1, 9);
		trixie.setEntry(1, 4, 17);
		trixie.setEntry(2, 1, 0);
		trixie.setEntry(5, 0, 0);
		trixie.setEntry(6, 1, 0);
		trixie.setEntry(3, 2, 0);

		trixie.print(); // printing trixie

		System.out.println("\n");

		trixie.invert().print(); // printing the inverse of trixie

		System.out.println("\n");

		trixie.times(trixie.invert()).print(); // printing the product of trixie and her inverse
	}
}
