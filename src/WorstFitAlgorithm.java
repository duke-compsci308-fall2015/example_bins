import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This class represents worst-fit algorithms for allocating files to disks.
 *
 * @author rcd
 */
public class WorstFitAlgorithm {

    /**
     * Default constructor
     */
    public WorstFitAlgorithm () {}

    /**
     * Allocates given files to the fewest number of disks.
     *
     * @param data collection of files to be allocated to disks
     */
    public void fitDisksAndPrint (List<Integer> data, OrganizeData organizer, String description) {
        List<Integer> copy = new ArrayList<>(data);
        organizer.organizeData(data);
        Collection<Disk> disks = addFiles(copy);
        printResults(disks, description);
    }

    /**
     * Arrange given data in preparation for fitting disks.
     *
     * @param disks collection of disks to be printed
     */
    protected void organizeData (List<Integer> data) {
        // by default, do nothing
    }

    // add files to the collection of Disks
    private Collection<Disk> addFiles (List<Integer> data) {
        PriorityQueue<Disk> pq = new PriorityQueue<>();
        pq.add(new Disk());
        for (Integer size : data) {
            Disk d = pq.peek();
            if (d.canFit(size)) {
                pq.poll();
            }
            else {
                d = new Disk();
            }
            d.add(size);
            pq.add(d);
        }
        return pq;
    }

    // print contents of given collection and a header, description
    private void printResults (Collection<Disk> disks, String description) {
        System.out.println();
        System.out.println("\n" + description + " method");
        System.out.println("number of disks used: " + disks.size());
        for (Disk d : disks) {
            System.out.println(d);
        }
    }
}
