package com.Pernilla.project;

import java.util.Scanner;

public class Game {

    Scanner scan = new Scanner(System.in);
    public void Welcome() {
        Monster monster = new Monster("",0,0);

        System.out.println("Welcome to DungeonRun! Hope you come prepared!");
        System.out.println("We are starting to prepare your tombstone. What is your name?");

        monster.createMonster(new Player(((scan.next())),
                        10,5,10,100,10,1,0),
                        new Monster("",0,0));
    }
    public void combatMenu(Player newPlayer, Monster newMonster) {

        Shop shop = new Shop();
        if (newPlayer.getExperience() >= 100) {
            newPlayer.playerLevelUp(newPlayer, newMonster);
        }
            System.out.println("\nChoose one of the following:" +
                    "\n Press 1 to swing your sword at the charging " + newMonster.getMonster() + "." +
                    "\n Press 2 to run away." +
                    "\n Press 3 to se your status." +
                    "\n Press 4 to se the monsters status." +
                    "\n Press 5 to shop." +
                    "\n Press 9 to exit the game");

            switch (scan.next()) {
                case "1":
                    newPlayer.fight(newPlayer, newMonster);
                    break;
                case "2":
                    newPlayer.playerFlee(newPlayer, newMonster);
                    break;
                case "3":
                    newPlayer.playerStatus(newPlayer,newMonster);
                    break;
                case "4":
                    newMonster.monsterStatus(newPlayer, newMonster);
                    break;
                case "5":
                    shop.Shop(newPlayer, newMonster);
                case "9":
                    System.out.println("Bye bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please try again" +
                            "\n Press 1 to swing your sword at it." +
                            "\n Press 2 to run away." +
                            "\n Press 3 to se your status." +
                            "\n Press 4 to se the monsters status." +
                            "\n Press 5 to shop." +
                            "\n Press 9 to exit the game");
            }
            newMonster.fight(newPlayer, newMonster);
    }
}
