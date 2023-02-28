package com.codecool.roguelike.Creatures;

import com.codecool.roguelike.Creatures.PlayableRaces.Race;

public class Boss extends Creature implements RacialSkill {

    public Boss(String name, Race race) {
        super(name, race);
    }

    @Override
    public int GoldPassive() {
        return 3;
    }

    @Override
    public int SwordPassive() {
        return 3;
    }
}
