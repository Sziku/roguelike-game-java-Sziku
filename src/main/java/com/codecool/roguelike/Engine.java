package com.codecool.roguelike;

import com.codecool.roguelike.Player.Player;
import com.codecool.roguelike.levels.Level;

public class Engine {
    private static char[][] board;
    private static final int WALL = 0;
    private static final int PATH = 1;
    private static final int PLAYER = 3;
    private static final int DOOR = 2;
    private static final int MONSTER = 4;
    private static final int GOLD = 5;
    private static final int SWORD= 6;
    private static final int KEY = 7;

    public static char[][] createBoard(int width, int height, Level level) {
        board = new char[height][width];
        generateBoardRows(level);
        generateBoardCols(level);
        generateGate(level);
        generateGold(level);
        generateMonsters(level);
        generateSwords(level);
        generateKey(level);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

               switch (board[i][j]){
                   case WALL -> board[i][j] = Icons.WALLICON.getItemIcon();
                   case DOOR -> board[i][j] = Icons.GATEICON.getItemIcon();
                   case GOLD -> board[i][j] = Icons.GOLDICON.getItemIcon();
                   case MONSTER -> board[i][j] = Icons.MONSTERICON.getItemIcon();
                   case SWORD -> board[i][j] = Icons.SWORDICON.getItemIcon();
                   case KEY -> board[i][j] = Icons.KEYICON.getItemIcon();

               }
            }
        }

        return board;
    }

    public static void putPlayerOnBoard(Player player) {
        board[player.getPlayerCordX()][player.getPlayerCordY()] = player.getPlayerIcon();
    }

    public static void removePlayerFromBoard( Player player) {
        board[player.getPlayerCordX()][player.getPlayerCordY()] = PATH;
    }


    private static void   generateBoardRows(Level level) {
        for (int[] row : level.getPathRows()){
            for (int i = row[1]; i < row[2]; i++) {
                board[row[0]][i]=PATH;
            }
        }

    }

    private static void   generateBoardCols(Level level) {
        for (int[] row : level.getPathCols()){
            for (int i = row[1]; i < row[2]; i++) {
                board[i][row[0]]=PATH;
            }
        }

    }

    private static void generateGate(Level level){
        for (int[] gateCord: level.getGates()){
                board[gateCord[0]][gateCord[1]]=DOOR;
            }
    }

    private static void generateGold(Level level){
        for (int[] gateCord: level.getGolds()){
            board[gateCord[0]][gateCord[1]]=GOLD;
        }
    }

    private static void generateMonsters(Level level){
        for (int[] gateCord: level.getMonstersSpawn()){
            board[gateCord[0]][gateCord[1]]=MONSTER;
        }
    }

    private static void generateSwords(Level level){
        for (int[] gateCord: level.getSwords()){
            board[gateCord[0]][gateCord[1]]=SWORD;
        }
    }

    private static void generateKey(Level level){
        board[level.getKey()[0]][level.getKey()[1]]=KEY;

    }
    public static void generateInventory(char[][] board,char invetoryStatus){
        for (int i = 1; i < 14; i++) {
            board[16][i]=invetoryStatus;
            board[17][i]=invetoryStatus;
        }
    }
    public static void putItemsInventory(char[][] board, Player player){
        int goldCount = player.getPlayerInventory().getGoldCount().getItemCount();
        int swordCount = player.getPlayerInventory().getSwordCount().getItemCount();

        for (int i = 1; i <= goldCount; i++) {
            board[16][i]=Icons.GOLDICON.getItemIcon();
        }
        for (int i = 1; i <= swordCount; i++) {
            board[17][i]=Icons.SWORDICON.getItemIcon();
        }
    }

    public static void generateStatistics(char[][] board,char statisticsStatus){
        for (int i = 4; i < 7; i++) {
            board[i][17]=statisticsStatus;
            board[i][18]=statisticsStatus;
        }
    }

    public static void setStatisticMenu(char[][] board, Player player){

            int swordCount = player.getPlayerInventory().getSwordCount().getItemCount();
            int goldCount = player.getPlayerInventory().getGoldCount().getItemCount();
            int orkKill = player.getPlayerStatistics().getOrcKill();
            int steps = player.getPlayerStatistics().getSteps();
            int points = swordCount * 3 + goldCount * 5 + orkKill * 2 - steps * 2;

            board[4][17]=Icons.MONSTERICON.getItemIcon();
            board[4][18]=calculteMany(orkKill);

            board[5][17]=Icons.STEP.getItemIcon();
            board[5][18]=calculteMany(steps);

            board[6][17]=Icons.POINTS.getItemIcon();
            board[6][18]=calculteMany(points);
    }

    private static char calculteMany(int count){
        char howMany ;
        if(count == 0){
            howMany = Icons.NULLICON.getItemIcon();
        } else if (count > 0 && count <= 10) {
            howMany = Icons.FEWICON.getItemIcon();
        } else if (count > 10) {
            howMany = Icons.LOTICON.getItemIcon();
        }else {
            howMany = Icons.NULLICON.getItemIcon();
        }
        return howMany;
    }

    public static int calculatePoints(Player player){
        int swordCount = player.getPlayerInventory().getSwordCount().getItemCount();
        int goldCount = player.getPlayerInventory().getGoldCount().getItemCount();
        int orkKill = player.getPlayerStatistics().getOrcKill();
        int steps = player.getPlayerStatistics().getSteps();
        int points = swordCount * 50 + goldCount * 10 + orkKill * 100 - steps * 2;

        return points;
    }

}
