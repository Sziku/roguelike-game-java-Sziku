package com.codecool.roguelike.levels;

public class level4 extends Level {

    private static final int[][] pathRows = new int[][]{{3, 4, 5}, {3, 7, 9}, {5, 4, 12}, {6, 3, 5}, {7, 9, 12}, {8, 1, 9}};
    private static final int[][] pathCols = new int[][]{{2, 1, 8}, {3, 8, 11}, {4, 3, 8}, {7, 3, 5}, {8, 5, 8}, {11, 5, 7}};
    private static final int[] playerSpawn = new int[]{8, 1};
    private static final int[][] monstersSpawn = new int[][]{{8, 7}, {5, 8}, {3, 8}};
    private static final int[][] gates = new int[][]{{5, 12}};
    private static final int[][] golds = new int[][]{{2, 2},{11, 3}, {7, 8}, {7, 10}, {3, 5}};
    private static final int[][] swords = new int[][]{{1, 2}, {8, 8}};
    private  static final int[] key = new int[]{3, 9};

    public level4() {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
