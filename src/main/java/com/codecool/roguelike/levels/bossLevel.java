package com.codecool.roguelike.levels;

public class bossLevel extends Level {
    private static final int[][] pathRows = new int[][]{{3, 2, 13}, {4, 2, 13}, {5, 2, 13}, {6, 2, 13}, {7, 2, 13}, {8, 2, 13}, {9, 2, 13}, {10, 2, 13}, {11, 2, 13}};
    private static final int[][] pathCols = new int[][]{{3, 2, 13}, {4, 2, 13}, {5, 2, 13}, {6, 2, 13}, {7, 2, 13}, {8, 2, 13}, {9, 2, 13}, {10, 2, 13}, {11, 2, 13}};
    private static final int[] playerSpawn = new int[]{4, 11};
    private static final int[][] monstersSpawn = new int[][]{{6, 7}};
    private static final int[][] gates = new int[][]{{10, 10}};
    private static final int[][] golds = new int[][]{{9, 8}};
    private static final int[][] swords = new int[][]{{5, 5}};
    private  static final int[] key = new int[]{3,7};

    public bossLevel() {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
