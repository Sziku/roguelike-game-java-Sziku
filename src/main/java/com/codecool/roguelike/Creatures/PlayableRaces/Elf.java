package com.codecool.roguelike.Creatures.PlayableRaces;

import com.codecool.roguelike.Creatures.RacialSkill;

public class Elf implements RacialSkill {

    @Override
    public int GoldPassive() {
        return 1;
    }

    @Override
    public int SwordPassive() {
        return 2;
    }
}
