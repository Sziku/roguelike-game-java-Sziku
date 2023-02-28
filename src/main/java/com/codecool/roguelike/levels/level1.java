package com.codecool.roguelike.levels;

public class level1 extends Level {


    private static final int[][] pathRows = new int[][]{{3, 4, 6}, {7, 1, 11}};
    private static final int[][] pathCols = new int[][]{{1, 7, 14}, {6, 3, 7}, {11, 4, 11}} ;
    private static final int[] playerSpawn = new int[]{13, 1};
    private static final int[][] monstersSpawn = new int[][]{{9, 11}};
    private static final int[][] gates = new int[][]{{11, 11}};
    private static final int[][] golds = new int[][]{{3, 4},{4,11}};
    private static final int[][] swords = new int[][]{{7,11}};
    private  static final int[] key = new int[]{10, 11};

    public level1()
    {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
