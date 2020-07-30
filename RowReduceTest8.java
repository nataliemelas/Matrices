package MatrixNMK;

import MatrixNMK.Matrix;

public class RowReduceTest8 {

	public static void main(String[] args) {
		Matrix alice = new Matrix(5, 5); // alice is invertible

		for (int i = 0; i < 5; i++) // Filling alice
		{
			for (int j = 0; j < 5; j++) {
				if (i + j == 3)
					alice.setEntry(i, j, i - 11);
				else
					alice.setEntry(i, j, 0);
			}
		}

		alice.setEntry(3, 3, 4);
		alice.setEntry(1, 4, 7);
		alice.setEntry(4, 2, 5);

		alice.print(); // printing alice

		System.out.println("\n");

		alice.rowreduce().print(); // printing the Matrix which results when alice is row reduced

		System.out.println("\n");

		alice.print(); // printing alice again!
	}

}