package com.codecool.roguelike.Items;

public class Inventory {
    private Items goldCount = new Gold();
    private Items  swordCount = new Sword();
    private int  keyCount = 0;


    public Items getGoldCount() {
        return goldCount;
    }


    public Items getSwordCount() {
        return swordCount;
    }

    public int getKeyCount() {
        return keyCount;
    }

    public void setKeyCount(int keyCount) {
        this.keyCount = keyCount;
    }
}
