// import json_parser.parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Board{

    // Square board, ten squares on each side
    // private Tile[] spots = new Tile[36];
    public Tile[] spots = new Tile[22]; // fake

    Board() {
        // Initialize all the squares!
        // Iterate and add to tiles

    }

    private static String getJSON(){
        String res = "";
        try {
            String loc = "data/tiles.json";
            BufferedReader br = new BufferedReader(new FileReader(loc));
            String line = br.readLine();
            while (line != null){
                res += line;
                line = br.readLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
       return res;
    }
}
