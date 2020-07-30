package MatrixNMK;

import MatrixNMK.Matrix;

public class LinearCombRowsTest {

	public static void main(String[] args) {
		Matrix trixie = new Matrix(4, 4);

		for (int i = 0; i < 4; i++) // Filling trixie
		{
			for (int j = 0; j < 4; j++) {
				if (i + j == 2)
					trixie.setEntry(i, j, i + 1);
				else
					trixie.setEntry(i, j, 0);
			}
		}

		trixie.setEntry(3, 3, 4);

		trixie.print(); // printing trixie

		System.out.println("\n");

		// printing the Matrix which results when 1.5 times row 3 of trixie is added to
		// row 0 of trixie
		trixie.linearCombRows(1.5, 3, 0).print();

		System.out.println("\n");

		trixie.print(); // printing trixie again!
	}

}
