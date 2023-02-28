package com.codecool.roguelike.Creatures.PlayableRaces;

import com.codecool.roguelike.Creatures.RacialSkill;

public class Dwarf  implements RacialSkill {
    @Override
    public int GoldPassive() {
        return 2;
    }

    @Override
    public int SwordPassive() {
        return 1;
    }
}
