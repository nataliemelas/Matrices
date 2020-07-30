package MatrixNMK;

import MatrixNMK.Matrix;

public class InvertTest2 {

	public static void main(String[] args) {
//		Matrix matt = new Matrix(2, 2); // matt is invertible
//
//		int counter = 0;
//
//		for (int i = 0; i < 2; i++) // Filling matt
//		{
//			for (int j = 0; j < 2; j++) {
//				counter++;
//				if (i + j == 2) {
//					matt.setEntry(i, j, 0);
//				} else {
//					matt.setEntry(i, j, counter);
//				}
//			}
//		}
//
//		matt.setEntry(1, 1, 10);
//
//		matt.print(); // printing matt
//
//		System.out.println("\n");
//
//		matt.invert().print(); // printing the inverse of matt
//
//		System.out.println("\n");
//
//		matt.times(matt.invert()).print(); // printing the product of matt and his inverse
//
//		System.out.println("\n");
//		System.out.println("\n");
//
//		Matrix trixie = new Matrix(5, 5); // trixie is invertible
//
//		for (int i = 0; i < 5; i++) // Filling trixie
//		{
//			for (int j = 0; j < 5; j++) {
//				if (i + j == 3)
//					trixie.setEntry(i, j, i - 11);
//				else
//					trixie.setEntry(i, j, 0);
//			}
//		}
//
//		trixie.setEntry(3, 3, 4);
//		trixie.setEntry(1, 4, 7);
//		trixie.setEntry(4, 2, 5);
//
//		trixie.print(); // printing trixie
//
//		System.out.println("\n");
//
//		trixie.invert().print(); // printing the inverse of trixie
//
//		System.out.println("\n");
//
//		trixie.times(trixie.invert()).print(); // printing the product of trixie and her inverse
//
//		System.out.println("\n");
//		System.out.println("\n");
//
//		Matrix check = new Matrix(3, 3);
//
//		check.setEntry(0, 0, 1);
//		check.setEntry(0, 1, 2);
//		check.setEntry(0, 2, -1);
//		check.setEntry(1, 0, 0);
//		check.setEntry(1, 1, 3);
//		check.setEntry(1, 2, 0);
//		check.setEntry(2, 0, -4);
//		check.setEntry(2, 1, 0);
//		check.setEntry(2, 2, 5);
//
//		check.print();
//		System.out.println("\n");
//		check.invert().print();
//		System.out.println("\n");
//		check.times(check.invert()).print();

		int counterz = 0;
		Matrix kara = new Matrix(4, 4);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i == 0) {
					kara.setEntry(i, j, Math.pow(1, j));
				} else if (i == 1) {
					kara.setEntry(i, j, Math.pow(2, j));
				} else if (i == 2) {
					kara.setEntry(i, j, Math.pow(4, j));
				} else if (i == 3) {
					kara.setEntry(i, j, Math.pow(7, j));
				}
			}
		}
		kara.print();
		System.out.println(" ");
		kara.invert().print();
		System.out.println(" ");
		kara.times(kara.invert()).print();
	}
}
