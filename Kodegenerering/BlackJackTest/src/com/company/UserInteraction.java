package com.company;
import java.util.Scanner;

public class UserInteraction
{
    UtilClass utilClass = new UtilClass();

    public String readInput(String text)
    {
        System.out.println(text);
        Scanner input = new Scanner(System.in);
        String result = input.next();

        return result;
    }
}
