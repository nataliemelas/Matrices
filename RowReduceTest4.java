package MatrixNMK;

import MatrixNMK.Matrix;

public class RowReduceTest4 {

	public static void main(String[] args) {
		Matrix alice = new Matrix(3, 5);

		for (int i = 0; i < 3; i++) // Filling alice
		{
			for (int j = 0; j < 5; j++) {
				if (i + j == 3)
					alice.setEntry(i, j, i+1);
				else if (i + j == 1)
					alice.setEntry(i, j, 0);
				else
					alice.setEntry(i, j, 0);
			}
		}

		alice.setEntry(2, 4, 7);

		alice.print(); // printing alice

		System.out.println("\n");

		alice.rowreduce().print(); // printing the Matrix which results when alice is row reduced

		System.out.println("\n");

		alice.print(); // printing alice again!
	}

}