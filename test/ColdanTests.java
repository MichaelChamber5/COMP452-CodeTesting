import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

public class ColdanTests {

    @Test
    void HumanGuessesGameTest(){
        HumanGuessesGame game = new HumanGuessesGame(300);

        assertEquals(GuessResult.LOW, game.makeGuess(299));
        assertEquals(GuessResult.LOW, game.makeGuess(100));
        assertEquals(GuessResult.LOW, game.makeGuess(Integer.MIN_VALUE));
        assertEquals(3, game.getNumGuesses());

        assertEquals(GuessResult.HIGH, game.makeGuess(301));
        assertEquals(GuessResult.HIGH, game.makeGuess(500));
        assertEquals(GuessResult.HIGH, game.makeGuess(Integer.MAX_VALUE));
        assertEquals(6, game.getNumGuesses());

        assertEquals(GuessResult.CORRECT, game.makeGuess(300));

        assertFalse(game.isDone());

        assertEquals(7, game.getNumGuesses());

    }

    @Test
    void ReadAndFormatStatsLabelsTest(){
        SortedMap<Integer, Integer> info = new TreeMap<>();
        for (int i = 1; i < 80; i++){
            info.put(i,1);
        }

        int[] buckets = {1,2,3,5,7,9,12,15,20,30,50};

        StatsFileDouble stats = new StatsFileDouble(info);

        StatsPanelRefactored statsPanel = new StatsPanelRefactored();

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

    @Test
    void ReadAndFormatCheckingForOffByOneErrorTest(){
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

}
