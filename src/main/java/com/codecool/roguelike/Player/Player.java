package com.codecool.roguelike.Player;

import com.codecool.roguelike.Creatures.Creature;
import com.codecool.roguelike.Creatures.PlayableRaces.*;
import com.codecool.roguelike.Creatures.RacialSkill;
import com.codecool.roguelike.Items.Inventory;
import com.codecool.roguelike.Statistics;

public class Player extends Creature {
    private int playerCordX;
    private int playerCordY;
    private char playerIcon;
    private RacialSkill racialSkill;
    private final Inventory playerInventory;
    private final Statistics playerStatistics;

    public Player(String name, Race race, int playerCordX, int playerCordY) {
        super(name, race);
        this.playerCordX = playerCordX;
        this.playerCordY = playerCordY;
        switch (race){
            case HUMAN -> racialSkill = new Human();
            case DWARF -> racialSkill = new Dwarf();
            case ELF -> racialSkill = new Elf();
        }
        playerInventory = new Inventory();
        playerStatistics = new Statistics();
    }

    public int getPlayerCordX() {
        return playerCordX;
    }

    public void setPlayerCordX(int playerCordX) {
        this.playerCordX = playerCordX;
    }

    public int getPlayerCordY() {
        return playerCordY;
    }

    public void setPlayerCordY(int playerCordY) {
        this.playerCordY = playerCordY;
    }

    public char getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(char playerIcon) {
        this.playerIcon = playerIcon;
    }

    public RacialSkill getRacialSkill() {
        return racialSkill;
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    public Statistics getPlayerStatistics() {
        return playerStatistics;
    }
}
