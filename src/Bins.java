import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Runs a number of algorithms that try to fit files onto disks.
 */
public class Bins {
    public static final String DATA_FILE = "example.txt";
    // all possible algorithms to compare --- add new instances here!
    private static WorstFitAlgorithm algortihmsToCompare[] = { 
        new WorstFitAlgorithm(),
        new WorstFitDecreasingAlgorithm()
    };


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
     * Returns sum of all values in given list.
     */
    public int getTotal (List<Integer> data) {
        int total = 0;
        for (int d : data) {
            total += d;
        }
        return total;
    }


    /**
     * The main program.
     */
    public static void main (String args[]) {
        Bins b = new Bins();
        Scanner input = new Scanner(Bins.class.getClassLoader().getResourceAsStream(DATA_FILE));
        List<Integer> data = b.readData(input);
        System.out.println("total size = " + b.getTotal(data) / 1000000.0 + "GB");

        for (WorstFitAlgorithm al : algortihmsToCompare) {
        	al.fitDisksAndPrint(data);
            al.fitDisksAndPrint(data, decideSort -> Collections.shuffle(decideSort));
        }
    }
}
