package com.Pernilla.project;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import static com.Pernilla.project.Colors.*;

public class Monster implements ICombat {

    private String monster = "";
    private int strength = 0;
    private int health = 0;
    private int damage;
    boolean stillChoosing;

    public String getMonster() {
        return monster;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Monster(String monster, int strength, int health) {
        this.monster = monster;
        this.strength = strength;
        this.health = health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void createMonster(Player newPlayer, Monster newMonster) {
        Game game = new Game();

        System.out.println("ENTERING THE DUNGEON");
        Random random = new Random();
        int i = random.nextInt(0, 5);
        switch (i) {
            case 0:
                newMonster.setMonster("Ogre");
                newMonster.setHealth(100);
                newMonster.setStrength(40);
                break;
            case 1:
                newMonster.setMonster("Troll");
                newMonster.setHealth(100);
                newMonster.setStrength(35);
                break;
            case 2:
                newMonster.setMonster("Orc");
                newMonster.setHealth(90);
                newMonster.setStrength(30);
                break;
            case 3:
                newMonster.setMonster("Minotaur");
                newMonster.setHealth(90);
                newMonster.setStrength(25);
                break;
            case 4:
                newMonster.setMonster("Zombie");
                newMonster.setHealth(80);
                newMonster.setStrength(20);
                break;
        }
        System.out.println("You have awakened the " + newMonster.getMonster()
                + " and it is charging against you!\nWhat will you do?");
        game.combatMenu(newPlayer, newMonster);

    }

    public void monsterStatus(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        System.out.println("The " + getMonster() + " has " + getStrength() +
                " in strength and " + getHealth() + " in health");

        game.combatMenu(newPlayer, newMonster);
    }
    public void monsterDied (Player newPlayer, Monster newMonster) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        newPlayer.setExperience(newPlayer.getExperience() + 10);
        newPlayer.setHealth(100);
        newPlayer.setMonsterSkulls(newPlayer.getMonsterSkulls() + 1);

        if(newPlayer.getMonsterSkulls() == 5) {
            System.out.println(newPlayer.getPlayerName() + " - YOU ARE THE RULER OF THIS DUNGEON AND WILL BE UNTIL THE END OF DAYS!");
            System.out.println("\nIf you want to start over - Press 1." +
                    "\nTo exit the game - Press 9.");
            do {
                try{
                    stillChoosing = false;
                    int i = scanInt.nextInt();
                    if (i == 1) {
                        System.out.println("Good luck with your new game, former dungeon ruler.");
                    } else if (i == 9) {
                        System.out.println("Live long and prosper, almighty " + newPlayer.getPlayerName());
                        System.exit(0);
                    }
                }catch(InputMismatchException e) {
                    System.out.println("Something went wrong; Please choose digits 1 or 9.");
                    scanner.next();
                    stillChoosing = true;
                }
            } while(stillChoosing);
        }

            System.out.println("\nCongrats! You have slayed the " + newMonster.getMonster() + " and can truly call " +
                    "yourself a monster slayer!" +
                    "\n\nIf you want to become the true ruler of this dungeon, you need to collect five monster skulls." +
                    "\nYou can buy some help to achieve this in the shop, but this will cost you skulls..." +
                    "\n\n Would you like to continue in to the dungeon or are you a quitter?" +
                    "\n Press 1 to continue" +
                    "\n Press 2 to quit");
            switch (scanner.next()) {
                case "1":
                    newMonster.createMonster(newPlayer,newMonster);
                    break;
                case "2":
                    System.out.println("Bye bye!");
                    System.exit(0);
                default:
                    System.out.println("Please try again;" +
                            "\n Press 1 to continue" +
                            "\n Press 2 to quit");
            }
    }
    @Override
    public void fight(Player newPlayer, Monster newMonster) {

        newMonster.setDamage(newMonster.getStrength() / 4 + 1);

        calculateDamage(newPlayer, newMonster);
    }
    @Override
    public void calculateDamage(Player newPlayer, Monster newMonster) {
        Game game = new Game();
        newPlayer.setHealth(newPlayer.getHealth() - newMonster.getDamage());

        if (newPlayer.getHealth() < 1) {
            newPlayer.playerLost(newPlayer, newMonster);
        } else {
            System.out.printf("The " + newMonster.getMonster() +" managed to cause damage to your health. It is now %s" +
                    newPlayer.getHealth() + "%s points.\n", GREEN, RESET);
            game.combatMenu(newPlayer, newMonster);
        }
    }
}