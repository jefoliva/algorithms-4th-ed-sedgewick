/******************************************************************************
 *  Compilation:  javac QuickFindUF.java
 *  Execution:    java QuickFindUF < tinyUF.txt
 *  Dependencies: StdIn StdOut
 *  
 *  Description: 1.5.7 Develop classes QuickFindUF and QuickFindUF that 
 *  implement quick-union and quick-find, respectively.
 *
 *  Example execution:
 *  % java QuickFindUF < tinyUF.txt
 *  4 3               
 *  3 8               
 *  6 5               
 *  9 4               
 *  2 1               
 *  5 0               
 *  7 2               
 *  6 1               
 *  Componentents: 2                                      
 ******************************************************************************/
package chapter_1.section_5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  @author jefoliva
 */

public class QuickFindUF {
	int[] id;
	int count;

	QuickFindUF(int N) {
		id = new int[N];
		count = N;

		for(int i = 0 ; i < id.length; i++)
			id[i] = i;
	}

	public int count() { return count; }

	public boolean connected(int p, int q) { 
		return find(p) == find(q);
	}

	public int find(int p) {
		return id[p];
	}

	public void union(int p, int q) {
		int pID = find(p);
		int qID = find(q);

		if (pID == qID) return;

		for (int i = 0; i < id.length; i++)
			if (id[i] == pID) id[i] = qID;
		count--;
	}

	public static void main(String[] args) {
		int N = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(N);

		while(!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();

			if(uf.connected(p, q))
                continue;
        
            uf.union(p, q);
            System.out.println(p + " " + q);
		}
		System.out.println("Componentents: " + uf.count()); 
	}
}