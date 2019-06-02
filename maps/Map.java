package maps;
import transitlines.*;
import disasters.*;

enum Terrain{
    GRASS, ROCK, RIVER, OCEAN, FOREST, DESERT, INDUSTRIAL, HOME, SHOPPING, FARM, DOWNTOWN;
}

public class Map{

    /*
    * To create multiple maps create new class that extends class maps and override constructor.
    */

    public int bank = 15;//keeps track of how much money the player has to use.
    public int turnCounter = 0;//keeps track of turns taken.
    public int totalCo2;//keeps track of accumulative Co2 count.
    public String tile[][] = new String[10][10];//string representation of map.
    Terrain terrainArray[][] = new Terrain[10][10];//array keeps track of terrain placement.
    TransitLine[][] transitArray = new TransitLine[10][10];//array keeps track of transitline placement.
    Disasters[][] disastersArray = new Disasters[10][10];//Array keeps track of where disasters occur.

    public Map(){//constructor for map.

        /*
        * Note that if different map size is required the following arrays must be initialized inside constructor:
        ** tile, transitArray, disastersArray.
        * If different starting bank number is required for new map size, initialize bank inside constructor.
        */

        //Fills terrainArray with values.
        for (int a = 0; a < terrainArray.length; a++){
            for (int b = 0; b < terrainArray.length; b ++){
                terrainArray[a][b] = Terrain.GRASS;
            }
        }

        for (int a = 0; a < 8; a++){
            terrainArray[0][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 6; a++){
            terrainArray[1][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 4; a++){
            terrainArray[2][a] = Terrain.OCEAN;
        }

        for (int a = 0; a < 10; a++){
            for (int b = 3; b < 10; b++){
                terrainArray[b][a] = Terrain.GRASS;
            }
        }

        terrainArray[1][8] = Terrain.FOREST;
        terrainArray[7][0] = Terrain.DESERT;

        terrainArray[9][4] = Terrain.RIVER;
        terrainArray[8][5] = Terrain.RIVER;
        terrainArray[7][6] = Terrain.RIVER;
        terrainArray[7][7] = Terrain.RIVER;
        terrainArray[7][8] = Terrain.RIVER;
        terrainArray[7][9] = Terrain.RIVER;

        terrainArray[8][8] = Terrain.FARM;
        terrainArray[4][8] = Terrain.INDUSTRIAL;
        terrainArray[7][3] = Terrain.SHOPPING;
        terrainArray[4][1] = Terrain.HOME;
        terrainArray[4][4] = Terrain.DOWNTOWN;

        //Sets starting transitArray values to NULLTRANSIT.
        for (int a = 0; a < transitArray.length; a++){
            for (int b = 0; b < transitArray.length; b++){
                transitArray[a][b] = TransitLine.NULLTRANSIT;
            }
        }

        //Sets starting disastersArray values to NULLDISASTER.
        for (int a = 0; a < disastersArray.length; a++){
            for (int b = 0; b < disastersArray.length; b++){
                disastersArray[a][b] = Disasters.NULLDISASTER;
            }
        }

        updateTile();//updates tile based on changes to terrainArray, transitArray, and disastersArray.

    }//ends constructor

    public boolean terrainChecker(int x, int y){///Checks if terrain is buildable.

         if(terrainArray[x][y] == Terrain.GRASS)return true;
         if(terrainArray[x][y] == Terrain.FOREST)return true;
         if(terrainArray[x][y] == Terrain.DESERT)return true;
         if(terrainArray[x][y] == Terrain.RIVER)return true;
         if(terrainArray[x][y] == Terrain.ROCK)return false;
         if(terrainArray[x][y] == Terrain.OCEAN)return false;
         if(terrainArray[x][y] == Terrain.INDUSTRIAL)return false;
         if(terrainArray[x][y] == Terrain.HOME)return false;
         if(terrainArray[x][y] == Terrain.SHOPPING)return false;
         if(terrainArray[x][y] == Terrain.FARM)return false;
         if(terrainArray[x][y] == Terrain.DOWNTOWN)return false;

         else return false;

    }

    public boolean transitChecker(int x, int y,TransitLine selection){//checks against transitArray to see if map coordinte is buildable.

        if(transitArray[x][y] == TransitLine.NULLTRANSIT){
            if (selection != TransitLine.BIKEPATH){
                return true;
            }
        }

        if(transitArray[x][y] == TransitLine.ROAD){
            if(selection == TransitLine.BIKEPATH) return true;
            else return false;
        }

        if(transitArray[x][y] == TransitLine.BIKEPATH)return false;
        if(transitArray[x][y] == TransitLine.GASTRAIN)return false;
        if(transitArray[x][y] == TransitLine.ELECTRICTRAIN)return false;
        if(transitArray[x][y] == TransitLine.ORBITALTRAIN)return false;

        else return false;

    }

    public void displayMap(){//Dipslays tile in command line. Use for debugging purposes.

        for(int t = 0; t < tile.length; t++){
            System.out.print("\n");
            for(int i= 0; i < tile.length; i++){
                System.out.print(tile[t][i]);
            }
        }

    }

    public void updateTile(){//Updates tile with terrainArray, transitArray, and disastersArray.

        for(int t = 0; t < tile.length; t++){ //Updates tile[][] with terrainArray[][]
            for(int i= 0; i < tile.length; i++){
                if (terrainArray[t][i] == Terrain.GRASS){
                    tile[t][i] = "[GRASS]";
                }
                if (terrainArray[t][i] == Terrain.ROCK){
                    tile[t][i] = "[ROCKS]";
                }
                if (terrainArray[t][i] == Terrain.RIVER){
                    tile[t][i] = "[RIVER]";
                }
                if (terrainArray[t][i] == Terrain.OCEAN){
                    tile[t][i] = "[OCEAN]";
                }
                if (terrainArray[t][i] == Terrain.FOREST){
                    tile[t][i] = "[FORST]";
                }
                if (terrainArray[t][i] == Terrain.DESERT){
                    tile[t][i] = "[DESRT]";
                }
            }
        }

        for(int t = 0; t < tile.length; t++){//Updates tile[][] with transitArray[][]
            for(int i= 0; i < tile.length; i++){
                if (transitArray[t][i] == TransitLine.ROAD){
                    tile[t][i] = "[ROADS]";
                }
                if (transitArray[t][i] == TransitLine.BIKEPATH){
                    tile[t][i] = "[BKPTH]";
                }
                if (transitArray[t][i] == TransitLine.GASTRAIN){
                    tile[t][i] = "[GSTRN]";
                }
                if (transitArray[t][i] == TransitLine.ELECTRICTRAIN){
                    tile[t][i] = "[ELTRN]";
                }
                if (transitArray[t][i] == TransitLine.ORBITALTRAIN){
                    tile[t][i] = "[ORTRN]";
                }
            }
        }

      for(int x = 0; x < tile.length; x++){//updates tile with disastersArray.
          for(int y = 0; y < tile.length; y++){
              if(disastersArray[x][y] == Disasters.FLOODING){
                  tile[x][y] = "[FLOOD]";
              }
              if(disastersArray[x][y] == Disasters.HURRICANES){
                  tile[x][y] = "[HURRI]";
              }
              if(disastersArray[x][y] == Disasters.FIRES){
                  tile[x][y] = "[FIRES]";
              }
              if(disastersArray[x][y] == Disasters.TORNADOES){
                  tile[x][y] = "[TRNDO]";
              }
          }
      }

      for(int t = 0; t < tile.length; t++){ //Updates tile[][] with terrainArray for city hubs.
          for(int i= 0; i < tile.length; i++){
              if (terrainArray[t][i] == Terrain.FARM){
                  tile[t][i] = "[FAARM]";
              }
              if (terrainArray[t][i] == Terrain.INDUSTRIAL){
                  tile[t][i] = "[INDUS]";
              }
              if (terrainArray[t][i] == Terrain.DOWNTOWN){
                  tile[t][i] = "[DOWNT]";
              }
              if (terrainArray[t][i] == Terrain.HOME){
                  tile[t][i] = "[HOOME]";
              }
              if (terrainArray[t][i] == Terrain.SHOPPING){
                  tile[t][i] = "[SHOPP]";
              }
          }
      }

    }

    public boolean debitCost(int x, int y, TransitLine selection){//debits cost for builing transitLine.

        int a = 0;

        if (terrainArray[x][y] == Terrain.RIVER){
            a = selection.cost + selection.returnBridgeCost();
            if(a < bank) {
                bank = bank - a;
                return true;
            }
            if(bank < a) return false;
        }
        else {
            a = selection.cost;
            if(a < bank) {
                bank = bank - a;
                return true;
            }
            if(bank < a) return false;
        }
        return false;

    }

    public void insertTransit(int x, int y, TransitLine selection){//inserts transitline at selected coordinates.

        if (terrainChecker(x, y) && transitChecker(x, y, selection)){//uses terrainChecker() and transitChecker() to validate move;

            //uses debitCost() to verfy the cost can be taken from bank and completes debit if possible.
            //If debitCost returns false keeps play from occuring.
            if (debitCost(x, y, selection)){
                transitArray[x][y] = selection;//inserts correct TransitLine value into transitArray.
                updateTile();//updates map using updateTile();
            }

        } else System.out.println("Error");

    }

    int returnVal(int input){
        return input;
    }

    public void removeTransit(int x, int y){//removes transit and debits fee from bank.

        if(transitArray[x][y] != TransitLine.NULLTRANSIT){
            if(bank >= 5){
                transitArray[x][y] = TransitLine.NULLTRANSIT;
                bank = bank - 5;
                updateTile();
            }
        }

    }

    public int TurnValueFromTransit(){//tallies up the income produced this turn.

        int valueCounter = 0;
        int connectionCounter = 0;

        connectionCounter = HubConnectedCounter("[HOOME]", "[ROADS]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter("[HOOME]", "[GSTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter("[HOOME]", "[ELTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter("[HOOME]", "[ORTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter("[HOOME]", "[BKPTH]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter("[DOWNT]", "[ROADS]", "[HOOME]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter("[DOWNT]", "[GSTRN]", "[HOOME]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter("[DOWNT]", "[ELTRN]", "[HOOME]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter("[DOWNT]", "[ORTRN]", "[HOOME]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter("[DOWNT]", "[BKPTH]", "[HOOME]", "[SHOPP]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter("[SHOPP]", "[ROADS]", "[DOWNT]", "[HOOME]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter("[SHOPP]", "[GSTRN]", "[DOWNT]", "[HOOME]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter("[SHOPP]", "[ELTRN]", "[DOWNT]", "[HOOME]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter("[SHOPP]", "[ORTRN]", "[DOWNT]", "[HOOME]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter("[SHOPP]", "[BKPTH]", "[DOWNT]", "[HOOME]", "[INDUS]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter("[INDUS]", "[ROADS]", "[DOWNT]", "[SHOPP]", "[HOOME]", "[FAARM]");
        connectionCounter = (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter("[INDUS]", "[GSTRN]", "[DOWNT]", "[SHOPP]", "[HOOME]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter("[INDUS]", "[ELTRN]", "[DOWNT]", "[SHOPP]", "[HOOME]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter("[INDUS]", "[ORTRN]", "[DOWNT]", "[SHOPP]", "[HOOME]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter("[INDUS]", "[BKPTH]", "[DOWNT]", "[SHOPP]", "[HOOME]", "[FAARM]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        connectionCounter = HubConnectedCounter("[FAARM]", "[ROADS]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[HOOME]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ROAD.value);
        connectionCounter = HubConnectedCounter("[FAARM]", "[GSTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[HOOME]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.GASTRAIN.value);
        connectionCounter = HubConnectedCounter("[FAARM]", "[ELTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[HOOME]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ELECTRICTRAIN.value);
        connectionCounter = HubConnectedCounter("[FAARM]", "[ORTRN]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[HOOME]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.ORBITALTRAIN.value);
        connectionCounter = HubConnectedCounter("[FAARM]", "[BKPTH]", "[DOWNT]", "[SHOPP]", "[INDUS]", "[HOOME]");
        valueCounter = valueCounter + (connectionCounter * TransitLine.BIKEPATH.value);

        valueCounter = valueCounter/2;

        double desertsubtract = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if(tile[a][b] == "[DESRT]"){
                    desertsubtract = desertsubtract + 0.5;
                }
            }
        }

        valueCounter = valueCounter - ((int)(desertsubtract));
        return valueCounter;

    }

    //Used for path detection to determine if specified starting city hub is connected to another hub.
    public boolean isHubConnected(String start, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        int startX = 0;
        int startY = 0;
        boolean isX1Connected = false;
        boolean isX2Connected = false;
        boolean isY1Connected = false;
        boolean isY2Connected = false;
        int connections = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (tile[a][b] == start){
                    startX = a;
                    startY = b;
                }
            }
        }

        if(isXConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX1Connected = true;
            connections++;
        }
        if(isXConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX2Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY1Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY2Connected = true;
            connections++;
        }

        if(connections > 0){
            return true;
        }
        return false;

    }

    //returns the number of connections from specified starting city hub.
    public int HubConnectedCounter(String start, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        int startX = 0;
        int startY = 0;
        boolean isX1Connected = false;
        boolean isX2Connected = false;
        boolean isY1Connected = false;
        boolean isY2Connected = false;
        int connections = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (tile[a][b] == start){
                    startX = a;
                    startY = b;
                }
            }
        }

        if(isXConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX1Connected = true;
            connections++;
        }
        if(isXConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isX2Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, 1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY1Connected = true;
            connections++;
        }
        if(isYConnected(startX, startY, -1, transitType, Loc1, Loc2, Loc3, Loc4)){
            isY2Connected = true;
            connections++;
        }

        return connections;

    }

    //takes in starting coordinates(x and y), negative or positive direction from start along x axis(int dir),
    //takes in type of transitline to look for - String transitType;
    //Loc1 - 4 are hold what city hub we are looking for connections to.
    public boolean isXConnected(int startX, int startY, int dir, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        //checks to see if transitType selected starts at specified coordinate;
        try{
            if(tile[startX + dir][startY] == transitType){

                //Sets coordinate values being used currently for search;
                int currentX = (returnVal(startX) + dir);
                int currentY = returnVal(startY);

                //Keeps track of coordinate values used previously in search;
                int previousX = returnVal(startX);
                int previousY = returnVal(startY);

                int iteration = 0;//keeps track of iterations through loop to avoid endless looping issue;

                FindPath:{
                    do{
                        TransitSearchBlock:{//changes current and previous coordinates if transitline path continues.

                            try{
                                if(tile[currentX + 1][currentY] == transitType){
                                    if((currentX + 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == transitType){
                                    if((currentX - 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == transitType){
                                    if((currentY + 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == transitType){
                                    if((currentY - 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                        }//closes TransitSearchBlock.

                        IsConnectedBlock:{//returns true if transitline is connected to city hub.

                            //Returns true if transitline is connected at X+1 direction;
                            try{
                                if(tile[currentX + 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at X-1 direction;
                            try{
                                if(tile[currentX - 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y+1 direction;
                            try{
                                if(tile[currentX][currentY + 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y-1 direction;
                            try{
                                if(tile[currentX][currentY - 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                        }//closes IsConnectedBlock.

                        iteration++;

                    }while (iteration <= 15);
                }

                if (iteration == 15){
                return false;
                }
            }

            return false;

        }catch(Exception e){
        }

        return false;

    }

    //determines if specified city hub is connected in Y + 1 or Y - 1 directions.
    public boolean isYConnected(int startX, int startY, int dir, String transitType, String Loc1, String Loc2, String Loc3, String Loc4){

        //checks to see if transitType selected starts at specified coordinate;
        try{
            if(tile[startX][startY + dir] == transitType){

                //Sets coordinate values being used currently for search;
                int currentX = returnVal(startX);
                int currentY = (returnVal(startY) + dir);

                //Keeps track of coordinate values used previously in search;
                int previousX = returnVal(startX);
                int previousY = returnVal(startY);

                int iteration = 0;//keeps track of iterations through loop to avoid endless looping issue;

                FindPath:{
                    do{
                        TransitSearchBlock:{

                            try{
                                if(tile[currentX + 1][currentY] == transitType){
                                    if((currentX + 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == transitType){
                                    if((currentX - 1) != previousX){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentX = currentX - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == transitType){
                                    if((currentY + 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY + 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == transitType){
                                    if((currentY - 1) != previousY){
                                        previousX = returnVal(currentX);
                                        previousY = returnVal(currentY);
                                        currentY = currentY - 1;
                                        break TransitSearchBlock;
                                    }
                                }
                            }catch(Exception e){
                            }

                        }

                        IsConnectedBlock:{
                            //Returns true if transitline is connected at X+1 direction;
                            try{
                                if(tile[currentX + 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX + 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at X-1 direction;
                            try{
                                if(tile[currentX - 1][currentY] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX - 1][currentY] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y+1 direction;
                            try{
                                if(tile[currentX][currentY + 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY + 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            //returns true if transitline is connected at Y-1 direction;
                            try{
                                if(tile[currentX][currentY - 1] == Loc1){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc2){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc3){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                            try{
                                if(tile[currentX][currentY - 1] == Loc4){
                                    return true;
                                }
                            }catch (Exception e){
                            }

                        }

                        iteration++;

                    }while (iteration <= 15);
                }

                if (iteration == 15){
                    return false;
                }

            }

            return false;
        }catch(Exception e){
        }
        return false;
    }


    public void resetDisasterArray(){//Sets disastersArray to null to ensure that disaster display only lasts one turn.

        for (int a = 0; a < disastersArray.length; a++){
            for (int b = 0; b < disastersArray.length; b++){
                disastersArray[a][b] = Disasters.NULLDISASTER;
            }
        }
        updateTile();
    }


    public void disasterStrike(int totalCo2) {//cycles through array and calls runDisaster(totalCo2);

        //RIVER - FLOODING
        if(turnCounter % 2 == 0){

            for(int i = 0; i < terrainArray.length; i++){
                for(int a = 0; a < terrainArray.length; a++){

                    if(terrainArray[i][a] == Terrain.RIVER){

                        int disasterX = i;
                        int disasterY = a;
                        int disasterSize = Disasters.FLOODING.runDisaster(totalCo2);

                        if(disasterSize >= 1){//LEVEL 1

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                        }

                        if(disasterSize >= 2){//LEVEL 2

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }
                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY - 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY + 1] = Disasters.FLOODING;
                            }catch (Exception e){
                            }
                        }
                    }
                }
            }
        }

        //OCEAN - HURRICANE
        if (turnCounter % 4 == 0){

            for(int i = 0; i < terrainArray.length; i++){
                for(int a = 0; a < terrainArray.length; a++){

                    if(terrainArray[i][a] == Terrain.OCEAN){

                        int disasterX = i;
                        int disasterY = a;
                        int disasterSize = Disasters.HURRICANES.runDisaster(totalCo2);

                        if(disasterSize >= 1){//LEVEL 1

                            try {
                                transitArray[disasterX + 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 1][disasterY - 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 1][disasterY - 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 1][disasterY + 1] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 1][disasterY + 1] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                        }

                        if(disasterSize >= 2){//LEVEL 2

                            try {
                                transitArray[disasterX + 2][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 2][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX + 2][disasterY - 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX + 2][disasterY - 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }

                            try {
                                transitArray[disasterX - 2][disasterY + 2] = TransitLine.NULLTRANSIT;
                                disastersArray[disasterX - 2][disasterY + 2] = Disasters.HURRICANES;
                            }catch (Exception e){
                            }
                        }
                    }
                }
            }
        }

          //DESERT - DESERTIFICATION
          if(turnCounter % 2 == 0){

              for(int i = 0; i < terrainArray.length; i++){
                  for(int a = 0; a < terrainArray.length; a++){

                      if(terrainArray[i][a] == Terrain.DESERT){

                          int disasterX = i;
                          int disasterY = a;
                          int disasterSize = Disasters.DESERTIFICATION.runDisaster(totalCo2);

                          if(disasterSize >= 1){//LEVEL 1
                              try {
                                  if(terrainArray[disasterX + 1][disasterY] == Terrain.GRASS){
                                      terrainArray[disasterX + 1][disasterY] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX - 1][disasterY] == Terrain.GRASS){
                                      terrainArray[disasterX - 1][disasterY] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX][disasterY + 1] == Terrain.GRASS){
                                      terrainArray[disasterX][disasterY + 1] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                              try {
                                  if(terrainArray[disasterX][disasterY - 1] == Terrain.GRASS){
                                      terrainArray[disasterX][disasterY - 1] = Terrain.DESERT;
                                  }
                              }catch (Exception e){
                              }

                        }

                        if(disasterSize >= 2){//LEVEL 2
                            try {
                                if(terrainArray[disasterX - 1][disasterY - 1] == Terrain.GRASS){
                                    terrainArray[disasterX - 1][disasterY - 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX + 1][disasterY + 1] == Terrain.GRASS){
                                    terrainArray[disasterX + 1][disasterY + 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX + 1][disasterY - 1] == Terrain.GRASS){
                                    terrainArray[disasterX + 1][disasterY - 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                            try {
                                if(terrainArray[disasterX - 1][disasterY + 1] == Terrain.GRASS){
                                    terrainArray[disasterX - 1][disasterY + 1] = Terrain.DESERT;
                                }
                            }catch (Exception e){
                            }

                        }
                    }
                }
            }
        }

        updateTile();

    }//Ends disasterStrike.

    public void incrementCo2(){//Adds co2 created this turn to total Co2.

        int turnCo2 = 0;

        for(int a = 0; a < tile.length; a++){
            for(int b = 0; b < tile.length; b++){
                if (transitArray[a][b] == TransitLine.ROAD){
                    turnCo2 = turnCo2 + TransitLine.ROAD.co2;
                }
                if(transitArray[a][b] == TransitLine.BIKEPATH){
                    turnCo2 = turnCo2 + TransitLine.BIKEPATH.co2;
                }
                if(transitArray[a][b] == TransitLine.GASTRAIN){
                    turnCo2 = turnCo2 + TransitLine.GASTRAIN.co2;
                }
                if(transitArray[a][b] == TransitLine.ELECTRICTRAIN){
                    turnCo2 = turnCo2 + TransitLine.ELECTRICTRAIN.co2;
                }
                if(transitArray[a][b] == TransitLine.ORBITALTRAIN){
                    turnCo2 = turnCo2 + TransitLine.ORBITALTRAIN.co2;
                }
            }
        }

        totalCo2 = totalCo2 + turnCo2;
    }

    public boolean winCondition(){

        /*
        * Tests to see if win condition is met by determining if their are connections
        * leading from home to all other city hubs. Connections can run through other
        * hubs before reaching final destination.
        */

        //Booleans keep track of immediate connections between hubs.
        boolean HomeToDowntown = false;
        boolean HomeToShopping = false;
        boolean HomeToFarm = false;
        boolean HomeToIndustrial = false;
        boolean DowntownToHome = false;
        boolean DowntownToShopping = false;
        boolean DowntownToFarm = false;
        boolean DowntownToIndustrial = false;
        boolean ShoppingToHome = false;
        boolean ShoppingToFarm = false;
        boolean ShoppingToIndustrial = false;
        boolean ShoppingToDowntown = false;
        boolean FarmToDowntown = false;
        boolean FarmToHome = false;
        boolean FarmToShopping = false;
        boolean FarmToIndustrial = false;
        boolean IndustrialToHome = false;
        boolean IndustrialToShopping = false;
        boolean IndustrialToFarm = false;
        boolean IndustrialToDowntown = false;

        //Checks to see if home is immediately connected to downtown
        if(isHubConnected("[HOOME]", "[ROADS]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToDowntown = true;
        }
        if(isHubConnected("[HOOME]", "[ELTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToDowntown = true;
        }
        if(isHubConnected("[HOOME]", "[GSTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToDowntown = true;
        }
        if(isHubConnected("[HOOME]", "[ORTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToDowntown = true;
        }
        if(isHubConnected("[HOOME]", "[BKPTH]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToDowntown = true;
        }

        //Checks to see if home is immediately connected to shopping.
        if(isHubConnected("[HOOME]", "[ROADS]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToShopping = true;
        }
        if(isHubConnected("[HOOME]", "[ELTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToShopping = true;
        }
        if(isHubConnected("[HOOME]", "[GSTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToShopping = true;
        }
        if(isHubConnected("[HOOME]", "[ORTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToShopping = true;
        }
        if(isHubConnected("[HOOME]", "[BKPTH]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToShopping = true;
        }

        //Checks ot see if home is immediately connected to farm.
        if(isHubConnected("[HOOME]", "[ROADS]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToFarm = true;
        }
        if(isHubConnected("[HOOME]", "[ELTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToFarm = true;
        }
        if(isHubConnected("[HOOME]", "[GSTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToFarm = true;
        }
        if(isHubConnected("[HOOME]", "[ORTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToFarm = true;
        }
        if(isHubConnected("[HOOME]", "[BKPTH]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToFarm = true;
        }

        //Checks to see if home is immediately connected to industry.
        if(isHubConnected("[HOOME]", "[ROADS]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToIndustrial = true;
        }
        if(isHubConnected("[HOOME]", "[ELTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToIndustrial = true;
        }
        if(isHubConnected("[HOOME]", "[GSTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToIndustrial = true;
        }
        if(isHubConnected("[HOOME]", "[ORTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToIndustrial = true;
        }
        if(isHubConnected("[HOOME]", "[BKPTH]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            HomeToIndustrial = true;
        }

        //Checks to see if downtown is immediately connected to home.
        if(isHubConnected("[DOWNT]", "[ROADS]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToHome = true;
        }
        if(isHubConnected("[DOWNT]", "[ELTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToHome = true;
        }
        if(isHubConnected("[DOWNT]", "[GSTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToHome = true;
        }
        if(isHubConnected("[DOWNT]", "[ORTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToHome = true;
        }
        if(isHubConnected("[DOWNT]", "[BKPTH]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToHome = true;
        }

        //Checks to see if downtown is immediately connected to shopping.
        if(isHubConnected("[DOWNT]", "[ROADS]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToShopping = true;
        }
        if(isHubConnected("[DOWNT]", "[ELTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToShopping = true;
        }
        if(isHubConnected("[DOWNT]", "[GSTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToShopping = true;
        }
        if(isHubConnected("[DOWNT]", "[ORTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToShopping = true;
        }
        if(isHubConnected("[DOWNT]", "[BKPTH]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToShopping = true;
        }

        //Checks to see if downtown is immediately connected to farm.
        if(isHubConnected("[DOWNT]", "[ROADS]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToFarm = true;
        }
        if(isHubConnected("[DOWNT]", "[ELTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToFarm = true;
        }
        if(isHubConnected("[DOWNT]", "[GSTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToFarm = true;
        }
        if(isHubConnected("[DOWNT]", "[ORTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToFarm = true;
        }
        if(isHubConnected("[DOWNT]", "[BKPTH]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToFarm = true;
        }

        //Checks to see if downtown is immediately connected to industry.
        if(isHubConnected("[DOWNT]", "[ROADS]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToIndustrial = true;
        }
        if(isHubConnected("[DOWNT]", "[ELTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToIndustrial = true;
        }
        if(isHubConnected("[DOWNT]", "[GSTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToIndustrial = true;
        }
        if(isHubConnected("[DOWNT]", "[ORTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToIndustrial = true;
        }
        if(isHubConnected("[DOWNT]", "[BKPTH]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            DowntownToIndustrial = true;
        }

        //Checks to see if shopping is immediately connected to home.
        if(isHubConnected("[SHOPP]", "[ROADS]", "[HOME]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToHome = true;
        }
        if(isHubConnected("[SHOPP]", "[ELTRN]", "[HOME]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToHome = true;
        }
        if(isHubConnected("[SHOPP]", "[GSTRN]", "[HOME]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToHome = true;
        }
        if(isHubConnected("[SHOPP]", "[ORTRN]", "[HOME]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToHome = true;
        }
        if(isHubConnected("[SHOPP]", "[BKPTH]", "[HOME]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToHome = true;
        }

        //Checks to see if shopping is immediately connected to farm.
        if(isHubConnected("[SHOPP]", "[ROADS]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToFarm = true;
        }
        if(isHubConnected("[SHOPP]", "[ELTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToFarm = true;
        }
        if(isHubConnected("[SHOPP]", "[GSTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToFarm = true;
        }
        if(isHubConnected("[SHOPP]", "[ORTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToFarm = true;
        }
        if(isHubConnected("[SHOPP]", "[BKPTH]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToFarm = true;
        }

        //Checks to see if Shopping is connected to industry.
        if(isHubConnected("[SHOPP]", "[ROADS]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected("[SHOPP]", "[ELTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected("[SHOPP]", "[GSTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected("[SHOPP]", "[ORTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToIndustrial = true;
        }
        if(isHubConnected("[SHOPP]", "[BKPTH]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToIndustrial = true;
        }

        //Checks to see if shopping is connected to Downtown.
        if(isHubConnected("[SHOPP]", "[ROADS]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToDowntown = true;
        }
        if(isHubConnected("[SHOPP]", "[ELTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToDowntown = true;
        }
        if(isHubConnected("[SHOPP]", "[GSTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToDowntown = true;
        }
        if(isHubConnected("[SHOPP]", "[ORTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToDowntown = true;
        }
        if(isHubConnected("[SHOPP]", "[BKPTH]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            ShoppingToDowntown = true;
        }

        //Checks ot see if farm is connected to downtown.
        if(isHubConnected("[FAARM]", "[ROADS]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToDowntown = true;
        }
        if(isHubConnected("[FAARM]", "[ELTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToDowntown = true;
        }
        if(isHubConnected("[FAARM]", "[GSTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToDowntown = true;
        }
        if(isHubConnected("[FAARM]", "[ORTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToDowntown = true;
        }
        if(isHubConnected("[FAARM]", "[BKPTH]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToDowntown = true;
        }

        //Checks to see if farm is connected to home.
        if(isHubConnected("[FAARM]", "[ROADS]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToHome = true;
        }
        if(isHubConnected("[FAARM]", "[ELTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToHome = true;
        }
        if(isHubConnected("[FAARM]", "[GSTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToHome = true;
        }
        if(isHubConnected("[FAARM]", "[ORTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToHome = true;
        }
        if(isHubConnected("[FAARM]", "[BKPTH]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToHome = true;
        }

        //Checks to see if farm is connected to shopping.
        if(isHubConnected("[FAARM]", "[ROADS]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToShopping = true;
        }
        if(isHubConnected("[FAARM]", "[ELTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToShopping = true;
        }
        if(isHubConnected("[FAARM]", "[GSTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToShopping = true;
        }
        if(isHubConnected("[FAARM]", "[ORTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToShopping = true;
        }
        if(isHubConnected("[FAARM]", "[BKPTH]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToShopping = true;
        }

        //Checks to see if farm is connected to industry.
        if(isHubConnected("[FAARM]", "[ROADS]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToIndustrial = true;
        }
        if(isHubConnected("[FAARM]", "[ELTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToIndustrial = true;
        }
        if(isHubConnected("[FAARM]", "[GSTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToIndustrial = true;
        }
        if(isHubConnected("[FAARM]", "[ORTRN]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToIndustrial = true;
        }
        if(isHubConnected("[FAARM]", "[BKPTH]", "[INDUS]", "[NULL]", "[NULL]", "[NULL]")){
            FarmToIndustrial = true;
        }

        //Checks ot see if industry is connected to home.
        if(isHubConnected("[INDUS]", "[ROADS]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToHome = true;
        }
        if(isHubConnected("[INDUS]", "[ELTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToHome = true;
        }
        if(isHubConnected("[INDUS]", "[GSTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToHome = true;
        }
        if(isHubConnected("[INDUS]", "[ORTRN]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToHome = true;
        }
        if(isHubConnected("[INDUS]", "[BKPTH]", "[HOOME]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToHome = true;
        }

        //Checks to see if industry is connected to shopping.
        if(isHubConnected("[INDUS]", "[ROADS]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToShopping = true;
        }
        if(isHubConnected("[INDUS]", "[ELTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToShopping = true;
        }
        if(isHubConnected("[INDUS]", "[GSTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToShopping = true;
        }
        if(isHubConnected("[INDUS]", "[ORTRN]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToShopping = true;
        }
        if(isHubConnected("[INDUS]", "[BKPTH]", "[SHOPP]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToShopping = true;
        }

        //Checks to see if industry is connected to farm.
        if(isHubConnected("[INDUS]", "[ROADS]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToFarm = true;
        }
        if(isHubConnected("[INDUS]", "[ELTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToFarm = true;
        }
        if(isHubConnected("[INDUS]", "[GSTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToFarm = true;
        }
        if(isHubConnected("[INDUS]", "[ORTRN]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToFarm = true;
        }
        if(isHubConnected("[INDUS]", "[BKPTH]", "[FAARM]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToFarm = true;
        }

        //Checks to see if industry is connected to downtown.
        if(isHubConnected("[INDUS]", "[ROADS]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToDowntown = true;
        }
        if(isHubConnected("[INDUS]", "[ELTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToDowntown = true;
        }
        if(isHubConnected("[INDUS]", "[GSTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToDowntown = true;
        }
        if(isHubConnected("[INDUS]", "[ORTRN]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToDowntown = true;
        }
        if(isHubConnected("[INDUS]", "[BKPTH]", "[DOWNT]", "[NULL]", "[NULL]", "[NULL]")){
            IndustrialToDowntown = true;
        }

        boolean Connect1 = false;//Home connects to downtown somehow
        boolean Connect2 = false;//Home connects to Farm somehow
        boolean Connect3 = false;//Home connects to Shopping somehow
        boolean Connect4 = false;//Home connects to Industrial somehow

        //Follows logical pathways to determine if home is connected to each of the other city hubs.
        //Uses booleans for immediate connections to build to larger boolen connections above.
        if(HomeToDowntown){
            Connect1 = true;

            if(DowntownToFarm){
                Connect2 = true;
                if(FarmToShopping){
                    Connect3 = true;
                    if(ShoppingToIndustrial){
                        Connect4 = true;
                    }
                }
                if(FarmToIndustrial){
                    Connect4 = true;
                    if(IndustrialToShopping){
                        Connect3 = true;
                    }
                }
            }
            if(DowntownToShopping){
                Connect3 = true;
                if(ShoppingToFarm){
                    Connect2 = true;
                    if(FarmToIndustrial){
                        Connect4 = true;
                    }
                }
                if(ShoppingToIndustrial){
                    Connect4 = true;
                    if(IndustrialToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(DowntownToIndustrial){
                Connect4 = true;
                if(IndustrialToFarm){
                    Connect2 = true;
                    if(FarmToShopping){
                        Connect3 = true;
                    }
                }
                if(IndustrialToShopping){
                    Connect3 = true;
                    if(ShoppingToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(HomeToFarm){
            Connect2 = true;

            if(FarmToDowntown){
                Connect1 = true;
                if(DowntownToShopping){
                    Connect3 = true;
                    if(ShoppingToIndustrial){
                        Connect4 = true;
                    }
                }
                if(DowntownToIndustrial){
                    Connect4 = true;
                    if(IndustrialToShopping){
                        Connect3 = true;
                    }

                }
            }
            if(FarmToShopping){
                Connect3 = true;
                if(ShoppingToDowntown){
                    Connect1 = true;
                    if(DowntownToIndustrial){
                        Connect4 = true;
                    }
                }
                if(ShoppingToIndustrial){
                    Connect4 = true;
                    if(IndustrialToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(FarmToIndustrial){
                Connect4 = true;
                if(IndustrialToDowntown){
                    Connect1 = true;
                    if(DowntownToShopping){
                        Connect3 = true;
                    }
                }
                if(IndustrialToShopping){
                    Connect3 = true;
                    if(ShoppingToDowntown){
                        Connect1 = true;
                    }
                }
            }
        }

        if(HomeToShopping){
            Connect3 = true;

            if(ShoppingToFarm){
                Connect2 = true;
                if(FarmToDowntown){
                    Connect1 = true;
                    if(DowntownToIndustrial){
                        Connect4 = true;
                    }
                }
                if(FarmToIndustrial){
                    Connect4 = true;
                    if(IndustrialToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(ShoppingToIndustrial){
                Connect4 = true;
                if(IndustrialToFarm){
                    Connect2 = true;
                    if(FarmToDowntown){
                        Connect1 = true;
                    }
                }
                if(IndustrialToDowntown){
                    Connect1 = true;
                    if(DowntownToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(ShoppingToDowntown){
                Connect1 = true;
                if(DowntownToFarm){
                    Connect2 = true;
                    if(FarmToIndustrial){
                        Connect4 = true;
                    }
                }
                if(DowntownToIndustrial){
                    Connect4 = true;
                    if(IndustrialToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(HomeToIndustrial){
            Connect4 = true;

            if(IndustrialToFarm){
                Connect2 = true;
                if(FarmToDowntown){
                    Connect1 = true;
                    if(DowntownToShopping){
                        Connect3 = true;
                    }
                }
                if(FarmToShopping){
                    Connect3 = true;
                    if(ShoppingToDowntown){
                        Connect1 = true;
                    }
                }
            }
            if(IndustrialToDowntown){
                Connect1 = true;
                if(DowntownToFarm){
                    Connect2 = true;
                    if(FarmToShopping){
                        Connect3 = true;
                    }
                }
                if(DowntownToShopping){
                    Connect3 = true;
                    if(ShoppingToFarm){
                        Connect2 = true;
                    }
                }
            }
            if(IndustrialToShopping){
                Connect3 = true;
                if(ShoppingToFarm){
                    Connect2 = true;
                    if(FarmToDowntown){
                        Connect1 = true;
                    }
                }
                if(ShoppingToDowntown){
                    Connect1 = true;
                    if(DowntownToFarm){
                        Connect2 = true;
                    }
                }
            }
        }

        if(Connect1 && Connect2 && Connect3 && Connect4){//if all hubs are connected.
            if(turnCounter == 16){//for win condition to be met turncounter must be at 16.
                return true;
            }
        }
        return false;
    }

    public String tileReturn(int x, int y){//returns tile at specified coordinate. Used for gui representation.
        return tile[x][y];
    }

    public int tileLength(){//returns length of map. Used for gui representation.
        return tile.length;
    }

}//ends map
