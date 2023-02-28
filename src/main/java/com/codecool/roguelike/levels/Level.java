package com.codecool.roguelike.levels;

public abstract class Level {
    private final int[][] pathRows;
    private final int[][] pathCols;
    private int[] playerSpawn;
    private int[][] monstersSpawn;
    private final int[][] gates;
    private int[][] golds;
    private int[][] swords;
    private int[] key;



    public Level(int[][] pathRows, int[][] pathCols, int[] playerSpawn, int[][] monstersSpawn, int[][] gates, int[][] golds, int[][] swords, int[] key){
        this.pathRows = pathRows;
        this.pathCols = pathCols;
        this.playerSpawn = playerSpawn;
        this.monstersSpawn = monstersSpawn;
        this.gates = gates;
        this.golds = golds;
        this.swords = swords;
        this.key = key;
    }

    public int[][] getPathRows() {
        return pathRows;
    }

    public int[][] getPathCols() {
        return pathCols;
    }

    public int[] getPlayerSpawn() {
        return playerSpawn;
    }

    public int[][] getMonstersSpawn() {
        return monstersSpawn;
    }

    public int[][] getGates() {
        return gates;
    }


    public int[][] getGolds() {
        return golds;
    }

    public int[][] getSwords() {
        return swords;
    }

    public int[] getKey() {
        return key;
    }
}
