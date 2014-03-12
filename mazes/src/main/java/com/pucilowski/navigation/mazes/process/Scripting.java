package com.pucilowski.navigation.mazes.process;

import java.io.IOException;

/**
 * Created by martin on 20/12/13.
 */
public class Scripting {

    public Scripting() throws IOException {

        String script = "script.lua";
        //List<String> script = Files.readAllLines(Paths.get("./script.lua"), Charset.defaultCharset());

/*
        LuaValue _G = JsePlatform.standardGlobals();
        _G.get("dofile").call( LuaValue.valueOf(script) );*/


    }

    public static void main(String[] args) throws IOException {

        Scripting s = new Scripting();



    }

}
