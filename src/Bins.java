import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins {
    
	//Make variables used in abstracted methods global
	private static int total;
    private static PriorityQueue<Disk> pq; 
    private static List<Integer> data; 
    private static int diskId;
    
	public static final String DATA_FILE = "example.txt";

    /**
     * Reads list of integer data from the given input.
     *
     * @param input tied to an input source that contains space separated numbers
     * @return list of the numbers in the order they were read
     */
    public List<Integer> readData (Scanner input) {
        List<Integer> results = new ArrayList<Integer>();
        while (input.hasNext()) {
            results.add(input.nextInt());
        }
        return results;
    }

    /**
     * The main program.
     */
    public static void main (String args[]) {
        Bins b = new Bins();
        Scanner input = new Scanner(Bins.class.getClassLoader().getResourceAsStream(DATA_FILE));
        data = b.readData(input);

        pq = new PriorityQueue<Disk>();
        
        //insert without sorting
        binInsertion(false);
        // Include a line insertion instead of separate system.out.println
        System.out.println("total size = " + total / 1000000.0 + "GB \n");
        printMethodResults("worst-fit");
        printBinAndDelete();
        
        
        //insert after sorting
        binInsertion(true);
        printMethodResults("worst-fit decreasing");
        printBinAndDelete();
    }


    //Method to perform the bin insertion, varying depending on whether or not we sort 
    //the collection beforehand
    private static void binInsertion(boolean sort) {
    	if(sort){ Collections.sort(data, Collections.reverseOrder());}
    	
    	pq.add(new Disk(0));
    	diskId = 1;
    	total = 0;
    	for (Integer size : data) {
    		Disk d = pq.peek();
    		if (d.freeSpace() > size || (sort && d.freeSpace() >= size)) {
    			pq.poll();
    			d.add(size);
    			pq.add(d);
    		} else {
    			Disk d2 = new Disk(diskId);
    			diskId++;
    			d2.add(size);
    			pq.add(d2);
    		}
    		total += size;
    	}
    }
    
    //Method that prints the method results 
	private static void printMethodResults(String methodType) {
		System.out.println(methodType + " method");
        System.out.println("number of pq used: " + pq.size());
	}
	
	//Method that deletes the priority queue and prints contents 
	private static void printBinAndDelete() {
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		System.out.println();
	}
}
