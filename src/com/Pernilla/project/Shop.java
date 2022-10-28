package com.Pernilla.project;

import java.util.Scanner;

public class Shop {
    Scanner scan = new Scanner(System.in);
    public void Shop(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        if (newPlayer.getMonsterSkulls() > 0) {
                System.out.println("Welcome to our little weapons-shop! Hope you bring a lot of skulls!" +
                        "\nToday we can offer you: " +
                        "\n Nr 1. Smelly monster - a perfume that will weaken the current monsters strength and the damage she costs you." +
                        "\n Nr 2. Monster mask - this will confuse all the monsters and make it easier for you to dodge and flee." +
                        "\n Nr 3. Heart breaker - the current monsters health will be reduced by a quarter." +
                        "\n The cost for these items is one monsters skull each." +
                        "\n Choose the number of the item that you want to buy, or press 9 to return to the dungeon.");

                switch (scan.next()) {
                    case "1" -> {
                        newMonster.setStrength(newMonster.getStrength() - 10);
                        newPlayer.setMonsterSkulls(newPlayer.getMonsterSkulls() - 1);
                        System.out.println("You have chosen the Smelly Monster-perfume, " +
                                "which makes the monsters knees week... for you." +
                                "\nPlease come back some other time.");
                    }
                    case "2" -> {
                        newPlayer.setAgility(newPlayer.getAgility() + 10);
                        newPlayer.setMonsterSkulls(newPlayer.getMonsterSkulls() - 1);
                        System.out.println("You have bought the Monster mask to confuse the monster. Welcome back!");
                    }
                    case "3" -> {
                        int i = newMonster.getHealth() / 4;
                        newMonster.setHealth(newMonster.getHealth() - i);
                        newPlayer.setMonsterSkulls(newPlayer.getMonsterSkulls() - 1);
                        System.out.println("The poor monster is now heartbroken and will be easier to kill. " +
                                "Welcome back!");
                    }
                    case "9" -> game.combatMenu(newPlayer, newMonster);
                    default -> System.out.println("Something went wrong - please choose a digit from the menu above.");
                }
        } else {
            System.out.println("This shop is open only for customers with monster skulls." +
                    "\nWelcome back when you have some.");
        }
        game.combatMenu(newPlayer, newMonster);
    }
}