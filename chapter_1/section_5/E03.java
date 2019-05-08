/******************************************************************************
 *  Compilation:  javac E03.java
 *  Execution:    java E03
 *  Dependencies: StdIn StdOut
 *  
 *  Description: Do Exercise 1.5.1, but use weighted quick-union (page 228)
 *
 *  Example execution:
 *  % java E03 < E01data.txt
 *  9-0: [ 0 1 2 3 4 5 6 7 8 0 ] accesses: 3
 *  3-4: [ 0 1 2 4 4 5 6 7 8 0 ] accesses: 3
 *  5-8: [ 0 1 2 4 4 8 6 7 8 0 ] accesses: 3
 *  7-2: [ 0 1 2 4 4 8 6 2 8 0 ] accesses: 3
 *  2-1: [ 0 1 1 4 4 8 6 2 8 0 ] accesses: 3
 *  5-7: [ 0 1 1 4 4 8 6 2 1 0 ] accesses: 9
 *  0-3: [ 4 1 1 4 4 8 6 2 1 0 ] accesses: 5
 *  4-2: [ 4 1 1 4 1 8 6 2 1 0 ] accesses: 5 
 *  Components: 2                                                  
 ******************************************************************************/
package chapter_1.section_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author jefoliva
 */
 
public class E03 {
	public static void main(String[] args) {
		UF03.main(args);
	}
}

class UF03 {
	private int[] id;
	private int[] sz;
	private int count;
	private int accesses;

	public UF03(int N) {
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
		if   (sz[i] < sz[j]) 	{ id[i] = j; sz[j] += sz[i]; }
		else 					{ id[j] = i; sz[i] += sz[j]; }

		accesses++;			// id is accesed only one time
		count--;

		return accesses;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		UF03 uf = new UF03(N);

		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if(p == q) continue;

			int accesses = uf.union(p, q);

			System.out.printf("%d-%d: [ ", p, q);
			for(int i : uf.id) {
				System.out.print(i + " ");
			}
			System.out.println("] accesses: " + accesses);
		}
		System.out.println("Components: " + uf.count());
	}
}