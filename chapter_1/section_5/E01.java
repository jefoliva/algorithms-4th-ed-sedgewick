/******************************************************************************
 *  Compilation:  javac E01.java
 *  Execution:    java E01
 *  Dependencies: StdIn StdOut
 *  
 *  Description: 1.5.1 Show the contents of the id[] array and the number of 
 *  times the array is accessed for each input pair when you use quick-find for
 *  the sequence 9-0 3-4 5-8 7-2 2-1 5-7 0-3 4-2
 *
 *  Example execution:
 *  % java E01 < E01data.txt
 *  9-0: [ 0 1 2 3 4 5 6 7 8 0 10 11 12 13 14 15 ] accesses: 19
 *  5-8: [ 0 1 2 4 4 8 6 7 8 0 10 11 12 13 14 15 ] accesses: 19
 *  7-2: [ 0 1 2 4 4 8 6 2 8 0 10 11 12 13 14 15 ] accesses: 19
 *  2-1: [ 0 1 1 4 4 8 6 1 8 0 10 11 12 13 14 15 ] accesses: 20
 *  5-7: [ 0 1 1 4 4 1 6 1 1 0 10 11 12 13 14 15 ] accesses: 20
 *  0-3: [ 4 1 1 4 4 1 6 1 1 4 10 11 12 13 14 15 ] accesses: 20
 *  4-2: [ 1 1 1 1 1 1 6 1 1 1 10 11 12 13 14 15 ] accesses: 22
 *  Components: 8
 ******************************************************************************/
package chapter_1.section_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author jefoliva
 */
 
public class E01 {
	public static void main(String[] args) {
		UF.main(args);
	}
}

class UF {
	int[] id;
	int count;

	public UF(int N) {
		count = N;
		id = new int[N];

		for(int i = 0; i < id.length; i++) 
			id[i] = i;
	}

	public int count() { return count; }

	public int find(int p) {
		return id[p];
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int union(int p, int q) {
		int pID = find(p);
		int qID = find(q);
		int accesses = 2;		// 2 array accesses por find(p) and find(q)	

		if(pID == qID) return accesses;

		for(int i = 0; i < id.length; i++) {
			if(id[i] == pID) {
				id[i] = qID;

				accesses += 2;
			} else {
				accesses++;
			}
		}
		count--;
		return accesses;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		UF uf = new UF(N);

		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if(p == q) continue;

			int accesses = uf.union(p, q);

			System.out.printf("%d-%d: [ ", p, q);
			for(int i : uf.id) {
				System.out.print(i + " ");
			}
			System.out.println(" ] accesses: " + accesses);
		}
		System.out.println("Components: " + uf.count());
	}
}