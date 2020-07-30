package MatrixNMK;

import java.text.DecimalFormat;
import polyfun.*;

public class Matrix {
	double[][] m = {};
	int r;
	int c;

	/**
	 * Creates a matrix
	 * 
	 * @param r
	 *            number of rows
	 * @param c
	 *            number of columns
	 */
	public Matrix(int r, int c) {
		m = new double[r][c];
		this.r = r; // rows
		this.c = c; // columns
		this.m = m; // matrix
	}

	/**
	 * Sets the value for a specific entry in the matrix
	 * 
	 * @param r
	 * @param c
	 * @param entry
	 */
	public void setEntry(int r, int c, double entry) {
		m[r][c] = entry;
	}

	/**
	 * Gets the value of a specific entry in the matrix
	 * 
	 * @param r
	 * @param c
	 * @return
	 */
	public double getEntry(int r, int c) {
		return m[r][c];
	}

	/**
	 * Prints a matrix with non-rounded entries
	 */
	public void print() {
		// row
		for (int i = 0; i < m.length; i++) {
			// column
			for (int j = 0; j < m[i].length; j++) {
				// making sure it doesn't print negative zero
				for (int k = 0; k < m.length; k++) {
					for (int h = 0; h < m[k].length; h++) {
						if ((double) Math.round(m[k][h] * 100000d) / 100000d == -0.00000) {
							m[k][h] = 0.0000;
						}
					}
				}
				// making each double a string with four decimal places (rounds each entry)
				String entry = String.format("%.4f \t", m[i][j]);
				System.out.print(entry);
			}
			System.out.println("");
		}
	}

	/**
	 * Adds two matrices
	 * 
	 * @param m2
	 * @return
	 */
	public Matrix plus(Matrix m2) {
		Matrix sum = new Matrix(r, c);
		// go through matrix
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				// add the entries with the corresponding coordinates
				sum.m[i][j] = m[i][j] + m2.m[i][j];
			}
		}
		return sum;
	}

	/**
	 * Multiplies two matrices
	 * 
	 * @param m2
	 * @return
	 */
	public Matrix times(Matrix m2) {
		int row = m.length; // row length m
		int samerc = m[1].length; // column length of m
		int column = m2.m[1].length; // column length (same as m row length)
		// new matrix (the product)
		Matrix prod = new Matrix(row, column);
		// goes through the first matrix row
		for (int i = 0; i < row; i++) {
			// goes through the second matrix column
			for (int j = 0; j < column; j++) {
				// goes through the first matrix column
				for (int k = 0; k < samerc; k++) {
					// sets the matrix entry to be the entry + the product of the two matrices
					prod.m[i][j] = prod.m[i][j] + m[i][k] * m2.m[k][j];
				}
			}
		}
		return prod;
	}

	/**
	 * Multiplies a scalar times a row
	 * 
	 * @param scalar
	 * @param row
	 * @return
	 */
	public Matrix scalarTimesRow(double scalar, int row) {
		// create matrix to be scaled
		Matrix mscaled = new Matrix(r, c);
		// goes through matrix
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// if it's the row specified, multiply each entry by the scalar
				if (i == row) {
					mscaled.m[i][j] = m[i][j] * scalar;
				}
				// if it's not the row we want, then keep the entries the same
				else {
					mscaled.m[i][j] = m[i][j];
				}
			}
		}
		return mscaled;
	}

	/**
	 * Switches two rows of a matrix
	 * 
	 * @param firstrow
	 * @param secondrow
	 * @return
	 */
	public Matrix switchRows(int firstrow, int secondrow) {
		// take the matrix
		Matrix mswitched = new Matrix(r, c);
		// go through each entry
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// if it's the first row then switch with the second row
				if (i == firstrow) {
					mswitched.m[i][j] = m[secondrow][j];
				}
				// if its the second row switch with the first row
				else if (i == secondrow) {
					mswitched.m[i][j] = m[firstrow][j];
				}
				// if it's neither, then keep it the same
				else {
					mswitched.m[i][j] = m[i][j];
				}
			}
		}
		return mswitched;
	}

	/**
	 * Adds a scalar multiple of the first row to the second row
	 * 
	 * @param scalar
	 * @param firstrow
	 * @param secondrow
	 * @return
	 */
	public Matrix linearCombRows(double scalar, int firstrow, int secondrow) {
		// creates matrix to be scaled
		Matrix mscaled = new Matrix(r, c);
		// goes through each row and column
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				// takes the row that is going to be added to
				if (i == secondrow) {
					// goes through the matrix again
					for (int x = 0; x < r; x++) {
						for (int y = 0; y < c; y++) {
							// gets the row that is multiplied by a scalar
							if (x == firstrow) {
								// set the entries of the row that's added to as the original entry and the
								// scalar of the other row's corresponding entry (same column)
								mscaled.m[secondrow][y] = m[secondrow][y] + (m[x][y] * scalar);
							}
						}
					}
				} else {
					mscaled.m[i][j] = m[i][j];
				}
			}
		}
		return mscaled;
	}

	/**
	 * Puts the Matrix in Row Reduced Echelon Form
	 * 
	 * @return matrix in row reduced echelon form
	 */
	public Matrix rowreduce() {
		// create matrix so we can row reduce it
		Matrix mreduced = new Matrix(r, c);
		// the matrix is filled w the inputted matrix's entries
		mreduced = this;
		boolean switched = false;
		int counter = 0;
		int column = 0;

		// goes through matrix and finds the pivot and switches the first row if it
		// starts with a zero
		for (int h = 0; h < mreduced.m[0].length; h++) {
			for (int k = 0; k < mreduced.m.length; k++) {
				// if it is not a zero
				if (mreduced.m[k][h] != 0.0) {
					// make sure it is the pivot
					for (int y = h; y >= 0; y = y - 1) {
						// count how many times there is another non zero number to the left of that
						// entry in that row
						if (mreduced.m[k][y] != 0) {
							counter++;
							column++;
						}
					}
				}
			}
			// if it is the pivot
			if (counter == 1) {
				for (int j = 0; j < mreduced.m[1].length; j++) {
					for (int i = 0; i < mreduced.m.length; i++) {
						// if the row hasn't been switched
						if (switched == false) {
							// check if it starts with a zero
							if (mreduced.m[i][h] != 0) {
								// switching row if it starts with a zero
								mreduced = mreduced.switchRows(column - 1, i);
								switched = true;
							}
						}
					}
					// goes to next column so needs to reset switched boolean
					switched = false;
				}
			}
			counter = 0;
		}

		// makes the first row start with one
		for (int w = 0; w < 1; w++) {
			for (int u = 0; u < mreduced.m[0].length; u++) {
				if (mreduced.m[w][u] != 0) {
					mreduced = mreduced.scalarTimesRow(1 / mreduced.m[w][u], w); // making row start w/ a 1
					break;
				}
			}
		}

		boolean alreadyone = false;

		// gets it in row echelon form
		for (int i = 0; i < mreduced.m.length; i++) {
			// takes the columns and combines the second
			for (int j = 0; j < i; j++) {
				mreduced = mreduced.linearCombRows(-mreduced.m[i][j], j, i);
			}
			double ToBeOne = mreduced.m[i][i];
			for (int k = i; k < mreduced.m[i].length; k++) {
				// checking if the pivot is zero
				if ((double) Math.round(mreduced.m[i][k] * 100000d) / 100000d == 0.00000) {
					// if the pivot is zero, we want to go through the column to switch the rows
					for (int b = i; b < mreduced.m.length; b++) {
						// if the pivot is not zero in a certain row (and that same column)
						if ((double) Math.round(mreduced.m[b][k] * 100000d) / 100000d != 0.0) {
							// then we want to switch the rows so there is a non-zero number at the correct
							// place
							mreduced = mreduced.switchRows(b, i);
							// now we want to make sure the row we just switched has zeros to the left of
							// the pivot
							for (int j = 0; j < i; j++) {
								mreduced = mreduced.linearCombRows(-mreduced.m[i][j], j, i);
							}
							// if it is in the end place, then we want to make the pivot 1 and say we've
							// switched the row
							if ((double) Math.round(mreduced.m[i][i] * 100000d) / 100000d != 0.0) {
								ToBeOne = mreduced.m[i][i];
								mreduced = mreduced.scalarTimesRow(1.0 / ToBeOne, i);
								alreadyone = true;
							}
							// if there is a zero in the end place, then we know there is no entry in that
							// column
							else {
								mreduced.m[i][i] = 0.0;
								alreadyone = true;
							}
							break;
						}
					}
					// if it is not the last entry in that row, then we want to move onto the next
					// entry
					if (k + 1 < mreduced.m[i].length) {
						ToBeOne = mreduced.m[i][k + 1];
					}
					// if the pivot is already one, then we want to move onto the next row (break)
					if (mreduced.m[i][i] == 1.0) {
						break;
					}
				}
				// if the row hasn't been switched and it does not start with a 0, make it start
				// with a 1, then move onto the next row (break)
				else if (alreadyone == false) {
					mreduced = mreduced.scalarTimesRow(1.0 / ToBeOne, i);
					break;
				}
			}
			alreadyone = false;
		}

		// now REDUCES it
		for (int i = 1; i < mreduced.m.length; i++) {
			// whesre the 1 is normally
			double pivot = mreduced.m[i][i];
			int pivotcolumn = i;
			for (int k = i; k < mreduced.m[i].length; k++) {
				// if the pivot is zero, then go to the next place:
				if (pivot == 0.0) {
					pivot = mreduced.m[i][k];
					pivotcolumn = k;
				}
				// the pivot is one, so you add the rows to reduce it:
				else {
					for (int j = 0; j < i; j++) {
						mreduced = mreduced.linearCombRows(-mreduced.m[j][pivotcolumn], i, j);
					}
					break;
				}
			}
		}

		// no negative zeros
		for (int i = 0; i < mreduced.m.length; i++) {
			for (int j = 0; j < mreduced.m[i].length; j++) {
				if ((double) Math.round(mreduced.m[i][j] * 100000d) / 100000d == -0.00000) {
					mreduced.m[i][j] = 0.0;
				}
			}
		}

		return mreduced;
	}

	/**
	 * Inverts a Matrix
	 * 
	 * @return Inverted Matrix
	 */
	public Matrix invert() {
		// create matrix so we can invert it
		Matrix minvert = new Matrix(r, c);
		// fills that matrix with the inputed entries
		minvert = this;
		// creates matrix that will later be 1/2 the identiy matrix to row reduce
		Matrix minvertFull = new Matrix(r, 2 * c);
		// use this matrix to take only the inverted matrix from minterFull
		Matrix minverted = new Matrix(r, c);

		// sets the first half of the later reduced matrix with the inputted entries
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				minvertFull.setEntry(i, j, minvert.m[i][j]);
			}
		}
		// adds the identity matrix to the right of the inputted matrix
		for (int i = 0; i < minvertFull.m.length; i++) {
			for (int j = c; j < minvertFull.m[i].length; j++) {
				if (j - c == i) {
					minvertFull.setEntry(i, j, 1.0);
				}
			}
		}
		// row reduce the matrix with the identity matrix on the right so it gets the
		// inverse
		minvertFull = minvertFull.rowreduce();
		// creating a matrix that is only the second half of columns
		// this is the inverse matrix
		for (int i = 0; i < r; i++) {
			for (int j = c; j < minvertFull.m[i].length; j++) {
				minverted.setEntry(i, j - c, minvertFull.m[i][j]);
			}
		}
		// makes the inputed matrix equal to the inverse
		// but this does not change the original
		minvert = minverted;

		// returned inverted matrix
		return minvert;
	}

}
