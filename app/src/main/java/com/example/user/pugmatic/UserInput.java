package com.example.user.pugmatic;

import java.util.Scanner;

/**
 * Created by stuartbryce on 2017-06-30.
 */

public class UserInput {

    public static int getUserInt() {
        Scanner sc = new Scanner(System.in);
        int answer;
            while (!sc.hasNextInt()) {
                System.out.println("number!");
                sc.next();
            }
            answer = sc.nextInt();
        return answer;
    }

    public static boolean getBoolean() {
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("yes")) {
            System.out.println("yes");
            return true;

        } else {
            System.out.println("no");

            return false;
        }
    }

    public static String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().toLowerCase();
    }

    public static int putMoneyIn(Player player) {
        int amount = 0;
        while (((amount < 1) || (amount > player.getMoneyAmount()))) {
            amount = getUserInt();
        }
        return amount;
    }
}

