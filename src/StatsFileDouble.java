import java.util.SortedMap;

//mock stats file class for dependency injection
public class StatsFileDouble extends StatsFile{

    private SortedMap<Integer, Integer> statsMapDouble;

    public StatsFileDouble(SortedMap<Integer, Integer> stats){
        statsMapDouble = stats;
    }

    @Override
    public int numGames(int numGuesses){
        return statsMapDouble.getOrDefault(numGuesses, 0);
    }

    @Override
    public int maxNumGuesses(){
        return statsMapDouble.isEmpty() ? 0:statsMapDouble.lastKey();
    }

}
