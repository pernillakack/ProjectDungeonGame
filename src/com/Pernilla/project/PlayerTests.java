package com.Pernilla.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTests {

    Player player;
    Monster monster;

    @BeforeEach
    void setup() {
       player = new Player("",10,5,10,100,0,1, 0);
        monster = new Monster("",40,100);
    }
    @Test
    @DisplayName("Can Player loose?")
    public void canPlayerLoose() {

        int startingHealth = 100;

        monster.setDamage(monster.getStrength() / 4 + 1);
        player.setHealth(player.getHealth() - monster.getDamage());
        assertTrue(player.getHealth() < startingHealth);
    }

        @Test
        @DisplayName("Can player level up?")
        public void canPlayerLevelUp() {

            player.setExperience((player.getExperience()) + 100);
            if (player.getExperience() >= 100) {
                player.setLevel(player.getLevel() + 1);
            }
            assertEquals(2, player.getLevel());
        }

        @Test
        @DisplayName("Is damage cost to the Monster as high as the Players damage?")
        public void isDamageCostCorrect() {

            int damageDifference = (monster.getHealth() - player.getDamage() );
            player.setDamage(player.getStrength() + player.getLevel() / 4 + 1);
            monster.setHealth(monster.getHealth() - player.getDamage());

            assertEquals((monster.getHealth() + player.getDamage()), damageDifference);
    }

}



