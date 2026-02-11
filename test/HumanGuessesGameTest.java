import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HumanGuessesGameTest {

    @Test
    void HumanGuessesLowGuessTest(){
        HumanGuessesGame game = new HumanGuessesGame(300);

        assertEquals(GuessResult.LOW, game.makeGuess(299));
        assertEquals(GuessResult.LOW, game.makeGuess(100));
        assertEquals(GuessResult.LOW, game.makeGuess(Integer.MIN_VALUE));
        assertEquals(3, game.getNumGuesses());

    }
    @Test
    void HumanGuessesGameHighTest(){
        HumanGuessesGame game = new HumanGuessesGame(300);

        assertEquals(GuessResult.HIGH, game.makeGuess(301));
        assertEquals(GuessResult.HIGH, game.makeGuess(500));
        assertEquals(GuessResult.HIGH, game.makeGuess(Integer.MAX_VALUE));
        assertEquals(3, game.getNumGuesses());
    }

    @Test
    void HumanGuessesGameCorrectTest(){
        HumanGuessesGame game = new HumanGuessesGame(300);
        assertEquals(1, game.getNumGuesses());

        assertEquals(GuessResult.CORRECT, game.makeGuess(300));
    }




}
