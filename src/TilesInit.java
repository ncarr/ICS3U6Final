/**
 * [TilesInit.java]
 * Creates a list of all the tiles
 * @author Nicholas Carr, Carol Chen
 */

class TilesInit {
    public static Tile[] tiles = {
        new Tile("Passing Go"),
        new Property(
            "The Macintosh",
            "brown",
            new int[] { 2, 10, 30, 90, 160, 250 },
            30,
            60,
            50
        ),
        new ChanceTile("Chance", false),
        new Property(
            "No World Wars in fifty years",
            "brown",
            new int[] { 4, 20, 60, 180, 320, 450 },
            30,
            60,
            50
        ),
        new TaxTile("Income Tax", 200),
        new HyperloopTile("WATER WATER WATER LOOP"),
        new Property(
            "Taylor Swift",
            "light blue",
            new int[] { 6, 30, 90, 270, 400, 550 },
            50,
            100,
            50
        ),
        new ChanceTile("Community Chest", false),
        new Property(
            "Miley Cyrus",
            "light blue",
            new int[] { 6, 30, 90, 270, 400, 550 },
            50,
            100,
            50
        ),
        new Property(
            "Kanye West",
            "light blue",
            new int[] { 8, 40, 100, 300, 450, 600 },
            60,
            120,
            50
        ),
        new Tile("Visiting Jail"),
        new Property(
            "Netflix and Chill",
            "magenta",
            new int[] { 10, 50, 150, 450, 625, 750 },
            70,
            140,
            100
        ),
        new ChanceTile("Wild Chance", true),
        new Property(
            "Using internet acronyms irl",
            "magenta",
            new int[] { 10, 50, 150, 450, 625, 750 },
            70,
            140,
            100
        ),
        new Property(
            "Paying a friend to take care of your Snap streaks",
            "magenta",
            new int[] { 12, 60, 180, 500, 700, 900 },
            80,
            160,
            100
        ),
        new HyperloopTile("WARR HYPERLOOP"),
        new Property(
            "Nyan Cat",
            "orange",
            new int[] { 14, 70, 200, 550, 750, 950 },
            90,
            180,
            100
        ),
        new ChanceTile("Chance", false),
        new Property(
            "Datboi",
            "orange",
            new int[] { 14, 70, 200, 550, 750, 950 },
            90,
            180,
            100
        ),
        new Property(
            "It's Wednesday my dudes",
            "orange",
            new int[] { 16, 80, 220, 600, 800, 1000 },
            100,
            200,
            100
        ),
        new TheftTile("Steal Tax Money"),
        new Property(
            "Knuckles",
            "red",
            new int[]{18,90,250,700,875,1050},
            110,
            220,
            150
        ),
        new ChanceTile("Community Chest", false),
        new Property(
            "Uganda",
            "red",
            new int[]{18,90,250,700,875,1050},
            110,
            220,
            150
        ),
        new Property(
            "memes",
            "red",
            new int[]{20,100,300,750,925,1100},
            120,
            240,
            150
        ),
        new HyperloopTile("Hyperloop One"),
        new Property(
            "Carol Cresent",
            "yellow",
            new int[]{22,110,330,800,975,1150},
            130,
            260,
            150
        ),
        new Property(
            "Nicholas Lane",
            "yellow",
            new int[]{22,110,330,800,975,1150},
            130,
            260,
            150
        ),
        new UtilityTile("Waterloo"),
        new Property(
            "Nathan Ave",
            "yellow",
            new int[]{24,120,360,850,1025,1200},
            140,
            280,
            150
        ),
        new GoToJailTile("Go to jail"),
        new Property(
            "Trump",
            "green",
            new int[]{26,130,390,900,1100,1275},
            150,
            300,
            200
        ),
        new Property(
            "Hillary",
            "green",
            new int[]{26,130,390,900,1100,1275},
            150,
            300,
            200
        ),
        new ChanceTile("Wild Chance", true),
        new Property(
            "Bernie can still Win",
            "green",
            new int[]{28,150,450,1000,1200,1400},
            160,
            320,
            200
        ),
        new HyperloopTile("The Boring Company"),
        new ChanceTile("Wild Chance", true),
        new Property(
            "Misha inc.",
            "blue",
            new int[]{35,175,500,1100,1300,1500},
            175,
            350,
            200
        ),
        // TODO: make this luxury tax
        new Property(
            "CBC",
            "blue",
            new int[]{50,200,600,1400,1700,2000},
            200,
            400,
            200
        ),
        new Property(
            "dHack",
            "blue",
            new int[]{50,200,600,1400,1700,2000},
            200,
            400,
            200
        )
    };
}
