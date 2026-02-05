import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class. NOTE: I think this is done
 */
public class ComputerGuessesPanel extends JPanel {

    private int numGuesses;
    private int lastGuess;

    // upperBound and lowerBound track the computer's knowledge about the correct number
    // They are updated after each guess is made
    private int upperBound; // correct number is <= upperBound
    private int lowerBound; // correct number is >= lowerBound

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        addButton("Lower", guessMessage, () -> handleLowerGuess(guessMessage));
        addButton("Equal", guessMessage, () -> handleCorrectGuess(guessMessage, cardsPanel, gameFinishedCallback));
        addButton("Higher", guessMessage, () -> handleHigherGuess(guessMessage));

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                resetGame(guessMessage);
            }
        });
    }

    private void addButton(String text, JLabel guessMessage, Runnable f)
    {
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton button = new JButton(text);
        button.addActionListener(e -> f.run());
        this.add(button);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void handleHigherGuess(JLabel guessMessage)
    {
        lowerBound = Math.max(lowerBound, lastGuess + 1);
        makeNextGuess(guessMessage);
    }

    private void handleLowerGuess(JLabel guessMessage)
    {
        upperBound = Math.min(upperBound, lastGuess);
        makeNextGuess(guessMessage);
    }

    private void handleCorrectGuess(JLabel guessMessage, JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback)
    {
        guessMessage.setText("I guess ___.");

        // Send the result of the finished game to the callback
        GameResult result = new GameResult(false, lastGuess, numGuesses);
        gameFinishedCallback.accept(result);

        CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
        cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
    }

    private int getLastGuess(int lowerBound, int upperBound)
    {
        return (lowerBound + upperBound + 1) / 2;
    }

    private void makeNextGuess(JLabel guessMessage)
    {
        lastGuess = getLastGuess(lowerBound, upperBound);
        numGuesses++;
        guessMessage.setText("I guess " + lastGuess + ".");
    }

    private void resetGame(JLabel guessMessage)
    {
        numGuesses = 0;
        upperBound = 1000;
        lowerBound = 1;

        lastGuess = getLastGuess(lowerBound, upperBound);
        guessMessage.setText("I guess " + lastGuess + ".");
    }
}
