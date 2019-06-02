package gui;

import javax.swing.*;

public class Gui {

    public Gui(){
        JFrame canvas = new MainGameGui("Transit-Game");//Main window of application

        canvas.setSize(1000, 700);//sets dimensions

        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//terminate on EXIT_ON_CLOSE

        canvas.setVisible(true);//makes visible
    }

}
