/******************************************************************************
 *  Compilation:  javac E10_VisualCounter.java
 *  Execution:    java E10_VisualCounter N max
 *  Dependencies: StdOut 
 *
 *  Description: 1.2.10 Develop a class VisualCounter that allows both increment and decrement
 *  operations. Take two arguments N and max in the constructor, where N specifies the
 *  maximum number of operations and max specifies the maximum absolute value for
 *  the counter. As a side effect, create a plot showing the value of the counter each time its
 *  tally changes. 
 *
 *  Example execution: 
 *  % java E10_VisualCounter 5000 100
 *	Final tally: -14
 *	
 ******************************************************************************/
package chapter_1.section_2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Jefoliva
 */
public class E10_VisualCounter {
	private int tally;
	private int N;
	private int max;

	E10_VisualCounter(int N, int max) {
		this.N = N;
		this.max = max;
		setCanvas();
	}

	private void setCanvas() {
		StdDraw.setCanvasSize(700, 700);
		StdDraw.setXscale(-max, max);
		StdDraw.setYscale(-5, 5);
		StdDraw.setPenRadius(0.005);
		StdDraw.show();
	}

	public void increment() {
		if(Math.abs(tally) < max && N != 0) {
			tally++;
			N--;
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.point(tally, StdRandom.uniform(-0.5, 0.5));
		}
		else if(Math.abs(tally) > max)
			StdOut.println("Maximum absolute value reached.");
		else 
			StdOut.println("Reached limit of allowed operations");
	}

	public void decrement() {
		if(Math.abs(tally) < max && N != 0) {
			tally--;
			N--;
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.point(tally, StdRandom.uniform(-0.5, 0.5));
		}
		else if(Math.abs(tally) > max)
			StdOut.println("Maximum absolute value reached.");
		else 
			StdOut.println("Reached limit of allowed operations");
	}

	public int tally() {
		return tally;
	}

	@Override
	public String toString() {
		return "tally: " + tally;
	}


	public static void main(String[] args) {
		if(args.length != 2) {
			StdOut.println("Usage: E10_VisualCounter N max");
			return;
		}

		int N = Integer.parseInt(args[0]);
		int max = Integer.parseInt(args[1]);
		var visualCounter = new E10_VisualCounter(N, max);

		for(int i = 0; i < N; i++) {
			// Change in x axis represent increment or decrement operations
		    // A red dot is an increment, a blue one, a decrement
		    // changes in y axis is only for visualization purposes
			if(StdRandom.bernoulli(0.5))  visualCounter.increment();
			else 				          visualCounter.decrement();
		}

		StdOut.println("Final " + visualCounter);
	}
}