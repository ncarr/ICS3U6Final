/* [Millenialopoly.java]
 * A Monopoly variation adapted to suit "Millenials" a.k.a. Snake People
 * @author Atharva Washimkar, adapted by Carol
 */

import java.util.Scanner;
import javax.swing.*;

class Millenialopoly {

    public static void main(String[] args) {
        // Start the shenanigans!
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel ());
        } catch (UnsupportedLookAndFeelException e) {}

        // Start with welcome panel, instructions, etc
        MillenialopolyWindow gameWindow = new MillenialopolyWindow();
        WelcomePanel welcomePanel = new WelcomePanel(gameWindow);
    }
}
