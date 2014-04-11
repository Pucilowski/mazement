package com.pucilowski.navigation.ui;

import com.pucilowski.navigation.environment.Environment;
import com.pucilowski.navigation.environment.enums.GridType;
import com.pucilowski.navigation.environment.events.NewGridEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by martin on 29/12/13.
 */

/*@Configuration
@ComponentScan*/
public class Main extends Environment {

    GUI gui;

    public Main() {
        System.out.println("Main" + this);

        //super();

        gui = new GUI(this);


    }

    public void init() {
        bus.post(new NewGridEvent(GridType.SQUARE, 20, 20));

        onRefresh();
    }

    @Override
    public void onRefresh() {
        gui.frame.repaint();
    }

    public static void main(String[] args) {
        new Main();

        //ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        //Main main = context.getBean(Main.class);

        //MessagePrinter printer = context.getBean(MessagePrinter.class);
        //printer.printMessage();

    }

}
