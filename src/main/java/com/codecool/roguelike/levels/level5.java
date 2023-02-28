package com.codecool.roguelike.levels;

public class level5 extends Level {

    private static final int[][] pathRows = new int[][]{{2, 4, 9}, {3, 8, 14}, {5, 1, 14}, {7, 10, 12}, {9, 4, 8}, {10, 11, 13}, {11, 1, 10}, {13, 10, 13}};
    private static final int[][] pathCols = new int[][]{{1, 1, 6}, {1, 10, 12}, {2, 3, 6}, {4, 1, 6}, {6, 6, 13}, {10, 11, 13}, {10, 3, 5}, {12, 3, 13}, {14, 3, 6}};
    private static final int[] playerSpawn = new int[]{13, 6};
    private static final int[][] monstersSpawn = new int[][]{{2, 1}, {3, 12}, {5, 12}, {5, 5}, {10, 1}, {12, 1}, {9, 4}, {9, 8}, {10, 11}, {10, 13}, {7, 10}, {2, 8}};
    private static final int[][] gates = new int[][]{{1, 1}};
    private static final int[][] golds = new int[][]{{3, 1},{5,8}, {3, 2}};
    private static final int[][] swords = new int[][]{{13,13}, {7, 12}, {3, 8}};
    private  static final int[] key = new int[]{3, 14};

    public level5() {
        super(pathRows, pathCols, playerSpawn, monstersSpawn, gates, golds, swords, key);
    }
}
