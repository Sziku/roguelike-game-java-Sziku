package com.codecool.roguelike;

import com.codecool.roguelike.Creatures.PlayableRaces.Race;
import com.codecool.roguelike.Player.Player;
import com.codecool.roguelike.ui.gui.ImageTileRenderer;
import com.codecool.roguelike.ui.gui.TileRenderer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int getRandomIntFromRange( int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static char getInputChar() throws IOException {
        return br.readLine().charAt(0);
    }

    public static void putPlayerAfterMacro(char path, Player player, char[][] board, int[] nextStep) {
        board[nextStep[0]][nextStep[1]] = path;
        Engine.removePlayerFromBoard(player);
        player.setPlayerCordX(nextStep[0]);
        player.setPlayerCordY(nextStep[1]);
        Engine.putPlayerOnBoard(player);
    }

    public static void playerMove(Player player, int[] nextStep, char key) {
        int playerX = player.getPlayerCordX();
        int playerY = player.getPlayerCordY();
        switch (key) {
            case 'w' -> {
                nextStep[0] = playerX - 1;
                nextStep[1] = playerY;
            }
            case 'a' -> {
                nextStep[0] = playerX;
                nextStep[1] = playerY - 1;
            }
            case 'd' -> {
                nextStep[0] = playerX;
                nextStep[1] = playerY + 1;
            }
            case 's' -> {
                nextStep[0] = playerX + 1;
                nextStep[1] = playerY;
            }
        }
    }

    public static Map<Character, TileRenderer> getCharacterTileRendererMap() {
        Map<Character, TileRenderer> gui = new HashMap<>();
        TileRenderer pictureWall = new ImageTileRenderer("/images/wall.png");
        TileRenderer pictureDoor = new ImageTileRenderer("/images/keyhole.png");
        TileRenderer picturePlayer = new ImageTileRenderer("/images/knight.png");
        TileRenderer pictureKey = new ImageTileRenderer("/images/key.png");
        TileRenderer pictureGold = new ImageTileRenderer("/images/gold.png");
        TileRenderer pictureOrc = new ImageTileRenderer("/images/ork_1.png");
        TileRenderer pictureSword = new ImageTileRenderer("/images/sword.png");
        TileRenderer pictureFew = new ImageTileRenderer("/images/few.png");
        TileRenderer pictureLot = new ImageTileRenderer("/images/lot.png");
        TileRenderer pictureNull = new ImageTileRenderer("/images/null.png");
        TileRenderer picturePoints = new ImageTileRenderer("/images/points.png");
        TileRenderer pictureSteps = new ImageTileRenderer("/images/steps.png");

        gui.put(Icons.WALLICON.getItemIcon(), pictureWall);
        gui.put(Icons.GATEICON.getItemIcon(), pictureDoor);
        gui.put(Icons.PLAYERICON.getItemIcon(), picturePlayer);
        gui.put(Icons.MONSTERICON.getItemIcon(), pictureOrc);
        gui.put(Icons.GOLDICON.getItemIcon(), pictureGold);
        gui.put(Icons.KEYICON.getItemIcon(), pictureKey);
        gui.put(Icons.SWORDICON.getItemIcon(), pictureSword);
        gui.put(Icons.FEWICON.getItemIcon(), pictureFew);
        gui.put(Icons.NULLICON.getItemIcon(), pictureNull);
        gui.put(Icons.LOTICON.getItemIcon(), pictureLot);
        gui.put(Icons.POINTS.getItemIcon(), picturePoints);
        gui.put(Icons.STEP.getItemIcon(), pictureSteps);



        return gui;
    }

    public static void printLine(String text){
        System.out.println(text);
    }

    public static void savePlayer(Player player){
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;

        String PATH = "src/main/java/com/codecool/roguelike/Saves/";
        String directoryName = PATH.concat(player.getName());
        String fileName = player.getName()+"_" + dateFormat.format(date) + ".txt";

        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        StringBuilder value = new StringBuilder();
        value.append(player.getName())
                .append(" ")
                .append(player.getPlayerInventory().getSwordCount().getItemCount())
                .append(" ")
                .append(player.getPlayerInventory().getGoldCount().getItemCount())
                .append(" ")
                .append(player.getRace());

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(value.toString());
            bw.close();
            System.out.println("save success");
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void monsterMove(char[][] board, char path) {
        for (int i = 0; i < board.length; i++) {
            for (int j =  0; j < board[i].length; j++) {
                if(board[i][j] == Icons.MONSTERICON.getItemIcon()){
                    List<List<Integer>> possibleMoves = getPossibleMoves(board, path, i, j);

                    if(possibleMoves.size() != 0){
                        List<Integer> choosenMove = possibleMoves.get(Util.getRandomIntFromRange(possibleMoves.size()));
                        board[i][j] = path;
                        board[choosenMove.get(0)][choosenMove.get(1)] = Icons.MONSTERICON.getItemIcon();
                    }
                }
            }
        }
    }

    private static List<List<Integer>> getPossibleMoves(char[][] board, char path, int i, int j) {
        List<List<Integer>> possibleMoves = new ArrayList<>();
        if(board[i][j +1] == path){
            List<Integer> possibleMove = new ArrayList<>();
            possibleMove.add(i);
            possibleMove.add(j +1);
            possibleMoves.add(possibleMove);
        }
        if(board[i][j -1] == path){
            List<Integer> possibleMove = new ArrayList<>();
            possibleMove.add(i);
            possibleMove.add(j -1);
            possibleMoves.add(possibleMove);
        }
        if(board[i +1][j] == path){
            List<Integer> possibleMove = new ArrayList<>();
            possibleMove.add(i +1);
            possibleMove.add(j);
            possibleMoves.add(possibleMove);
        }
        if(board[i -1][j] == path){
            List<Integer> possibleMove = new ArrayList<>();
            possibleMove.add(i -1);
            possibleMove.add(j);
            possibleMoves.add(possibleMove);
        }
        return possibleMoves;
    }

    public static String[] collectPlayers(){
        File file = new File("src/main/java/com/codecool/roguelike/Saves/");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });

        return directories;

    }

    public static void printPlayers(String[] directories) {
        int playerSelect = 0;
        for (String players: directories) {
            Util.printLine(playerSelect+". "+players);
            playerSelect++;
        }
    }

    public static Set<String> showSaveFiles(int selectedPlayer){
        String saves = "src/main/java/com/codecool/roguelike/Saves/";
        String[] collectplayers = collectPlayers();
        String playersaves = saves.concat(collectplayers[selectedPlayer]);

        Set<String> saveFiles;
         saveFiles = Stream.of(new File(playersaves).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());

        int saveFileSelect = 0;
        for (String savefile: saveFiles) {
            Util.printLine(saveFileSelect+". "+savefile);
           saveFileSelect++;
        }
         return saveFiles;
    }


    public static String[] loadPlayer(String slectedPlayer, Set<String> stringSet,int selectedPlayerSavefiles){
        String saves = "src/main/java/com/codecool/roguelike/Saves/";
        List<String> arr = new ArrayList<>(stringSet);
        arr.addAll(stringSet);
        String saveFile = arr.get(selectedPlayerSavefiles);
        String file = saves.concat(slectedPlayer).concat("/").concat(saveFile);


        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st =  br.readLine();
            String[] loadedSavefile = st.split("\\s");
            return loadedSavefile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
