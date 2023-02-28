package com.codecool.roguelike.levels;

public class level2 extends Level {


    private static final int[][] pathRows = new int[][]{{4, 3, 5}, {4, 9, 11}, {6, 3, 11}, {8, 9, 11}, {9, 1, 7}, {12, 1, 4}, {12, 6, 10}};
    private static final int[][] pathCols = new int[][]{{3, 1, 6}, {4, 9, 13}, {5, 4, 6}, {6, 10, 12}, {8, 12, 15}, {11, 4, 7}, {9, 6, 12}};
    private static final int[] playerSpawn = new int[]{1, 3};
    private static final int[][] monstersSpawn = new int[][]{{4, 5}, {8, 8}, {8, 10}};
    private static final int[][] gates = new int[][]{{12, 1}};
    private static final int[][] golds = new int[][]{{4, 3},{13, 8}};
    private static final int[][] swords = new int[][]{{4, 9}};
    private  static final int[] key = new int[]{9, 1};

    public level2() {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
