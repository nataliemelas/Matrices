package MatrixNMK;

import MatrixNMK.Matrix;

public class RowReduceTest7 {

	public static void main(String[] args) {
		Matrix alice = new Matrix(2, 6);

		for (int i = 0; i < 2; i++) // Filling alice
		{
			for (int j = 0; j < 6; j++) {
				if (i + j == 4 || i + j == 7)
					alice.setEntry(i, j, 0);
				else if (i + j == 3)
					alice.setEntry(i, j, j + 1);
				else
					alice.setEntry(i, j, .25);
			}
		}

		alice.setEntry(1, 4, -13);

		alice.print(); // printing alice

		System.out.println("\n");

		alice.rowreduce().print(); // printing the Matrix which results when alice is row reduced

		System.out.println("\n");

		alice.print(); // printing alice again!
	}

}