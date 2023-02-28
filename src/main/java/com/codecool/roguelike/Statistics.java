package com.codecool.roguelike;

public class Statistics {
    private int orcKill = 0;
    private int trollKill = 0;
    private int steps = 0;
    private int points = 0;

    public int getOrcKill() {
        return orcKill;
    }

    public void setOrcKill(int orcKill) {
        this.orcKill = orcKill;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTrollKill() {
        return trollKill;
    }

    public void setTrollKill(int trollKill) {
        this.trollKill = trollKill;
    }
}
