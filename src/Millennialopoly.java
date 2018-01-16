/* [Millennialopoly.java]
 * A Monopoly variation adapted to suit "Millennials" a.k.a. Snake People
 * @author Atharva Washimkar, adapted by Carol
 */

import java.util.Scanner;
import javax.swing.*;

class Millennialopoly {

    public static void main(String[] args) {
        // Start the shenanigans!
        try {
            UIManager.setLookAndFeel (new MaterialLookAndFeel ());
        } catch (UnsupportedLookAndFeelException e) {}

        // Start with welcome panel, instructions, etc
        MillennialopolyWindow gameWindow = new MillennialopolyWindow();
        WelcomePanel welcomePanel = new WelcomePanel(gameWindow);
    }
}
