package com.codecool.roguelike.Creatures.Monsters;

import com.codecool.roguelike.Creatures.Creature;
import com.codecool.roguelike.Creatures.PlayableRaces.Race;
import com.codecool.roguelike.Creatures.RacialSkill;

public class Ork extends Creature implements RacialSkill {

    public Ork(String name, Race race) {
        super(name, race);
    }

    @Override
    public int GoldPassive() {
        return 0;
    }

    @Override
    public int SwordPassive() {
        return 1;
    }
}
