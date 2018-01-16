/**
 * [UtilityTile.java]
 * Utility tile
 * @author Nicholas Carr, Carol Chen
 */

 public class UtilityTile extends Ownable implements Comparable<Ownable> {

     public static int cost = 150;

     UtilityTile(String name) {
        super(name);
        this.mortgage = 75;
     }

     public int compareTo(Ownable o) {
         if (o instanceof UtilityTile) {
             return super.compareTo(o);
         }
         return 1;
     }
 }
