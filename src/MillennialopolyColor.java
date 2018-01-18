/**
 * [MillennialopolyColor.java]
 * Game-specific colours
 * @author Nicholas Carr, Carol Chen, Nathan Shen
 */

import java.awt.Color;
// Allows colour on the board
public class MillennialopolyColor extends Color {
    private int index;

    private MillennialopolyColor(Color color, int index) {
        super(color.getRGB());
        this.index = index;
    }
    private MillennialopolyColor(int r, int g, int b, int index) {
        super(r, g, b);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
// Colour choices
    public static final MillennialopolyColor brown = new MillennialopolyColor(165, 42, 42, 0);
    public static final MillennialopolyColor lightBlue = new MillennialopolyColor(173, 216, 230, 1);
    public static final MillennialopolyColor magenta = new MillennialopolyColor(Color.magenta, 2);
    public static final MillennialopolyColor orange = new MillennialopolyColor(Color.orange, 3);
    public static final MillennialopolyColor red = new MillennialopolyColor(Color.red, 4);
    public static final MillennialopolyColor yellow = new MillennialopolyColor(Color.yellow, 5);
    public static final MillennialopolyColor green = new MillennialopolyColor(Color.green, 6);
    public static final MillennialopolyColor blue = new MillennialopolyColor(0, 128, 255, 7);

    public static MillennialopolyColor[] colors = {
        brown,
        lightBlue,
        magenta,
        orange,
        red,
        yellow,
        green,
        blue
    };
}
