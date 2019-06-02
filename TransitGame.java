import maps.*;
import disasters.*;
import transitlines.*;
import gui.*;
import javax.swing.*;

public class TransitGame {
    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable(){//create the frame on the eventdispatching thread.
            public void run(){
                new Gui();
            }
        });

    }
}
