package com.codecool.roguelike;

public enum Icons {
    GOLDICON('g'), WALLICON('#'), GATEICON('='), PLAYERICON('@'), MONSTERICON('o'), SWORDICON('s'), KEYICON('k'), STEP('j'), POINTS('p'),
    NULLICON('n'), FEWICON('f'), LOTICON('l');

    private final char spriteIcon;

    Icons(char spriteicon) {
        this.spriteIcon = spriteicon;
    }
    public char getItemIcon() {
        return spriteIcon;
    }
}
