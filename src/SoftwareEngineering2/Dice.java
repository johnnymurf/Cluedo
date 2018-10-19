package SoftwareEngineering2;

import java.util.Random;
/*Basic Dice class two random numbers used to simulate dice roll, bounds set 1 to 6*/
public class Dice {
    private static final int min = 1;
    private static final int max = 6;
    private static Random randomDie1 = new Random();
    private static Random randomDie2 = new Random();

    public static int rollDie1(){
        return randomDie1.nextInt((max - min) + 1) + min;
    }

    public static int rollDie2(){
        return randomDie2.nextInt((max - min) + 1) + min;
    }

    public static int rollTotal(){
        return rollDie1() + rollDie2();
    }
}
