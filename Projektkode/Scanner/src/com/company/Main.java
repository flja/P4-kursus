package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Scanner1 scanner = new Scanner1();
        Parser parser = new Parser();
        parser.LLparser(scanner.Lexer());
        //Scanner1 s = new Scanner1();
        //for (Token token : s.Lexer())
        //{
        //    System.out.println(token.getClass().getSimpleName());

	// write your code here
    }


}


