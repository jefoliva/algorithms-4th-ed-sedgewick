/******************************************************************************
 *  Compilation:  javac Exercise_35_DiceSimulation
 *  Execution:    java Exercise_35_DiceSimulation N
 *  Dependencies: StdOut Counter StdRandom
 *
 *  Description: 1.1.35 Dice simulation. The following code computes the exact probability distribution for the sum of two dice:
 *	int SIDES = 6;
 *	double[] dist = new double[2*SIDES+1];
 *	for (int i = 1; i <= SIDES; i++)
 *		for (int j = 1; j <= SIDES; j++)
 *			dist[i+j] += 1.0;
 *
 *	for (int k = 2; k <= 2*SIDES; k++)
 *		dist[k] /= 36.0;
 *
 *	The value dist[i] is the probability that the dice sum to k. Run experiments to validate this calculation simulating 
 *  N dice throws, keeping track of the frequencies of occurrence of each value when you compute the sum of two random 
 *  integers between 1 and 6. How large does N have to be before your empirical results match the exact results
 *	to three decimal places?

 ANSWER: N should be around 2,000,000 to meeet expected values
 ******************************************************************************/
package chapter_1.section_1;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Arrays;

/**
 * @author Jefoliva
 */

public class Exercise_35_DiceSimulation {

    public static void main(String[] args) {
    	int SIDES = 6;
    	int N = Integer.parseInt(args[0]);
	 	double[] dist = new double[2*SIDES+1];
	 	for (int i = 1; i <= SIDES; i++)
	 		for (int j = 1; j <= SIDES; j++)
	 			dist[i+j] += 1.0;
	 		
	 	
	 	for (int k = 2; k <= 2*SIDES; k++) 
	 		dist[k] /= 36.0;
	 	

	 	Counter[] rolls  = new Counter[SIDES*2 + 1];
	 	for (int i = 1; i <= SIDES*2 ; i++ ) 
	 		rolls[i] = new Counter(i + "s");

	 	for(int i = 0; i < N; i++) {
	 		int diceOne = StdRandom.uniform(1, SIDES+1);
	 		int diceTwo = StdRandom.uniform(1, SIDES+1);

	 		rolls[diceOne + diceTwo].increment();
	 	}
	 	System.out.println("\n   Result: \t Expected");
	 	
	 	for (int i = 1; i <= SIDES*2; i++ ) { 
	 		System.out.printf("%9.3f", (rolls[i].tally() * 1.0) / (N*1.0));
	 		System.out.print("\t");
	 		System.out.printf("%9.3f\n", dist[i]);
	 	}
    }
}