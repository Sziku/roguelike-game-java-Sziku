package com.codecool.roguelike.ui.console;

import com.codecool.roguelike.ui.GameUI;

public class ConsoleUI implements GameUI {

    private final int PATH = 1;
    private final String SPACE = " ";
    @Override
    public void displayBoard(char[][] board) {
        StringBuilder boardString = new StringBuilder();
        for (char[] chars : board) {
            for (char c : chars) {
                if(c==PATH){
                    boardString.append(SPACE);
                }else {
                    boardString.append(c);
                }

            }
            boardString.append("\n");
        }
        System.out.println(boardString);
    }
}
