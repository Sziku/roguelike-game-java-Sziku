package com.codecool.roguelike.Items;

public abstract class Items {
    private String itemName;
    private int itemCount;

    public Items(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }


    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return  itemName + " : " + itemCount + " pieces";
    }
}
