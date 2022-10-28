package com.Pernilla.project;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static com.Pernilla.project.Colors.*;



public class Player implements ICombat {

    private String playerName;
    private int strength = 10;
    private int intelligence = 5;
    private int agility = 10;
    private int health = 100;
    private int experience = 10;
    private int level = 1;
    private int damage;
    private int monsterSkulls;
    boolean chooseWay;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMonsterSkulls() {
        return monsterSkulls;
    }

    public void setMonsterSkulls(int monsterSkulls) {
        this.monsterSkulls = monsterSkulls;
    }

    public Player(String playerName, int strength, int intelligence, int agility, int health, int experience, int level,
                  int monsterSkulls) {
        this.playerName = playerName;
        this.strength = strength;
        this.intelligence = intelligence;
        this.agility = agility;
        this.health = health;
        this.experience = experience;
        this.level = level;
        this.monsterSkulls = monsterSkulls;
    }

    public void playerLevelUp(Player newPlayer, Monster newMonster) {
        Game game = new Game();

            newPlayer.setLevel(newPlayer.getLevel() + 1);
            newPlayer.setStrength(newPlayer.getStrength() + 5);
            newPlayer.setIntelligence(newPlayer.getIntelligence() + 5);
            newPlayer.setAgility(newPlayer.getAgility() + 5);
            newPlayer.setHealth(100);
            newPlayer.setExperience(getExperience() - 100);
            if (getExperience() < 10) {
                setExperience(10);
            }
            System.out.println(newPlayer.getPlayerName() + ", you have leveled up!");
            game.combatMenu(newPlayer, newMonster);
    }
    public void playerLost(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        Scanner scan1 = new Scanner(System.in);

        System.out.println("You have been killed by the " + newMonster.getMonster() +
                " and your tombstone came to it's use, " + newPlayer.getPlayerName() + "!");
        System.out.println("\nPress 1 if you want to try again" +
                "\nPress 9 if you want to quit.");

        switch (scan1.next()) {
            case "1" -> game.Welcome();
            case "9" -> {
            System.out.println("Bye bye, poor dead " + newPlayer.getPlayerName() + ".");
            System.exit(0);
            }
            default -> System.out.println("Something went wrong; Please Press 1 to try again or 9 to exit.");
        }
    }
    public void playerDidDodge(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        Random random = new Random();
        int i = random.nextInt(0, 50);
        if (i <= newPlayer.getAgility()) {
            System.out.println("You managed to dodge the attack!");
            game.combatMenu(newPlayer, newMonster);
        } else {
            System.out.println("You were to slow!");
            newMonster.fight(newPlayer, newMonster);
        }
    }
    public void playerFlee(Player newPlayer, Monster newMonster) {
        Random random = new Random();
        int i = random.nextInt(0,50);
        if(i <= newPlayer.getAgility()) {
            System.out.println("You managed to flee out of the Dungeon! You got rid of the " + newMonster.getMonster() +
                    "." +
                    "\nPress 1 to re-enter the Dungeon and try your luck again" +
                    "\nPress 9 to quit while you are still alive");
            Scanner scan2 = new Scanner(System.in);
            switch (scan2.next()) {
                case "1" -> newMonster.createMonster(newPlayer, newMonster);
                case "9" -> {
                    System.out.println("Bye bye");
                    System.exit(0);
                }
                default -> System.out.println("Something went wrong - Press 1 to try again or 9 to exit the game.");
            }
        } else {
            System.out.println("The " + newMonster.getMonster() + " caught you in your cowardly flight!");
            newMonster.fight(newPlayer, newMonster);
        }
    }
    public void playerStatus(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        System.out.printf("This is your current status, " + newPlayer.getPlayerName() + ":" +
                "\n%s Strength: " + getStrength() +
                "\n%s%s Intelligence: " + getIntelligence() +
                "\n%s%s Agility: " + getAgility() +
                "\n%s%s Health: " + getHealth() +
                "\n%s%s Experience: " + getExperience() +
                "\n%s%s Level: " + getLevel() +
                "\n%s MonsterSkulls: " + getMonsterSkulls()
                , RED, RESET, YELLOW, RESET, BLUE, RESET, GREEN, RESET, PURPLE, RESET, CYAN, RESET, RED_UNDERLINED, RESET);

        System.out.println("\nPress 1 to go back to the fighting menu or press 9 to exit the game");
        Scanner scans = new Scanner(System.in);
        do {
            try {
                chooseWay = false;
                int x = scans.nextInt();
                if (x == 1) {
                    game.combatMenu(newPlayer, newMonster);
                } else if (x == 9) {
                    System.out.println("Bye bye");
                    System.exit(0);
                }
            } catch (InputMismatchException e) {
                System.out.println("Something went wrong; Please choose digits 1 or 9.");
                scans.next();
                chooseWay = true;
            }
        } while (chooseWay);
    }


    @Override
    public void fight(Player newPlayer, Monster newMonster) {
        Random random = new Random();

        int i = random.nextInt(0,40);
        if(i <= (newPlayer.getIntelligence()+newPlayer.getAgility())) {
            setDamage(newPlayer.getStrength() + newPlayer.getLevel() / 4 + 1);
            System.out.println("You cost damage to the " + newMonster.getMonster() + " by " + getDamage() + ".");
            calculateDamage(newPlayer, newMonster);
        } else {
            System.out.println("Oh no - she dodged your attack! ");
            newMonster.fight(newPlayer, newMonster);
        }
    }
    @Override
    public void calculateDamage(Player newPlayer, Monster newMonster) {
        newMonster.setHealth(newMonster.getHealth() - getDamage());
        newPlayer.setExperience(newPlayer.getExperience() + 10);

        if (newMonster.getHealth() < 1) {
            newMonster.monsterDied(newPlayer, newMonster);
        } else {
            System.out.println("The " + newMonster.getMonster() + "'s health is now "
                    + newMonster.getHealth() + " points." +
                    "\n\nThe " + newMonster.getMonster() + " swings her club at you!" +
            "\n\nWhat will you do?" +
            "\nPress 1 to try and dodge." +
            "\nPress 2 to try running away");
            Scanner scan = new Scanner(System.in);
            switch (scan.next()) {
                case "1" -> newPlayer.playerDidDodge(newPlayer, newMonster);
                case "2" -> newPlayer.playerFlee(newPlayer, newMonster);
                default -> System.out.println("Something went wrong, choose digit 1 or 2.");
            }
        }
    }
}