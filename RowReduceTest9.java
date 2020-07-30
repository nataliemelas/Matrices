package MatrixNMK;

import MatrixNMK.Matrix;

public class RowReduceTest9 {
	public static void main(String[] args) {
		Matrix matt = new Matrix(3, 6); // matt is invertible

		int counter = 0;

		for (int i = 0; i < 3; i++) // Filling matt
		{
			for (int j = 0; j < 3; j++) {
				counter++;
				matt.setEntry(i, j, counter);
			}
		}

		matt.setEntry(2, 2, 10);
		matt.setEntry(0, 3, 1);
		matt.setEntry(1, 4, 1);
		matt.setEntry(2, 5, 1);

		matt.print(); // printing matt

		System.out.println("\n");

		matt.rowreduce().print(); // printing the rowreduce of matt

		System.out.println("\n");

		matt.print(); // printing matt

		System.out.println("\n");

		Matrix Test = new Matrix(5, 5);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Test.setEntry(i, j, .1 + 5 * i + j);
			}
		}

		Test.print(); // printing Test

		System.out.println("\n");

		Test.rowreduce().print(); // printing the inverse of Test

		System.out.println("\n");

		Test.print(); // printing Test

		System.out.println("\n");

		Matrix mattrix = new Matrix(7, 7); // matt is invertible

		int counterix = 0;

		for (int i = 0; i < 7; i++) // Filling matt
		{
			for (int j = 0; j < 7; j++) {
				counterix++;
				if (i + j == 6) {
					mattrix.setEntry(i, j, 4.0);
				}
				if (i + j == 7) {
					mattrix.setEntry(i, j, 2.0);
				}
				if (i + j == 10) {
					mattrix.setEntry(i, j, 17.0);
				} else {
					mattrix.setEntry(i, j, counterix);
				}
			}
		}
		// mattrix.setEntry(0, 7, 1.0);
		// mattrix.setEntry(1, 8, 1.0);
		// mattrix.setEntry(2, 9, 1.0);
		// mattrix.setEntry(3, 10, 1.0);
		// mattrix.setEntry(4, 11, 1.0);
		// mattrix.setEntry(5, 12, 1.0);
		// mattrix.setEntry(6, 13, 1.0);

		mattrix.setEntry(1, 1, 9);
		mattrix.setEntry(2, 1, 0);
		mattrix.setEntry(5, 0, 0);
		mattrix.setEntry(6, 1, 0);
		mattrix.setEntry(3, 2, 0);

		mattrix.print(); // printing Test

		System.out.println("\n");

		mattrix.rowreduce().print(); // printing the inverse of Test

		System.out.println("\n");

		mattrix.print(); // printing Test

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

		trixie.rowreduce().print(); // printing the inverse of trixie

		System.out.println("\n");

		trixie.print(); // printing trixie

		System.out.println("\n");
	}
}
