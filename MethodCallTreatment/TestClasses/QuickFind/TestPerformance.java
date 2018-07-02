import static org.junit.Assert.*; 

import org.junit.*; 



public  class  TestPerformance {
	
	private static final String TEST_DATA_LOCATION = "TestData";

	

	@Test
	public void testSmallData() {
		//System.out.println("test small size data:");
		testData(TEST_DATA_LOCATION + "/tinyUF.txt");
	}

	
	@Test
	public void testMediumData() {
		//System.out.println("test medium size data:");
		testData(TEST_DATA_LOCATION + "/mediumUF.txt");
	}

	
	@Test
	public void testLargeData() {
		//System.out.println("testing large size data:");
		testData(TEST_DATA_LOCATION + "/largeUF.txt");
	}

	
	private void testData(String filename){
		In in = new In(filename);
        int N = in.readInt();
        Stopwatch s = new Stopwatch();
        UnionFind uf = new UnionFind(N);

        // read in a sequence of pairs of integers (each in the range 0 to N-1),
         // calling find() for each pair: If the members of the pair are not already
        // call union() and print the pair.
        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
           
        }
       // StdOut.println(s.elapsedTime() + " time");
        
	}


}
