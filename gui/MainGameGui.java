package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JButton;

import maps.*;
import transitlines.*;

public class MainGameGui extends JFrame{

    mapInterpreter MapDisplay = new mapInterpreter();

    int selectedx; // Where selector X coordinate is.
    int selectedy; // Selector Y Coordinate.
    TransitLine selectedTransit; //Stores which transit type is selected to be inserted into map.


    public MainGameGui(String title){//constructor for MainGameGui.

        super(title);

        setLayout(new FlowLayout());//sets layout for Jframe.

        JPanel mainPanel = new JPanel();//Creates JPanel to store main layout for all game components.
        add(mainPanel);//adds mainPanel to JFrame.
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints mainConstraints = new GridBagConstraints();

        //Adds map graphics to Main JPanel.
        mainConstraints.gridx = 1;
        mainConstraints.gridy = 0;
        mainPanel.add(MapDisplay, mainConstraints);

        //Create buttons.
        JButton button1 = new JButton("UP");
        button1.setPreferredSize(new Dimension(60, 20));
        JButton button2 = new JButton("DOWN");
        button2.setPreferredSize(new Dimension(60, 20));
        JButton button3 = new JButton("LEFT");
        button3.setPreferredSize(new Dimension(60, 20));
        JButton button4 = new JButton("RIGHT");
        button4.setPreferredSize(new Dimension(60, 20));

        JButton button5 = new JButton("PLACE");
        JButton button6 = new JButton("REMOVE");

        JButton button7 = new JButton("ROAD");
        JButton button8 = new JButton("ROAD WITH BIKEPATH");
        JButton button9 = new JButton("GAS TRAIN");
        JButton button10 = new JButton("ELECTRIC TRAIN");
        JButton button11 = new JButton("ORBITAL TRAIN");
        JButton button13 = new JButton("END TURN");

        //Creates ImageIcons for use in key.
        ImageIcon ocean = new ImageIcon(getClass().getResource("BoardImages/Ocean-01.png"));
        ImageIcon selector = new ImageIcon(getClass().getResource("BoardImages/Selector-01.png"));
        ImageIcon bikepath = new ImageIcon(getClass().getResource("BoardImages/Bikepath-01.png"));
        ImageIcon desert = new ImageIcon(getClass().getResource("BoardImages/Desert-01.png"));
        ImageIcon downtown = new ImageIcon(getClass().getResource("BoardImages/Downtown-01.png"));
        ImageIcon electrictrain = new ImageIcon(getClass().getResource("BoardImages/ElectricTrain-01.png"));
        ImageIcon farm = new ImageIcon(getClass().getResource("BoardImages/Farm-01.png"));
        ImageIcon forest = new ImageIcon(getClass().getResource("BoardImages/Forest-01.png"));
        ImageIcon gastrain = new ImageIcon(getClass().getResource("BoardImages/GasTrain-01.png"));
        ImageIcon grass = new ImageIcon(getClass().getResource("BoardImages/Grass-01.png"));
        ImageIcon home = new ImageIcon(getClass().getResource("BoardImages/Home-01.png"));
        ImageIcon industrial = new ImageIcon(getClass().getResource("BoardImages/Industrial-01.png"));
        ImageIcon orbitaltrain = new ImageIcon(getClass().getResource("BoardImages/OrbitalTrain-01.png"));
        ImageIcon river = new ImageIcon(getClass().getResource("BoardImages/River-01.png"));
        ImageIcon road = new ImageIcon(getClass().getResource("BoardImages/Road-01.png"));
        ImageIcon rock = new ImageIcon(getClass().getResource("BoardImages/Rock-01.png"));
        ImageIcon shopping = new ImageIcon(getClass().getResource("BoardImages/Shopping-01.png"));

        JPanel buttonLayoutA = new JPanel();//ButtonLayoutA for Directional Buttons;
        buttonLayoutA.setLayout(new GridBagLayout());
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 1;
        mainPanel.add(buttonLayoutA, mainConstraints);//adds Button Layout A to Main JPanel.
        GridBagConstraints constraintsA = new GridBagConstraints();

        //Place directional Buttons in buttonLayoutA.
        constraintsA.gridx = 1;
        constraintsA.gridy = 0;
        buttonLayoutA.add(button1, constraintsA);
        constraintsA.gridx = 1;
        constraintsA.gridy = 2;
        buttonLayoutA.add(button2, constraintsA);
        constraintsA.gridx = 0;
        constraintsA.gridy = 1;
        buttonLayoutA.add(button3, constraintsA);
        constraintsA.gridx = 2;
        constraintsA.gridy = 1;
        buttonLayoutA.add(button4, constraintsA);

        JPanel buttonLayoutB = new JPanel();//buttonLayoutB for place, remove, and end turn buttons;
        buttonLayoutB.setLayout(new GridBagLayout());
        mainConstraints.gridx = 1;
        mainConstraints.gridy = 1;
        mainPanel.add(buttonLayoutB, mainConstraints);//adds Button Layout B to Main JPanel.
        GridBagConstraints constraintsB = new GridBagConstraints();

        //Adds place, remove, and end turn buttons to Button Layout B.
        constraintsB.gridx = 0;
        constraintsB.gridy = 0;
        buttonLayoutB.add(button5, constraintsB);
        button5.setPreferredSize(new Dimension(400, 20));
        constraintsB.gridx = 0;
        constraintsB.gridy = 1;
        buttonLayoutB.add(button6, constraintsB);
        button6.setPreferredSize(new Dimension(400, 20));
        constraintsB.gridx = 0;
        constraintsB.gridy = 2;
        buttonLayoutB.add(button13, constraintsB);
        button13.setPreferredSize(new Dimension(400, 20));

        JPanel infoPanel = new JPanel();//Creates JPanel for Key.
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints infoPanelConstraints = new GridBagConstraints();
        infoPanelConstraints.anchor = GridBagConstraints.WEST;

        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        mainPanel.add(infoPanel, mainConstraints);//Adds Key to Main JPanel.

        //Adds components to Key Panel.
        JLabel roadKey = new JLabel(road);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 0;
        infoPanel.add(roadKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 0;
        button7.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(button7, infoPanelConstraints);

        JLabel bikepathKey = new JLabel(bikepath);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 1;
        infoPanel.add(bikepathKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 1;
        button8.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(button8, infoPanelConstraints);

        JLabel gastrainKey = new JLabel(gastrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 2;
        infoPanel.add(gastrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 2;
        button9.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(button9, infoPanelConstraints);

        JLabel eltrainKey = new JLabel(electrictrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 3;
        infoPanel.add(eltrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 3;
        button10.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(button10, infoPanelConstraints);

        JLabel orbitaltrainKey = new JLabel(orbitaltrain);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 4;
        infoPanel.add(orbitaltrainKey, infoPanelConstraints);

        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 4;
        button11.setPreferredSize(new Dimension(150, 40));
        infoPanel.add(button11, infoPanelConstraints);

        JLabel homeKey = new JLabel(home);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 5;
        infoPanel.add(homeKey, infoPanelConstraints);

        JLabel homeText = new JLabel(" Home ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 5;
        infoPanel.add(homeText, infoPanelConstraints);

        JLabel shoppingKey = new JLabel(shopping);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 6;
        infoPanel.add(shoppingKey, infoPanelConstraints);

        JLabel shoppingText = new JLabel(" Shopping Center ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 6;
        infoPanel.add(shoppingText, infoPanelConstraints);

        JLabel downtownKey = new JLabel(downtown);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 7;
        infoPanel.add(downtownKey, infoPanelConstraints);

        JLabel downtownText = new JLabel(" Downtown ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 7;
        infoPanel.add(downtownText, infoPanelConstraints);

        JLabel industrialKey = new JLabel(industrial);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 8;
        infoPanel.add(industrialKey, infoPanelConstraints);

        JLabel industrialText = new JLabel(" Industrial Zone ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 8;
        infoPanel.add(industrialText, infoPanelConstraints);

        JLabel farmKey = new JLabel(farm);
        infoPanelConstraints.gridx = 0;
        infoPanelConstraints.gridy = 9;
        infoPanel.add(farmKey, infoPanelConstraints);

        JLabel farmText = new JLabel(" Farmland ");
        infoPanelConstraints.gridx = 1;
        infoPanelConstraints.gridy = 9;
        infoPanel.add(farmText, infoPanelConstraints);

        //Adds behaviors to buttons.
        button1.addActionListener(new UpAction());
        button2.addActionListener(new DownAction());
        button3.addActionListener(new LeftAction());
        button4.addActionListener(new RightAction());

        button5.addActionListener(new PlaceAction());
        button6.addActionListener(new RemoveAction());

        button7.addActionListener(new RoadAction());
        button8.addActionListener(new BikeAction());
        button9.addActionListener(new GasTrainAction());
        button10.addActionListener(new ElectricTrainAction());
        button11.addActionListener(new OrbitalTrainAction());
        button13.addActionListener(new EndTurnAction());
    }


    class UpAction implements ActionListener{//For Up button.
        public void actionPerformed(ActionEvent e){
            if (selectedy >= 1){
                selectedy = selectedy - 1;
                MapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class DownAction implements ActionListener{//For Down button.
        public void actionPerformed(ActionEvent e){
            if (selectedy < (MapDisplay.mapLength()-1)){
                selectedy = selectedy + 1;
                MapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class LeftAction implements ActionListener{//For Left button.
        public void actionPerformed(ActionEvent e){
            if (selectedx >= 1){
                selectedx = selectedx - 1;
                MapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class RightAction implements ActionListener{//For Right button.
        public void actionPerformed(ActionEvent e){
            if (selectedx < (MapDisplay.mapLength()-1)){
               selectedx = selectedx + 1;
               MapDisplay.setSelector(selectedx, selectedy);
            }
        }
    }

    class PlaceAction implements ActionListener{//For Place button.
        public void actionPerformed(ActionEvent e){
                MapDisplay.insert(selectedTransit);
        }
    }

    class RemoveAction implements ActionListener{//For remove button.
        public void actionPerformed(ActionEvent e){
                MapDisplay.remove();
        }
    }

    class RoadAction implements ActionListener{//For selecting Road.
        public void actionPerformed(ActionEvent e){
                selectedTransit = TransitLine.ROAD;
        }
    }

    class BikeAction implements ActionListener{//For selecting Bikepath.
        public void actionPerformed(ActionEvent e){
                selectedTransit = TransitLine.BIKEPATH;
        }
    }

    class GasTrainAction implements ActionListener{//For selecting Gas Train.
        public void actionPerformed(ActionEvent e){
                selectedTransit = TransitLine.GASTRAIN;
        }
    }

    class ElectricTrainAction implements ActionListener{//For selecting Electric Train.
        public void actionPerformed(ActionEvent e){
                selectedTransit = TransitLine.ELECTRICTRAIN;
        }
    }

    class OrbitalTrainAction implements ActionListener{//For selecing Orbital Train.
        public void actionPerformed(ActionEvent e){
                selectedTransit = TransitLine.ORBITALTRAIN;
        }
    }

    class EndTurnAction implements ActionListener{//For End Turn button.
        public void actionPerformed(ActionEvent e){
            MapDisplay.endTurn();

        }
    }

}//Ends MainGameGui

class mapInterpreter extends JPanel{//Class for displaying visual map elements.

    Map GameMap = new Map();//Creates new map object.

    int DEFAULT_WIDTH = (GameMap.tileLength() * 40);//Sets width based on map size.
    int DEFAULT_HEIGHT = ((GameMap.tileLength() * 40) + 20);//sets height based on map size.

    //for refreshing map at regular intervals.
    private int myTimerDelay;
    private final Timer myTimer;

    int selectorX;//for keeping track of selected coordinate in map.
    int selectorY;//for keeping track of selected coordinate in map.

    //Sets image icons for map tiles.
    ImageIcon ocean = new ImageIcon(getClass().getResource("BoardImages/Ocean-01.png"));
    ImageIcon selector = new ImageIcon(getClass().getResource("BoardImages/Selector-01.png"));
    ImageIcon bikepath = new ImageIcon(getClass().getResource("BoardImages/Bikepath-01.png"));
    ImageIcon desert = new ImageIcon(getClass().getResource("BoardImages/Desert-01.png"));
    ImageIcon downtown = new ImageIcon(getClass().getResource("BoardImages/Downtown-01.png"));
    ImageIcon electrictrain = new ImageIcon(getClass().getResource("BoardImages/ElectricTrain-01.png"));
    ImageIcon farm = new ImageIcon(getClass().getResource("BoardImages/Farm-01.png"));
    ImageIcon forest = new ImageIcon(getClass().getResource("BoardImages/Forest-01.png"));
    ImageIcon gastrain = new ImageIcon(getClass().getResource("BoardImages/GasTrain-01.png"));
    ImageIcon grass = new ImageIcon(getClass().getResource("BoardImages/Grass-01.png"));
    ImageIcon home = new ImageIcon(getClass().getResource("BoardImages/Home-01.png"));
    ImageIcon industrial = new ImageIcon(getClass().getResource("BoardImages/Industrial-01.png"));
    ImageIcon orbitaltrain = new ImageIcon(getClass().getResource("BoardImages/OrbitalTrain-01.png"));
    ImageIcon river = new ImageIcon(getClass().getResource("BoardImages/River-01.png"));
    ImageIcon road = new ImageIcon(getClass().getResource("BoardImages/Road-01.png"));
    ImageIcon rock = new ImageIcon(getClass().getResource("BoardImages/Rock-01.png"));
    ImageIcon shopping = new ImageIcon(getClass().getResource("BoardImages/Shopping-01.png"));
    ImageIcon win = new ImageIcon(getClass().getResource("BoardImages/Win-01.png"));
    ImageIcon lose = new ImageIcon(getClass().getResource("BoardImages/Lose-01.png"));
    ImageIcon fires = new ImageIcon(getClass().getResource("BoardImages/Fire-01.png"));
    ImageIcon tornadoes = new ImageIcon(getClass().getResource("BoardImages/Tornado-01.png"));

    //Declares Image for use in drawImage() method. Does not initialize.
    Image oceanTile;
    Image selectorTile;
    Image bikepathTile;
    Image desertTile;
    Image downtownTile;
    Image electrictrainTile;
    Image farmTile;
    Image forestTile;
    Image gastrainTile;
    Image grassTile;
    Image homeTile;
    Image industrialTile;
    Image orbitaltrainTile;
    Image riverTile;
    Image roadTile;
    Image rockTile;
    Image shoppingTile;
    Image youWin;
    Image youLose;
    Image fireTile;
    Image tornadoTile;

    public mapInterpreter(){//constructor for mapInterpreter.

        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setVisible(true);
        this.setBackground(Color.WHITE);

        myTimerDelay = 100;//sets refresh rate for map.
        myTimer = new Timer(myTimerDelay, gameTimer);
        myTimer.start();

        setImageFiles();//initializes tile Image with ImageIcons set above.
        setSelector(0,0);

    }

    public void setImageFiles(){//initializes tile Image with ImageIcons set above.

        oceanTile = ocean.getImage();
        selectorTile = selector.getImage();
        bikepathTile = bikepath.getImage();
        desertTile = desert.getImage();
        downtownTile = downtown.getImage();
        electrictrainTile = electrictrain.getImage();
        farmTile = farm.getImage();
        forestTile = forest.getImage();
        gastrainTile = gastrain.getImage();
        grassTile = grass.getImage();
        homeTile = home.getImage();
        industrialTile = industrial.getImage();
        orbitaltrainTile = orbitaltrain.getImage();
        riverTile = river.getImage();
        roadTile = road.getImage();
        rockTile = rock.getImage();
        shoppingTile = shopping.getImage();
        youWin = win.getImage();
        youLose = lose.getImage();
        fireTile = fires.getImage();
        tornadoTile = tornadoes.getImage();

    }

        public void paintComponent(Graphics g){

            super.paintComponent(g);

            if(!GameMap.winCondition()){//Draws map if win Condition has not been met.

                //Cycles through tile array in map and sets Images based on array values.
                for(int a = 0; a < GameMap.tileLength(); a++){
                    for(int b = 0; b < GameMap.tileLength(); b++){

                        //sets pixel coordinates for image display.
                        int xcoord = a * 40;
                        int ycoord = b * 40;

                        //Draws image based on tile array in map class.
                        if(GameMap.tileReturn(a, b) == "[OCEAN]"){
                            g.drawImage(oceanTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[BKPTH]"){
                            g.drawImage(bikepathTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[DESRT]"){
                            g.drawImage(desertTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[DOWNT]"){
                            g.drawImage(downtownTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[ELTRN]"){
                            g.drawImage(electrictrainTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[FAARM]"){
                            g.drawImage(farmTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[FORST]"){
                            g.drawImage(forestTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[GSTRN]"){
                            g.drawImage(gastrainTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[GRASS]"){
                            g.drawImage(grassTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[HOOME]"){
                            g.drawImage(homeTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[INDUS]"){
                            g.drawImage(industrialTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[ORTRN]"){
                            g.drawImage(orbitaltrainTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[RIVER]"){
                            g.drawImage(riverTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[ROADS]"){
                            g.drawImage(roadTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[ROCKS]"){
                            g.drawImage(rockTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[SHOPP]"){
                            g.drawImage(shoppingTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "FIRES"){
                            g.drawImage(fireTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[TRNDO]"){
                            g.drawImage(tornadoTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[FLOOD]"){
                            g.drawImage(riverTile, xcoord, ycoord, null);
                        }
                        if(GameMap.tileReturn(a,b) == "[HURRI]"){
                            g.drawImage(oceanTile, xcoord, ycoord, null);
                        }
                    }
                }

                g.drawImage(selectorTile, (selectorX * 40), (selectorY * 40), null);//Draws selector square on map.

                int numDisplayHeight = GameMap.tileLength() * 40 + 15;//sets position of stat display.
                g.drawString(bankDisplay(), 0, numDisplayHeight);//draws stat display for bank.
                g.drawString(turnDisplay(), 75, numDisplayHeight);//draws stat display for turn counter.
            }

            if(GameMap.winCondition()){//Draws you win image if win condition is met.
                g.drawImage(youWin, 0, 0, null);
            }
            if((!GameMap.winCondition())&&(GameMap.turnCounter == 16)){//Draws you lose image if game is complete and win condition not met.
                g.drawImage(youLose, 0, 0, null);
            }
        }

        public void redraw(){//redraws map. Called when refresh occurs.
        this.repaint();

        }

        public int mapLength(){//returns length of map.
            return GameMap.tileLength();
        }

        public void setSelector(int x, int y){//sets selector x and y coordinates on map.
            selectorX = x;
            selectorY = y;
        }

        public void insert(TransitLine selection){//Inserts selected transitline into tile array in map.
            GameMap.insertTransit(selectorX, selectorY, selection);
        }

        public void endTurn(){//actions that should occur at end of turn.

            GameMap.turnCounter ++;//increments turn counter.

            if(GameMap.winCondition()){//ends method is win condition met.
                return;
            }

            GameMap.resetDisasterArray();//resets disaster array in map to null values.
            GameMap.bank = GameMap.bank + GameMap.TurnValueFromTransit();//adds value to bank based on turn income.
            GameMap.incrementCo2();//adds Co2 created this turn to total Co2 count.
            GameMap.disasterStrike(GameMap.totalCo2);//runs disasters.

        }

        public void remove(){//removes transitline from map at selected coordinates.
            GameMap.removeTransit(selectorX, selectorY);
        }

        String bankDisplay(){//sets text for bank stats - used in paintComponent().

            String number = Integer.toString(GameMap.bank);
            String display = "BANK: $";
            return display + number;
        }
        String turnDisplay(){//sets text for turn counter stats - used in paintComponent().

            String number = Integer.toString(GameMap.turnCounter);
            String display = "TURN COUNT: ";
            return display + number;
        }

        ActionListener gameTimer = new ActionListener() {//redraws map based on timer refresh rate.

            public void actionPerformed(ActionEvent theEvent){
                redraw();
            }
        };
        
}
