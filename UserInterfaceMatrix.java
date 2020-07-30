package MatrixNMK;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;
import MatrixNMK.Matrix;

public class UserInterfaceMatrix {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean input = true;
		boolean directions = true;
		boolean playagain = true;
		while (playagain = true) {
			while (input == true) {
				System.out.println("Welcome to your own Matrix Calculator!");
				System.out.println("Here, you get to input your own matrix.");
				System.out.println("How many rows do you want in your matrix?");
				if (!scan.hasNextInt()) {
					directions = false;
					break;
				}
				int rows = scan.nextInt();
				System.out.println("How many columns do you want in your matrix?");
				if (!scan.hasNextInt()) {
					directions = false;
					break;
				}
				int columns = scan.nextInt();
				int rlength = columns;
				Matrix matrix = new Matrix(rows, columns);
				System.out.println(
						"Please enter the first row of your matrix with the numbers separated by commas. This is referred to as row 0.");
				System.out.println("Example row: 3, 4, 5");
				for (int i = 0; i < rows; i++) {
					rlength = columns;
					if (i == 0) {
						scan.nextLine();
					}
					String rInput = scan.nextLine();
					try {
						String[] row = rInput.split(","); // split of the first line of the file into strings
						if (row.length < columns) {
							rlength = row.length;
						}
						if (row.length > columns) {
							System.out.println("You inputted a row that was too long.");
							directions = false;
							break;
						}
						for (int j = 0; j < rlength; j++) {
							matrix.setEntry(i, j, Double.parseDouble(row[j]));
						}
						System.out.println("So far, your matrix is");
						matrix.print();
						if (i < rows - 1) {
							System.out.println("Please enter row " + (i + 1)
									+ " of your matrix with the numberes separated by commas.");
							System.out.println("Example row: 3, 4, 5");
						}
					} catch (NumberFormatException e) {
						System.out.println("Error: you did not put in a valid row. The matrix from now on is zeros.");
						directions = false;
						break;
					}
				}
				System.out.println("What do you want to do to your matrix?");
				System.out.println(
						"Your options are add, times, scalar times row, switch rows, linear combine rows, row reduce, or invert.");
				String action = scan.nextLine();
				if (action.toLowerCase().equals("add")) {
					Matrix matrix2 = new Matrix(rows, columns);
					System.out.println(
							"Please enter the first row of the matrix you want to add with the numbers separated by commas. This is referred to as row 0.");
					System.out.println("Example row: 3, 4, 5");
					for (int i = 0; i < rows; i++) {
						rlength = columns;
						String rInput = scan.nextLine();
						try {
							String[] row = rInput.split(","); // split of the first line of the file into strings
							if (row.length < columns) {
								rlength = row.length;
							}
							if (row.length > columns) {
								System.out.println("You inputted a row that was too long.");
								directions = false;
								break;
							}
							for (int j = 0; j < rlength; j++) {
								matrix2.setEntry(i, j, Double.parseDouble(row[j]));
							}
							System.out.println("So far, your second matrix is");
							matrix2.print();
							if (i < rows - 1) {
								System.out.println("Please enter row " + (i + 1)
										+ " of your matrix with the numbers separated by commas.");
								System.out.println("Example row: 3, 4, 5");
							}
						} catch (NumberFormatException e) {
							System.out.println("Error: you did not put in a valid row. The matrix from now on is zeros.");
							directions = false;
							break;
						}
					}
					System.out.println("");
					System.out.println("The addition of the matrices is: ");
					matrix.plus(matrix2).print();
				} else if (action.toLowerCase().equals("times")) {
					System.out.println("How many columns do you want in your matrix?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int cols2 = scan.nextInt();
					Matrix matrix2 = new Matrix(columns, cols2);
					System.out.println(
							"Please enter the first row of the matrix you want to add with the numbers separated by commas. This is referred to as row 0.");
					System.out.println("Example row: 3, 4, 5");
					for (int i = 0; i < rows; i++) {
						rlength = columns;
						String rInput = scan.nextLine();
						try {
							String[] row = rInput.split(","); // split of the first line of the file into strings
							if (row.length < columns) {
								rlength = row.length;
							}
							if (row.length > columns) {
								System.out.println("You inputted a row that was too long.");
								directions = false;
								break;
							}
							for (int j = 0; j < rlength; j++) {
								matrix2.setEntry(i, j, Double.parseDouble(row[j]));
							}
							System.out.println("So far, your second matrix is");
							matrix2.print();
							if (i < rows - 1) {
								System.out.println("Please enter row " + (i + 1)
										+ " of your matrix with the numbers separated by commas.");
								System.out.println("Example row: 3, 4, 5");
							}
						} catch (NumberFormatException e) {
							System.out.println("Error: you did not put in a valid row. The matrix from now on is zeros.");
							directions = false;
							break;
						}
					}
					System.out.println("");
					System.out.println("The mulitplication of the matrices is: ");
					matrix.times(matrix2).print();
				} else if (action.equals("scalar times row")) {
					System.out.println("What row do you want to scale?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int row = scan.nextInt();
					if (row > (rows - 1)) {
						System.out.println("Error: you did not put in a valid row number");
						directions = false;
						break;
					}
					System.out.println("By what number do you want to scale it?");
					if (!scan.hasNextDouble()) {
						directions = false;
						break;
					}
					double scalar = scan.nextDouble();
					System.out.println("");
					System.out.println("Your new matrix is: ");
					matrix.scalarTimesRow(scalar, row).print();
				} else if (action.toLowerCase().equals("switch rows")) {
					System.out.println("What is one row you want to switch?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int row1 = scan.nextInt();
					if (row1 > (rows - 1)) {
						System.out.println("Error: you did not put in a valid row number");
						directions = false;
						break;
					}
					System.out.println("What is the other row you want to switch?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int row2 = scan.nextInt();
					if (row2 > (rows - 1)) {
						System.out.println("Error: you did not put in a valid row number");
						directions = false;
						break;
					}
					System.out.println("");
					System.out.println("Your new matrix is: ");
					matrix.switchRows(row2, row1).print();
				} else if (action.toLowerCase().equals("linear combine rows")) {
					// sclara, first row, second row
					System.out.println("What row do you want to scale?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int rowMult = scan.nextInt();
					if (rowMult > (rows - 1)) {
						System.out.println("Error: you did not put in a valid row number");
						directions = false;
						break;
					}
					System.out.println("By what number do you want to scale it?");
					if (!scan.hasNextDouble()) {
						directions = false;
						break;
					}
					double scalar = scan.nextDouble();
					System.out.println("What row do you want to add this to?");
					if (!scan.hasNextInt()) {
						directions = false;
						break;
					}
					int rowAdd = scan.nextInt();
					if (rowAdd > (rows - 1)) {
						System.out.println("Error: you did not put in a valid row number");
						directions = false;
						break;
					}
					System.out.println("");
					System.out.println("Your new matrix is: ");
					matrix.linearCombRows(scalar, rowMult, rowAdd).print();
				} else if (action.toLowerCase().equals("row reduce")) {
					System.out.println("");
					System.out.println("The row-reduced matrix is: ");
					matrix.rowreduce().print();
					System.out.println("");
				} else if (action.toLowerCase().equals("invert")) {
					System.out.println("The inverted matrix is: ");
					matrix.invert().print();
				}
			}
			if (input == false || directions == false) {
				System.out.println(
						"I'm sorry, you did not follow the directions. This matrix is ruined. Do you want to enter another matrix? Enter yes or no.");
				scan.nextLine();
				if ((scan.nextLine()).toLowerCase().equals("yes")) {
					input = true;
					playagain = true;
				} else if ((scan.nextLine()).toLowerCase().equals("no")) {
					System.out.println("Okay, bye.");
					input = false;
					playagain = false;
					break;
				}
			}

		}
	}

}