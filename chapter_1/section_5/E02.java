/******************************************************************************
 *  Compilation:  javac E02.java
 *  Execution:    java E02
 *  Dependencies: StdIn StdOut
 *  
 *  Description: 1.5.2 Do Exercise 1.5.1, but use quick-union (page 224). In 
 *  addition, draw the forest of trees represented by the id[] array after each
 *  input pair is processed.
 *
 *  Example execution:
 *  % java E02 < E01data.txt
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
 
public class E02 {
	public static void main(String[] args) {
		UF02.main(args);
	}
}

class UF02 {
	private int[] id;
	private int count;
	private int accesses;

	public UF02(int N) {
		count = N;
		id = new int[N];

		for(int i = 0; i < id.length; i++) 
			id[i] = i;
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
		int pRoot = find(p);
		int qRoot = find(q);

		if (pRoot == qRoot) return accesses;
		id[pRoot] = qRoot;

		accesses++;
		count--;

		return accesses;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		UF02 uf = new UF02(N);

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