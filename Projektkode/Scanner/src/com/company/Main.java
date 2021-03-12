package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception
    {
        lexer();
	// write your code here
    }

    static String lexer() throws IOException
    {
        String s;

        FileReader fr = new FileReader("C:\\Users\\Alexander Droob\\Desktop\\SW4\\Test.txt");
        BufferedReader br = new BufferedReader(fr);
        while ((s = br.readLine()) != null)
        {
            scanLine(s);

        }

        fr.close();
        return "";
    }


}


