package com.codecool.roguelike.levels;

public class level3 extends Level {
    private static final int[][] pathRows = new int[][]{{4, 10, 14}, {5, 7, 10}, {8, 3, 7}, {10, 3, 10}, {12, 6, 7}, {13, 1, 6}};
    private static final int[][] pathCols = new int[][]{{3, 8, 10}, {5, 10, 14}, {6, 5, 10}, {9, 1, 5}, {13, 1, 5}};
    private static final int[] playerSpawn = new int[]{13, 1};
    private static final int[][] monstersSpawn = new int[][]{{9, 3}, {6, 8}, {2, 9}, {2, 13}};
    private static final int[][] gates = new int[][]{{0, 13}};
    private static final int[][] golds = new int[][]{{10, 9},{8, 2}, {4, 11}};
    private static final int[][] swords = new int[][]{{10, 8}, {5, 9}};
    private  static final int[] key = new int[]{1, 9};

    public level3() {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
