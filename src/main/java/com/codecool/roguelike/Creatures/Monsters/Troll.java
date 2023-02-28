package com.codecool.roguelike.Creatures.Monsters;

import com.codecool.roguelike.Creatures.Creature;
import com.codecool.roguelike.Creatures.PlayableRaces.Race;
import com.codecool.roguelike.Creatures.RacialSkill;

public class Troll extends Creature implements RacialSkill {

    public Troll(String name, Race race) {
        super(name, race);
    }

    @Override
    public int GoldPassive() {
        return 1;
    }

    @Override
    public int SwordPassive() {
        return 0;
    }
}
