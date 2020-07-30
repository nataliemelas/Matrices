package MatrixNMK;

import java.util.Scanner;
import org.opensourcephysics.frames.PlotFrame;
import polyfun.Polynomial;
import MatrixNMK.Matrix;

public abstract class UserInterfaceVDM extends VerticalDifferenceMethod {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean input = true;
		boolean directions = true;
		boolean playagain = true;
		boolean here = false;
		boolean madeIt = false;

		while (playagain = true) {
			while (input == true) {
				here = false;
				int right = 0;

				System.out.println("Welcome to your own Derivative Calculator!");
				System.out.println("Here, you get to input your own polynomial.");
				System.out.println("What is the highest degree in your polynomial?");
				if (!scan.hasNextInt()) {
					directions = false;
					break;
				}
				int maxDegree = scan.nextInt();
				double[] coefs = new double[maxDegree + 1];
				System.out.println(
						"Nice one! Please input the coefficient of the term in your polynomial which has the lowest degree (x^0)");
				if (!scan.hasNextDouble()) {
					scan.nextLine();
					directions = false;
					break;
				}
				coefs[0] = scan.nextDouble();
				if (maxDegree > 0) {
					System.out.println(
							"Now, please input the coefficient of the term in your polynomial which is one degree higher (x^1)");
				}
				for (int i = 1; i < maxDegree; i++) {
					if (!scan.hasNextDouble()) {
						// scan.nextLine();
						directions = false;
						break;
					}
					coefs[i] = scan.nextDouble();
					System.out.println(
							"Now, please input the coefficient of the term in your polynomial which is one degree higher (x^"
									+ (i + 1) + ")");
					if (!scan.hasNextDouble()) {
						// scan.nextLine();
						directions = false;
						break;
					}
				}
				if (maxDegree > 0) {
					if (!scan.hasNextDouble()) {
						scan.nextLine();
						directions = false;
						break;
					}
					coefs[maxDegree] = scan.nextDouble();
				}
				Polynomial poly = new Polynomial(coefs);
				System.out.print("This is your polynomial: ");
				poly.print();
				System.out.println("");

				System.out.println(
						"Great choice of polynomial! Do you want to know the tangent line at a point, the derivative, or compare "
								+ "the accumulation function of its derivative to the derivative of its accumulation function? Please enter "
								+ "'tangent', 'derivative', or 'acc function'");
				scan.nextLine();
				String choice = scan.nextLine();
				if (choice.toLowerCase().equals("tangent")) {
					System.out.println("At what point do you want the tangent line?");
					if (!scan.hasNextDouble()) {
						directions = false;
						break;
					}
					double xCoord = scan.nextDouble();
					VDM(poly, xCoord);
					right = 1;
					madeIt = true;
				} else if (choice.toLowerCase().equals("derivative")) {
					System.out.print("The derivative of your polynomial is ");
					polyInterp(poly).print();
					right = 1;
				} else if (choice.toLowerCase().equals("acc function")) {
					InterpolationAcc(poly);
					right = 1;
				}
				System.out.println(" ");
				if (right == 0) {
					System.out.print("That was not an option. ");
				}
				System.out.println("Do you want to enter another polynomial? Please enter yes or no.");
				if (madeIt == true) {
					scan.nextLine();
				}
				String PlayAgain = scan.nextLine();
				if (PlayAgain.toLowerCase().contains("yes")) {
					System.out.println(" ");
				} else if (PlayAgain.toLowerCase().contains("no")) {
					System.out.println("Okay, bye.");
					break;
				}
			}

			if (input == false || directions == false) {
				System.out.println(
						"I'm sorry, you did not follow the directions. This polynomial is ruined. Do you want to enter another polynomial? Enter yes or no.");
				if (here == false) {
					scan.nextLine();
				}
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
