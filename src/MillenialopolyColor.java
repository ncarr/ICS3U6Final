/**
 * [MillenialopolyColor.java]
 * Game-specific colours
 * @author Nicholas Carr, Carol Chen
 */

import java.awt.Color;

public class MillenialopolyColor extends Color {
    private int index;

    private MillenialopolyColor(Color color, int index) {
        super(color.getRGB());
        this.index = index;
        System.out.println(this.equals(color));
    }
    private MillenialopolyColor(int r, int g, int b, int index) {
        super(r, g, b);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static final MillenialopolyColor brown = new MillenialopolyColor(165, 42, 42, 0);
    public static final MillenialopolyColor lightBlue = new MillenialopolyColor(173, 216, 230, 1);
    public static final MillenialopolyColor magenta = new MillenialopolyColor(Color.magenta, 2);
    public static final MillenialopolyColor orange = new MillenialopolyColor(Color.orange, 3);
    public static final MillenialopolyColor red = new MillenialopolyColor(Color.red, 4);
    public static final MillenialopolyColor yellow = new MillenialopolyColor(Color.yellow, 5);
    public static final MillenialopolyColor green = new MillenialopolyColor(Color.green, 6);
    public static final MillenialopolyColor blue = new MillenialopolyColor(0, 128, 255, 7);

    public static MillenialopolyColor[] colors = {
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
