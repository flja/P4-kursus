package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception
    {
        for (List<Integer> a: Data.generateTable())
        {
            for (Integer b : a)
            {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        //Scanner1 s = new Scanner1();
        //for (Token token : s.Lexer())
        //{
        //    System.out.println(token.getClass().getSimpleName());

	// write your code here
    }


}


