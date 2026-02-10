import javax.swing.*;
import java.util.ArrayList;

public class StatsPanelDouble extends JPanel {
    public Integer[] getResults(int[] bins, StatsFile stats){
        Integer[] returnInfo = new Integer[bins.length];

        for(int binIndex=0; binIndex<bins.length; binIndex++){
            final int lowerBound = bins[binIndex];
            int numGames = 0;

            if(binIndex == bins.length-1){
                // last bin
                // Sum all the results from lowerBound on up
                for(int numGuesses=lowerBound; numGuesses<stats.maxNumGuesses(); numGuesses++){
                    numGames += stats.numGames(numGuesses);
                }
            }
            else{
                int upperBound = bins[binIndex+1];
                for(int numGuesses=lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }
            returnInfo[binIndex] = numGames;
        }

        return returnInfo;
    }
}
