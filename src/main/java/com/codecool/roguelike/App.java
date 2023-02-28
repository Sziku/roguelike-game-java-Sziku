package com.codecool.roguelike;

import com.codecool.roguelike.Creatures.Boss;
import com.codecool.roguelike.Creatures.Monsters.Ork;
import com.codecool.roguelike.Creatures.Monsters.Troll;
import com.codecool.roguelike.Creatures.PlayableRaces.Race;
import com.codecool.roguelike.Creatures.RacialSkill;
import com.codecool.roguelike.Player.Player;
import com.codecool.roguelike.levels.*;
import com.codecool.roguelike.ui.GameInputReader;
import com.codecool.roguelike.ui.GameUI;
import com.codecool.roguelike.ui.console.ConsoleGameInputReader;
import com.codecool.roguelike.ui.console.ConsoleUI;
import com.codecool.roguelike.ui.gui.GameGui;
import com.codecool.roguelike.ui.gui.TileRenderer;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {

        final int boardWidth = 20;
        final int boardHeight = 19;
        final char path = 1;
        final int MAX_STORAGE_CAPACITY = 14;

        boolean stop = true;
        boolean inventoryOpen = false;
        boolean statisticsOpen = false;

        Level currentLevel = null;
        Player player = null;
        GameUI ui = null;
        GameInputReader reader = null;


        char[][] board = new char[0][];
        char nextBlock = 0;

        int level = 0;
        int[] nextStep = new int[2];

        enum State {
            INILEVEL, PRINT, STEP, LEVELSELECT, CREATEBOARD, GUISELECT, END, NEXTBLOCK, INVENTORY, INIPLAYER, CREATEPLAYER, FIGHT, DEADORALIVE, COLLECT, CHEAT, NEXTSTEP, SAVE, LOAD, STATISTICS, START, WON, STORY
        }

        State activeState = State.START;
        while (stop) {
            Scanner scanner = new Scanner(System.in);
            switch (activeState) {
                case START -> {
                    Util.printLine("Main menu");
                    Util.printLine("   1. New Game");
                    Util.printLine("   2. Load Game");
                    Util.printLine("Instruction: wasd - move, i - open/close inventory, p - open/close statistics, e - save the game, q - RAGE QUIT");
                    Util.printLine("----------------------------------------------------------------------------------------------------------------");

                    int gameMode = scanner.nextInt();
                    switch (gameMode) {
                        case 1 -> activeState = State.CREATEPLAYER;
                        case 2 -> activeState = State.LOAD;
                        default -> activeState = State.END;
                    }

                }

                case CREATEPLAYER -> {
                    Util.printLine("Welcome adventurer!");
                    Util.printLine("Please choose name: ");
                    String playerName = scanner.nextLine();
                    Util.clearScreen();
                    Util.printLine("Please select race: ");
                    Util.printLine("  1. Human");
                    Util.printLine("  2. Elf");
                    Util.printLine("  3. Dwarf");
                    int race = scanner.nextInt();
                    Race playerRace = null;
                    switch (race) {
                        case 1 -> playerRace = Race.HUMAN;
                        case 2 -> playerRace = Race.ELF;
                        case 3 -> playerRace = Race.DWARF;
                        default -> {
                            Util.printLine("Wrong race");
                            stop = false;
                        }
                    }

                    player = new Player(playerName, playerRace, 0, 0);
                    activeState = State.STORY;
                }
                case INILEVEL -> {
                    if (level == 0) {
                        level++;
                    } else if (level > 6) {
                        level = 2;
                    }
                    switch (level) {
                        case 1 -> currentLevel = new level1();
                        case 2 -> currentLevel = new level2();
                        case 3 -> currentLevel = new level3();
                        case 4 -> currentLevel = new level4();
                        case 5 -> currentLevel = new level5();
                        case 6 -> currentLevel = new bossLevel();
                    }
                    activeState = State.INIPLAYER;
                }
                case INIPLAYER -> {
                    player.setPlayerCordX(currentLevel.getPlayerSpawn()[0]);
                    player.setPlayerCordY(currentLevel.getPlayerSpawn()[1]);
                    player.setPlayerIcon(Icons.PLAYERICON.getItemIcon());
                    activeState = State.CREATEBOARD;
                }
                case CREATEBOARD -> {
                    board = Engine.createBoard(boardWidth, boardHeight, currentLevel);
                    if (level > 1) {
                        activeState = State.PRINT;
                    } else {
                        activeState = State.GUISELECT;
                    }
                }

                case GUISELECT -> {
                    Util.clearScreen();
                    Map<Character, TileRenderer> gui = Util.getCharacterTileRendererMap();

                    if (args.length > 0 && args[0].equals("--gui")) {
                        ui = new GameGui(boardWidth, boardHeight, gui);
                        reader = (GameInputReader) ui;
                    } else {
                        ui = new ConsoleUI();
                        reader = new ConsoleGameInputReader();
                    }

                    activeState = State.PRINT;
                }
                case PRINT -> {
                    assert player != null;
                    Engine.putPlayerOnBoard(player);
                    player.getPlayerStatistics().setPoints(Engine.calculatePoints(player));
                    ui.displayBoard(board);
                    activeState = State.STEP;

                }

                case STEP -> {
                    char key = reader.getInputChar();
                    if (key == 'q') {
                        player.setHealth(0);
                        activeState = State.DEADORALIVE;
                    } else if ((key == 'w' || key == 'a' || key == 'd' || key == 's') && !inventoryOpen && !statisticsOpen) {
                        Util.playerMove(player, nextStep, key);
                        activeState = State.NEXTBLOCK;
                    } else if (key == 'i') {
                        activeState = State.INVENTORY;
                    } else if (key == 'p') {
                        activeState = State.STATISTICS;
                    } else if (key == 'e') {
                        activeState = State.SAVE;
                    } else if (key == 'c') {
                        activeState = State.CHEAT;
                    }
                }
                case END -> {
                    Util.savePlayer(player);
                    System.out.println("Hall of Fame");
                    assert player != null;
                    Util.printLine("Points: " + player.getPlayerStatistics().getPoints());
                    Util.printLine("Collect: " + player.getPlayerInventory().getGoldCount());
                    Util.printLine("Collect: " + player.getPlayerInventory().getSwordCount());
                    Util.printLine("Orc killed: " + player.getPlayerStatistics().getOrcKill());
                    Util.printLine("Troll killed: " + player.getPlayerStatistics().getTrollKill());
                    Util.printLine("All steps: " + player.getPlayerStatistics().getSteps());
                    stop = false;
                    System.exit(0);
                }

                case NEXTBLOCK -> {
                    nextBlock = board[nextStep[0]][nextStep[1]];
                    if (nextBlock == Icons.SWORDICON.getItemIcon() || nextBlock == Icons.GOLDICON.getItemIcon() || nextBlock == Icons.KEYICON.getItemIcon()) {
                        activeState = State.COLLECT;
                    } else if (nextBlock == path) {
                        activeState = State.NEXTSTEP;
                    } else if (nextBlock == Icons.MONSTERICON.getItemIcon()) {
                        activeState = State.FIGHT;
                    } else if (nextBlock == Icons.GATEICON.getItemIcon()) {
                        activeState = State.LEVELSELECT;
                    } else {
                        activeState = State.PRINT;
                    }
                }
                case INVENTORY -> {
                    if (!inventoryOpen) {
                        Engine.generateInventory(board, path);
                        Engine.putItemsInventory(board, player);
                        inventoryOpen = true;
                    } else {
                        Engine.generateInventory(board, Icons.WALLICON.getItemIcon());
                        inventoryOpen = false;
                    }
                    activeState = State.PRINT;
                }
                case COLLECT -> {
                    if (nextBlock == Icons.GOLDICON.getItemIcon()) {
                        int gold = player.getPlayerInventory().getGoldCount().getItemCount() + player.getRacialSkill().GoldPassive();
                        player.getPlayerInventory().getGoldCount().setItemCount(Math.min(gold, MAX_STORAGE_CAPACITY));
                    } else if (nextBlock == Icons.SWORDICON.getItemIcon()) {
                        int sword = player.getPlayerInventory().getSwordCount().getItemCount() + player.getRacialSkill().SwordPassive();
                        player.getPlayerInventory().getSwordCount().setItemCount(Math.min(sword, MAX_STORAGE_CAPACITY));
                    } else if (nextBlock == Icons.KEYICON.getItemIcon()) {
                        player.getPlayerInventory().setKeyCount(player.getPlayerInventory().getKeyCount() + 1);
                    }

                    activeState = State.NEXTSTEP;
                }
                case FIGHT -> {
                    final RacialSkill orc = new Ork("Orc", Race.ORC);
                    final RacialSkill troll = new Troll("Troll", Race.TROLL);
                    final RacialSkill boss = new Boss("Big Boss", Race.ORC);


                    if (level == 6) {
                        if (player.getPlayerInventory().getSwordCount().getItemCount() >= boss.SwordPassive() && player.getPlayerInventory().getGoldCount().getItemCount() >= boss.GoldPassive()) {
                            player.getPlayerInventory().getSwordCount().setItemCount(player.getPlayerInventory().getSwordCount().getItemCount() - boss.SwordPassive());
                            player.getPlayerInventory().getGoldCount().setItemCount(player.getPlayerInventory().getGoldCount().getItemCount() - boss.GoldPassive());
                            player.getPlayerStatistics().setOrcKill(player.getPlayerStatistics().getOrcKill() + 3);
                        }
                    } else {
                        int random = Util.getRandomIntFromRange(2);
                        switch (random){
                            case 0 -> {
                                if(player.getPlayerInventory().getSwordCount().getItemCount() >= orc.SwordPassive()){
                                    player.getPlayerInventory().getSwordCount().setItemCount(player.getPlayerInventory().getSwordCount().getItemCount() - orc.SwordPassive());
                                    player.getPlayerStatistics().setOrcKill(player.getPlayerStatistics().getOrcKill() + 1);
                                }else {
                                    player.setHealth(0);
                                }
                            }
                            case 1 -> {
                                if(player.getPlayerInventory().getSwordCount().getItemCount() >= troll.SwordPassive()){
                                    player.getPlayerInventory().getGoldCount().setItemCount(player.getPlayerInventory().getGoldCount().getItemCount() - troll.GoldPassive());
                                    player.getPlayerStatistics().setTrollKill(player.getPlayerStatistics().getTrollKill() + 1);
                                }else {
                                    player.setHealth(0);
                                }
                            }
                        }
                    }
                    activeState = State.DEADORALIVE;
                }

                case LEVELSELECT -> {
                    if (player.getPlayerInventory().getKeyCount() > 0) {
                        player.getPlayerInventory().setKeyCount(player.getPlayerInventory().getKeyCount() - 1);
                        level++;
                        activeState = State.INILEVEL;
                    } else {
                        activeState = State.PRINT;
                    }
                }
                case NEXTSTEP -> {
                    Util.putPlayerAfterMacro(path, player, board, nextStep);
                    player.getPlayerStatistics().setSteps(player.getPlayerStatistics().getSteps() + 1);
                    Util.monsterMove(board,path);
                    activeState = State.PRINT;
                }
                case DEADORALIVE -> {
                    player.isDead();
                    if (!player.isAlive()) {
                        Util.printLine("GAME OVER");
                        Util.printLine(player + " has died");
                        activeState = State.END;
                    } else {
                        if(level == 6){
                            activeState = State.WON;
                        }else {
                            activeState = State.NEXTSTEP;
                        }
                    }
                }
                case STATISTICS -> {
                    if (!statisticsOpen) {
                        Engine.generateStatistics(board, path);
                        Engine.setStatisticMenu(board, player);
                        statisticsOpen = true;
                    } else {
                        Engine.generateStatistics(board, Icons.WALLICON.getItemIcon());
                        statisticsOpen = false;
                    }
                    activeState = State.PRINT;
                }

                case SAVE -> {
                    Util.savePlayer(player);
                    activeState = State.PRINT;
                }

                case LOAD -> {
                    Util.printLine("Please select a player:");
                    Util.printPlayers(Util.collectPlayers());
                    int selectedPlayer = scanner.nextInt();
                    Set<String> setlist = Util.showSaveFiles(selectedPlayer);
                    int selectedSaveFile = scanner.nextInt();
                    String[] loadedSavefile = Util.loadPlayer(Util.collectPlayers()[selectedPlayer],setlist,selectedSaveFile);
                    Race loadrace = switch (loadedSavefile[3]) {
                        case "HUMAN" -> Race.HUMAN;
                        case "ELF" -> Race.ELF;
                        case "DWARF" -> Race.DWARF;
                        default -> null;
                    };
                    player = new Player(loadedSavefile[0], loadrace,0,0);
                    player.getPlayerInventory().getSwordCount().setItemCount(Integer.parseInt(loadedSavefile[1]));
                    player.getPlayerInventory().getGoldCount().setItemCount(Integer.parseInt(loadedSavefile[2]));
                    Util.printLine("Load was successful");
                    activeState = State.INILEVEL;
                }

                case CHEAT -> {
                    player.getPlayerInventory().getGoldCount().setItemCount(13);
                    player.getPlayerInventory().getSwordCount().setItemCount(13);
                    activeState = State.PRINT;
                }
                case  WON -> {
                    Util.printLine("Congratulations!");
                    Util.printLine("You killed the final boss and your war is now over.");
                    Util.printLine("Or is not?");
                    Util.printLine("Everything that lives is designed to end.\n They are perpetually trapped in a never-ending spiral of life and death.\n However, life is all about the struggle within this cycle.\n That is what 'we' believe.");

                    activeState = State.END;
                }
                case STORY -> {
                    Util.printLine("They are rage, brutal, without mercy.");
                    Util.printLine("But you.");
                    Util.printLine("You will be worse.");
                    Util.printLine("Rip and tear, until it is done.");

                    activeState = State.INILEVEL;
                }
            }
        }
    }


}
