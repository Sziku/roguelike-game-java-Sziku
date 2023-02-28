package com.codecool.roguelike.ui;

import com.codecool.roguelike.ui.gui.TileRenderer;

import java.util.Map;

/**
 * Displays complete game board on the screen
 */
public interface GameUI {
    void displayBoard(char[][] board);

}
