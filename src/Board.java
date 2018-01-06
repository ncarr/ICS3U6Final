// import json_parser.parser;
import JsonParser.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Board{

    // Square board, ten squares on each side
    // private Tile[] spots = new Tile[36];
    public Tile[] spots = new Tile[18]; // fake

    Board() {
        // Initialize all the squares!
        // Iterate and add to tiles
        JSONObject json = new JSONObject(getJSON());
        JSONArray tiles = json.getJSONArray("tiles");

        for (int i = 0; i < tiles.length(); i++){
            JSONObject tile = tiles.getJSONObject(i);
            String tileType = tile.getString("type");
            String tileName = tile.getString("name");
            if (tileType.equals("go")){
                spots[i] = new GoTile();
            } else if (tileType.equals("jail")){
                spots[i] = new JailTile();
            } else if (tileType.equals("theft")){
                spots[i] = new TheftTile();
            } else if (tileType.equals("tax")){
                spots[i] = new TaxTile(tile.getInt("tax"));
            } else if (tileType.equals("chance")){
                spots[i] = new ChanceTile(false);
            } else if (tileType.equals("wild_chance")){
                spots[i] = new ChanceTile(true);
            } else if (tileType.equals("hyperloop")){
                spots[i] = new HyperloopTile(tileName);
            } else if (tileType.equals("property")){
                String color = tile.getString("color");
                int[] rent = {
                    tile.getJSONObject("rent").getInt("0_avocadoes"),
                    tile.getJSONObject("rent").getInt("1_avocadoes"),
                    tile.getJSONObject("rent").getInt("2_avocadoes"),
                    tile.getJSONObject("rent").getInt("3_avocadoes"),
                    tile.getJSONObject("rent").getInt("4_avocadoes"),
                    tile.getJSONObject("rent").getInt("5_avocadoes"),
                };
                int mortgage = tile.getInt("mortgage");
                int cost = tile.getInt("cost");
                int avocadoCost = tile.getInt("avocado_cost");
                spots[i] = new Property(tileName, color, rent,
                                        mortgage, cost, avocadoCost);
            }
        }

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
