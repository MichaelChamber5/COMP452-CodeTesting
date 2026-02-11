import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class StatsPanelTest {

    @Test
    void GetResultsTest(){

        //USING DEPENDENCY INJECTION

        SortedMap<Integer, Integer> info = new TreeMap<>();
        for (int i = 1; i < 80; i++){
            info.put(i,1);
        }

        int[] buckets = {1,2,3,5,7,9,12,15,20,30,50};

        //Inject CSV dependency into StatsFile
        StatsFileDouble stats = new StatsFileDouble(info);

        StatsPanelRefactored statsPanel = new StatsPanelRefactored();

        //Inject StatsFile dependency into the statsPanelResults function
        Integer[] results = statsPanel.getResults(buckets, stats);

        assertEquals(1, results[0]); //1
        assertEquals(1, results[1]); //2
        assertEquals(2, results[2]); //3-4
        assertEquals(2, results[3]); //5-6
        assertEquals(2, results[4]); //7-8
        assertEquals(3, results[5]); //9-11
        assertEquals(3, results[6]); //12-14
        assertEquals(5, results[7]); //15-19
        assertEquals(10, results[8]); //20-29
        assertEquals(20, results[9]); //30-49
        assertEquals(30, results[10]);//50+
    }

    //This test tests for what I suspect the issue is based off the failure of the first test
    @Test
    void GetResultsOffByOneErrorTest(){
        SortedMap<Integer, Integer> info = new TreeMap<>();
        for (int i = 1; i < 80; i++){
            info.put(i,1);
        }

        int[] buckets = {1,2,3,5,7,9,12,15,20,30,50};

        StatsFileDouble stats = new StatsFileDouble(info);

        StatsPanelRefactored statsPanel = new StatsPanelRefactored();

        Integer[] results = statsPanel.getResults(buckets, stats);

        assertEquals(2, results[0]); //1-2
        assertEquals(2, results[1]); //2-3
        assertEquals(3, results[2]); //3-5
        assertEquals(3, results[3]); //5-7
        assertEquals(3, results[4]); //7-9
        assertEquals(4, results[5]); //9-12
        assertEquals(4, results[6]); //12-15
        assertEquals(6, results[7]); //15-20
        assertEquals(11, results[8]); //20-30
        assertEquals(21, results[9]); //30-50

//      Even while accounting for an off by one counting error, this particular test STILL fails
//      (likely due to another, separate off by one error)
        assertEquals(30, results[10]); //50+
    }

    @Test
    void DoesNotAcceptNegativeValuesTest(){
        //Testing for exception
        SortedMap<Integer, Integer> info = new TreeMap<>();
        info.put(-1, 1);

        int[] buckets = {1,2,3,5,7,9,12,15,20,30,50};

        StatsFileDouble stats = new StatsFileDouble(info);

        StatsPanelRefactored statsPanel = new StatsPanelRefactored();

        assertThrows(IllegalArgumentException.class, () -> {
            statsPanel.getResults(buckets, stats);
        });
    }
    @Test
    void DoesNotAcceptCrazyLargeNumbers(){
        //Testing for exception

        //kinda set this up arbitrarily, where a "crazy large guess number" is
        //a value that takes the function over 10 seconds to parse.
        SortedMap<Integer, Integer> info = new TreeMap<>();

        //really big number
        info.put(Integer.MAX_VALUE, 1);
        int[] buckets = {1,2,3,5,7,9,12,15,20,30,50};
        StatsFileDouble stats = new StatsFileDouble(info);
        StatsPanelRefactored statsPanel = new StatsPanelRefactored();

        assertThrows(IllegalArgumentException.class, () -> {
            CompletableFuture<Integer[]> future = CompletableFuture.supplyAsync(() -> {
                Integer[] results = statsPanel.getResults(buckets, stats);
                return results;
            });
            try {
                Integer[] results = future.get(10000, TimeUnit.MILLISECONDS);
            }
            catch(Exception e){
                throw e;
            }
        });
    }



}
