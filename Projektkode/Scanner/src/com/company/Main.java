package com.company;

//import shufflerCode.Shuffler;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
public class Main
{

    public static void main(String[] args) throws Exception
    {
        //new ShufflerExecuter().Execute();
        new Compiler().Compile();
        //Process p = Runtime.getRuntime().exec();
        //new OutputCompiler().run();

        /*JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null,"C:\\Users\\alexa\\Desktop\\SW4\\SW4_github\\P4-kursus\\Projektkode\\Scanner\\src\\shufflerCode\\Shuffler.java");
        System.out.println(result);
        File classesDir = new File("C:\\Users\\alexa\\Desktop\\SW4\\SW4_github\\P4-kursus\\Projektkode\\Scanner\\src\\shufflerCode");
        URLClassLoader classLoader;
        try
        {
            classLoader = URLClassLoader.newInstance(new URL[]{classesDir.toURI().toURL()});
            Class<?> cls;
            cls = Class.forName("shufflerCode.Shuffler", true, classLoader);
            //Shuffler instance = (Shuffler)cls.newInstance();
            //instance.ShufflerRun();
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/
        /*try
        {
            System.out.println("************");
            runProcess("javac C:\\Users\\alexa\\Desktop\\SW4\\SW4_github\\P4-kursus\\Projektkode\\Scanner\\src\\shufflerCode\\Shuffler.java");
            System.out.println("************");
            runProcess("java C:\\Users\\alexa\\Desktop\\SW4\\SW4_github\\P4-kursus\\Projektkode\\Scanner\\src\\shufflerCode\\Shuffler.java");
        } catch (Exception e)
        {
            e.printStackTrace();
        }*/
    }

    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());

        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }

    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }

}

