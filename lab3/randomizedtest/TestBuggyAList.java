package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> ANR = new AListNoResizing<>();
        BuggyAList<Integer> BAL = new BuggyAList<>();
        for (int i = 4; i < 7; i++){
            ANR.addLast(i);
            BAL.addLast(i);
        }
        for (int i = 0; i < 3; i++){
            assertEquals(ANR.removeLast(), BAL.removeLast());
        }
    }

    @Test
    public void randomTest(){
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L1.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size1 = L1.size();
                int size2 = L2.size();
                System.out.println("size1: " + size1 + " size2: " + size2);
                assertEquals(size1, size2);
            } else if (operationNumber == 2){
                //getLast
                if (L1.size() > 0 && L2.size() > 0){
                    int last1 = L1.getLast();
                    int last2 = L2.getLast();
                    System.out.println("getLast1: " + last1 + " getLast2: " + last2);
                    assertEquals(last1, last2);
                }
            } else if (operationNumber == 3){
                //removeLast
                if(L1.size() > 0 && L2.size() > 0){
                    int last1 = L1.removeLast();
                    int last2 = L2.removeLast();
                    System.out.println("removeLast(" + last1 + ")" + " getLast2: " + last2);
                    assertEquals(last1, last2);
                }
            }
        }
    }
}
