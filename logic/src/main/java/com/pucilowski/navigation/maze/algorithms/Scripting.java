package com.pucilowski.navigation.maze.algorithms;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by martin on 20/12/13.
 */
public class Scripting {

    public Scripting() throws IOException {


        List<String> script = Files.readAllLines(Paths.get("./script.lua"), Charset.defaultCharset());



    }

    public static void main(String[] args) throws IOException {

        Scripting s = new Scripting();



    }

}
