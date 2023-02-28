package com.codecool.roguelike.Creatures;

import com.codecool.roguelike.Creatures.PlayableRaces.Race;

public abstract class Creature {
        private String name;
        private Race race;
        private boolean isAlive;
        private boolean isActive;
        private int health;

        public Creature(String name, Race race) {
            this.name = name;
            this.race = race;
            health = 1;
            isAlive = true;
        }

        public String getName() {
            return name;
        }

        public Race getRace() {
            return race;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void isDead() {
            if (health == 0) {
                isAlive = false;
            }
        }

    @Override
    public String toString() {
        return  name + " the " + race + " hero";
    }
}
