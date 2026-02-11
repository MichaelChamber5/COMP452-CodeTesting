import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameOverTest {

    @Test
    void SetAnswerTextTest(){

        GameResult result = new GameResult(true, 500, 12);

        GameOverPanel gameover = new GameOverPanel();

        String[] texts = gameover.setAnswerText(result);
        assertEquals("The answer was 500.", texts[0]);
        assertEquals("It took you 12 guesses.", texts[1]);

        GameResult result2 = new GameResult(false, 700, 12);

        texts = gameover.setAnswerText(result2);

        assertEquals("The answer was 700.", texts[0]);
        assertEquals("It took me 12 guesses.", texts[1]);


        GameResult result3 = new GameResult(false, 1000, 1);

        texts = gameover.setAnswerText(result3);

        assertEquals("The answer was 1000.", texts[0]);
        assertEquals("I guessed it on the first try!", texts[1]);



        GameResult result4 = new GameResult(true, 0, 1);

        texts = gameover.setAnswerText(result4);

        assertEquals("The answer was 0.", texts[0]);
        assertEquals("You guessed it on the first try!", texts[1]);

    }
}
