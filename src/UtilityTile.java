/**
 * [UtilityTile.java]
 * Utility tile
 * @author Nicholas Carr, Carol Chen
 */

 public class UtilityTile extends Tile{

     public static int mortgage = 75;
     public static int cost = 150;

     private int owner;
     private boolean mortgaged;

     UtilityTile(String name) {
         super(name);
         mortgaged = false;
         owner = -1;

     }

     public boolean isMortgaged() {
         return mortgaged;
     }

     public void changeMortgage() {
         mortgaged = !mortgaged;
     }

     public int getOwner() {
         return owner;
     }

     public void setOwner(int player) {
         owner = player;
     }
 }
