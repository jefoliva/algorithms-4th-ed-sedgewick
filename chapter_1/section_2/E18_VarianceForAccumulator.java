/******************************************************************************
 *  Compilation:  javac E18_VarianceForAccumulator 
 *  Execution:    java E18_VarianceForAccumulator  
 *  Dependencies: StdOut StdRandom
 *	
 *  Description: 1.2.18 Variance for accumulator. Validate that the following code, 
 *  which adds the methods var() and stddev() to Accumulator, computes both the mean 
 *  and variance of the numbers presented as arguments to addDataValue()
 *
 *  Example execution: 
 *  % java E18_VarianceForAccumulator
 *  mean: (100 values):  0.540622
 *  var: (100 values):  0.088291
 *
 *  mean: (1000 values):  0.506133
 *  var: (1000 values):  0.083240
 *
 *  mean: (10000 values):  0.503103
 *  var: (10000 values):  0.083573
 ******************************************************************************/
package chapter_1.section_2;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Jefoliva
 */

public class E18_VarianceForAccumulator {
	public static void main(String[] args) {
		Accumulator.main(new String[]{"100"});	
		Accumulator.main(new String[]{"1000"});	
		Accumulator.main(new String[]{"10000"});	
	}
}

class Accumulator {
	private double m;
	private double s;
	private int N;

	public void addDataValue(double x) {
		N++;
		s = s + 1.0 * (N-1) / N * (x - m) * (x - m);
		m = m + (x - m) / N;
	}

	public double mean()   { return m; }
	public double var()    { return s/(N - 1); }
	public double stddev() { return Math.sqrt(this.var()); }

	public static void main(String[] args) {
		if(args.length != 1) {
			StdOut.println("Usage: java E18_VarianceForAccumulator  N");
			return;
		}

		int N = Integer.parseInt(args[0]);
		Accumulator a = new Accumulator();

		for(int i = 0; i < N; i++) {
			a.addDataValue(StdRandom.uniform());
		}

		StdOut.printf("mean: (%d values):  %f\n", N, a.mean());
		StdOut.printf("var: (%d values):  %f\n", N, a.var());
		StdOut.println();
	}
}