/**
 * Lösung Übungsaufgabe Schweizer Flagge
 * @author Ben Fiedler
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class SwissFlagProgram {
	/**
	 * Totally overengineered class for drawing Swiss flags.
	 * 
	 * Swiss flag spec:
	 * https://en.wikipedia.org/wiki/Flag_of_Switzerland
	 * 
	 * Ratio: 1:1
	 * Cross length is 5/8 times flag length.
	 * Cross arms are 7/6 times as long as the square in the middle.
	 * 
	 * For serious submission, see bottom
	 * @author Ben Fiedler
	 */
	static class SwissFlag {
		// The ratio of a character's height to a character's width.
		// Useful for pretty output. My system has a ratio of 23/12,
		// so I picked 2.
		final int htwr = 2;
		private int middle;
		private int cross;
		private int arm;
		private int border;
		
		private boolean useUtf8;
		private boolean drawBorder;
		private boolean color;
		
		boolean[][] flag;
		
		/**
		 * Construct a Swiss flag with a given resolution.
		 * Depending on the input, the flag will vary in size as it
		 * is optimized to resemble the original flag as closely
		 * as possible.
		 * 
		 * Note: Coloring doesn't work on Windows.
		 */
		public SwissFlag(int size, boolean drawBorder, boolean useUtf8, boolean color) {
			double exactMiddle = (5.0 / 8.0) * size;
			double exactCross = exactMiddle / 3.0;
			double exactArm = exactCross * 7.0 / 6.0;
			double exactBorder = (size - exactMiddle) / 2.0;
			
			cross = (int)exactCross;
			arm = (int)exactArm;
			middle = 2 * arm + cross;
			border = (int)exactBorder;
			size = middle + 2 * border;
			
			this.drawBorder = drawBorder;
			this.useUtf8 = useUtf8;
			this.color = color;
			
			flag = new boolean[size][size];
			
			//System.out.println(size);
			//System.out.println(middle);
			//System.out.println(cross);
			//System.out.println(arm);
			//System.out.println(border);
			
			createFlag();
		}
		
		/**
		 * Actually create the flag and store the pixels in an array of array of bool.
		 * This is done for extensibility purposes.
		 */
		private void createFlag() {
			for (int i = 0; i < flag.length; i++) {
				for (int j = 0; j < flag.length; j++) {
					flag[i][j] = isInCross(i+1, j+1) || isInCross(j+1, i+1);
				}
			}
		}
		
		/**
		 * Repeat a given string n times
		 */
		private String repeat(String source, int repetitions) {
			return String.join("", Collections.nCopies(repetitions, source));
		}
		
		/**
		 * Check whether a given pixel (x, y) is inside the flag or not.
		 */
		private boolean isInCross(int x, int y) {
			return (x > border && y > border + arm)
			    && (x <= border + middle && y <= border + arm + cross); 
		}
		
		/**
		 * Draw a horizontal border.
		 * @return
		 */
		private String horizontalBorder() {
			return (color ? "\u001b[0m" : "") + "+" + repeat("-", htwr * flag.length) + "+"; 
		}
		
		private String redPixel() {
			return (color ? "\u001b[31m" : "") + (useUtf8 ? "\u2588" : "*");
		}

		private String whitePixel() {
			return (color ? "\u001b[37m" : "") + (color && useUtf8 ? "\u2588" : " ");
		}

		/**
		 * Render the flag a string scaling it correctly.
		 * @return Flag as ascii art.
		 */
		public String drawFlag() {
			StringBuilder result = new StringBuilder();
			
			if (drawBorder) result.append(horizontalBorder() + "\n");
			
			for (int i = 0; i < flag.length; i++) {
				if (drawBorder)
					result.append((color ? "\u001b[0m" : "") + "|");
				for (int j = 0; j < flag.length; j++) {
					// scale the rows according the the height-width ratio given
					result.append(repeat(flag[i][j] ? whitePixel() : redPixel(), htwr));
				}
				result.append((color ? "\u001b[0m" : "") + (drawBorder ? "|" : "") + "\n");
			}
			
			if (drawBorder) result.append(horizontalBorder());
			return result.toString() + (color ? "\001b[0m" : "");
		}
		
		@SuppressWarnings("serial")
		class Cross extends JComponent {
			private int scaling;
			public Cross(int scaling) { this.scaling = scaling; }
			
			@Override
			public void paint(Graphics g) {
				g.setColor(Color.white);
				g.fillRect(border * scaling, (border + arm) * scaling, middle * scaling, cross * scaling);
				g.fillRect((border + arm) * scaling, border * scaling, cross * scaling, middle * scaling);
			}
			
		}
		
		public void drawPicture(int scaling) {
			JFrame frame = new JFrame();
			frame.setSize(scaling * flag.length, scaling * flag.length);
			frame.setVisible(true);
			frame.getContentPane().setBackground(Color.red.darker());
			frame.getContentPane().add(new Cross(scaling));
		}
	}
	
	/**************************************************************************************/
	
	/**
	 * Does what it's named to do.
	 */
	private static void drawHorizontalBorder(int len) {
		String s = "+";
		for (int i = 0; i < len * 2; i++) {
			s += "-";
		}
		System.out.println(s + "+");
	}
	
	/**
	 * Abstract the printing of a white pixel.
	 */
	private static void printWhite() {
		System.out.print("  ");
	}
	
	/**
	 * Abstract the printing of a red pixel.
	 */
	private static void printRed() {
		System.out.print("**");
	}
	
	/**
	 * Checks whether a given pixel at (x, y) is in the cross.
	 */
	private static boolean isInCross(int x, int y, int border, int middle, int arm, int cross) {
		return x > border && y > border + arm
				&& x <= border + middle && y <= border + arm + cross;
	}
	/**
	 * Ok just grade this if you like.
	 * The size of the flag will not be exact in most cases as
	 * it will generate better looking output like this.
	 * 
	 * But looking at the often passed parameters, these would be better fit in a class
	 * to eliminate the need to pass them around all the time.
	 * @param size of the flag
	 */
	private static void drawSwissFlag(int size) {
		// Swiss flag specification
		double exactMiddle = (5.0 / 8.0) * size;
		double exactCross = exactMiddle / 3.0;
		double exactArm = exactCross * 7.0 / 6.0;
		double exactBorder = (size - exactMiddle) / 2.0;
		// Rounding to ints
		int cross = (int)exactCross;
		int arm = (int)exactArm;
		int middle = 2 * arm + cross;
		int border = (int)exactBorder;
		size = middle + 2 * border;
		drawHorizontalBorder(size);
		for (int i = 1; i <= size; i++) {
			System.out.print('|');
			for (int j = 1; j <= size; j++) {
				if (isInCross(i, j, border, middle, arm, cross) || isInCross(j, i, border, middle, arm, cross)) {
					printWhite();
				} else {
					printRed();
				}
			}
			System.out.println('|');
		}
		drawHorizontalBorder(size);
	}
	
	/*************************************************************************************************************/
	
	public static void main(String[] args) {
		//System.out.println("\u2588 \u2588\u2588s     ");
		//System.out.println(redPixel());
		//System.out.println("\u2588");
		SwissFlag s = new SwissFlag(40, true, true, false);
		System.out.println(s.drawFlag());
		s.drawPicture(23);
		//drawSwissFlag(40); // equiv to new SwissFlag(40, true, false, false).drawFlag();
	}
}
