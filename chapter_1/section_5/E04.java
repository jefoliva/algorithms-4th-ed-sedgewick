/******************************************************************************
 *  Compilation:  javac E04.java
 *  Execution:    java E04
 *  Dependencies: StdIn StdOut
 *  
 *  Description: 1.5.4 Show the contents of the sz[] and id[] arrays and the 
 *  number of array accesses for each input pair corresponding to the weighted 
 *  quick-union examples in the text (both the reference input and the worst-case 
 *  input).
 *
 *  Example execution:
 *  % java E04 < data/referece-input.txt
 *  4-3: 5 accesses  id : 0 1 2 4 4 5 6 7 8 9  sz : 1 1 1 1 2 1 1 1 1 1
 *  3-8: 7 accesses  id : 0 1 2 4 4 5 6 7 4 9  sz : 1 1 1 1 3 1 1 1 1 1
 *  6-5: 5 accesses  id : 0 1 2 4 4 6 6 7 4 9  sz : 1 1 1 1 3 1 2 1 1 1
 *  9-4: 7 accesses  id : 0 1 2 4 4 6 6 7 4 4  sz : 1 1 1 1 4 1 2 1 1 1
 *  2-1: 5 accesses  id : 0 2 2 4 4 6 6 7 4 4  sz : 1 1 2 1 4 1 2 1 1 1
 *  8-9: 6 accesses  id : 0 2 2 4 4 6 6 7 4 4  sz : 1 1 2 1 4 1 2 1 1 1
 *  5-0: 7 accesses  id : 6 2 2 4 4 6 6 7 4 4  sz : 1 1 2 1 4 1 3 1 1 1
 *  7-2: 7 accesses  id : 6 2 2 4 4 6 6 2 4 4  sz : 1 1 3 1 4 1 3 1 1 1
 *  6-1: 7 accesses  id : 6 2 6 4 4 6 6 2 4 4  sz : 1 1 3 1 4 1 6 1 1 1
 *  1-0: 8 accesses  id : 6 2 6 4 4 6 6 2 4 4  sz : 1 1 3 1 4 1 6 1 1 1
 *  6-7: 6 accesses  id : 6 2 6 4 4 6 6 2 4 4  sz : 1 1 3 1 4 1 6 1 1 1 
 *  Components: 2    

 *  % java E04 < data/worst-case-input.txt
 *  0-1: 5 accesses  id : 0 0 2 3 4 5 6 7  sz : 2 1 1 1 1 1 1 1
 *  2-3: 5 accesses  id : 0 0 2 2 4 5 6 7  sz : 2 1 2 1 1 1 1 1
 *  4-5: 5 accesses  id : 0 0 2 2 4 4 6 7  sz : 2 1 2 1 2 1 1 1
 *  6-7: 5 accesses  id : 0 0 2 2 4 4 6 6  sz : 2 1 2 1 2 1 2 1
 *  0-2: 5 accesses  id : 0 0 0 2 4 4 6 6  sz : 4 1 2 1 2 1 2 1
 *  4-6: 5 accesses  id : 0 0 0 2 4 4 4 6  sz : 4 1 2 1 4 1 2 1
 *  0-4: 5 accesses  id : 0 0 0 2 0 4 4 6  sz : 8 1 2 1 4 1 2 1
 *  Components: 1                                         
 ******************************************************************************/
package chapter_1.section_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author jefoliva
 */
 
public class E04 {
	public static void main(String[] args) {
		UF04.main(args);
	}
}

class UF04 {
	private int[] id;
	private int[] sz;
	private int count;
	private int accesses;

	public UF04(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];

		for(int i = 0; i < id.length; i++) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	public int count() { return count; }

	private int find(int p) {
		// Find component name.
		while (p != id[p]) {
			p = id[p];
			accesses += 2;
		}
		accesses++;
		return p;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int union(int p, int q) {
		this.accesses = 0;

		// Give p and q the same root.
		int i = find(p);
		int j = find(q);
		if(i == j) return accesses;

		// Make smaller root point to larger root
		if   (sz[i] < sz[j]) { 
			id[i] = j; 
			sz[j] += sz[i]; 

			accesses += 5;
		}
		else { 
			id[j] = i; sz[i] += sz[j]; 
			accesses += 3;
		}

		count--;

		return accesses;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		UF04 uf = new UF04(N);

		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if(p == q) continue;

			int accesses = uf.union(p, q);

			System.out.printf("%d-%d: %d accesses  ", p, q, accesses);

			System.out.print("id : ");
			for(int i : uf.id) {
				System.out.print(i + " ");
			}

			System.out.print(" sz : ");
			for(int i : uf.sz) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		System.out.println("Components: " + uf.count());
	}
}