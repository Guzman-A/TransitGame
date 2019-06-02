
package disasters;

public enum Disasters{

    FLOODING(1,true), HURRICANES(2,false), DESERTIFICATION(3,false), FIRES(3,false), TORNADOES(1,false), NULLDISASTER(0,false);

    public int size;
    public boolean impede;//Not used in current setup. An option for future implementations.
    public int threshold;

    Disasters(int Size, boolean Impede){//constructor for Disasters.
        size = Size;
        impede = Impede;
    }

    public void setThreshold(int mapSize){//Sets threshold for determining whether disaster runs based on map size.
        threshold = mapSize * mapSize;
    }

    boolean isDisaster(int co2){//Determines whether disaster is run based on Co2 and random number generator.

        if (co2 >= threshold){
            double random = (double)(Math.random() * 100);
            double level = (((co2/100)*random));
            if(level > threshold){
                return true;
            }
            return false;

        }

        if(co2 < threshold){
            double random = (double)(Math.random() * 100);
            double level = ((co2/20)*random);
            if(level > threshold){
                return true;
            }
            return false;
        }
      return false;
    }


    int getSize(int co2){//Determines size(range) of disaster based on Co2 and random number generator.

        int random = (int)(Math.random() * 10);
        int num = (co2 * (random * size)/100);
        System.out.println("Size is " + num);
        return num;

    }

    public int runDisaster(int co2){//accessed from maps; checks if disaster runs and returns size, if not returns 0.
        if(isDisaster(co2)){
            return size + getSize(co2);
        }
        else return 0;
    }
}





























































    /*

    Disasters:
    Flooding (exacerbated by rivers)
    Earthquakes (occur with fault lines (fracking))
    Hurricanes (occur with coastal area)
    Desertification (exacerbated by desert areas)
    Fires (exacerbated by forest areas)
    Tornadoes
    //Refugee Influx

    */


/*
    public int getRange([x],[y],terrainArray,int Co2){
//starting location—-[x][y],terrain type,
//distribution pattern——-size of speckled circle, determined by size of Co2
//Triggers getImpede method

//Flooding starts at river.(Distribution - 2, Speckled - NO)
//Earthquake starts at entire map.(Distribution - 8, Speckled - yes)
//Hurricane starts at ocean.(Distribution - 10, Speckled - yes)
//Dessertification starts at edge that’s not ocean.(Distribution - 10, Speckled - yes)
//Fires starts at foliage (Distribution - 3, Speckled - yes)
//tornadoes starts at terrain grass. (Distribution - 5, Speckled - NO)


//speckled/dispersion method
//Flooding 1-3; co2 determines lvl
//earthquakes rndom ; dependent on co2
//hurricane range from sea; co2 determines inland range
//dessertification- co2 determines inland speed
//fires co2 determines frequency
//tornado co2 determines damage or movement range from start position

//triggers impede method


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
flooder(x,y,co2)

int level;
int co2 = intensity;


//location/ switcher//////////////////////////////
if(terrain[x][y] == terrain.RIVER && co2 == true){

}

//////////////////////////////////////////////////
intensity(){

    if(intensity >= 75){
        level = 3;
    }

    if(intensity <= 50 > 25){
        level = 2;
    }

    if(intensity <= 25){
        level = 1;
    }
}
////////////////////////////////////////////////////

distribution(){

switch (level){
  case 1:
    for(int i; i < terrain.length; i++){
      for(int a; a < terrain.length; a++){
        if(terrain[i][a] == RIVER){
          i = i + 1;
          terrain[i][a];

          i = i - 1;
          terrain[i][a];

          a = a + 1;
          terrain[i][a];

          a = a - 1;
          terrain[i][a];
      }
    }
  }

  case 2:
    for(int i; i < terrain.length; i++){
      for(int a; a < terrain.length; a++){
        if(terrain[i][a] == RIVER){
          i = i + 2;
          terrain[i][a];

          i = i - 2;
          terrain[i][a];

          a = a + 2;
          terrain[i][a];

          a = a - 2;
          terrain[i][a];
      }
    }
  }

  case 3:
    for(int i; i < terrain.length; i++){
      for(int a; a < terrain.length; a++){
        if(terrain[i][a] == RIVER){
          i = i + 3;
          terrain[i][a];

          i = i - 3;
          terrain[i][a];

          a = a + 3;
          terrain[i][a];

          a = a - 3;
          terrain[i][a];
      }
    }
  }

  default:
    System.out.println("error in Disasters.java");
}





///trigger for impede
public impede(boolean){
  if(true){
    terrain[i][a] = terrain.ROCK;
  }
  else{
    System.out.println("error in Disasters.java");
  }
}


















///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


earthquaker(x,y,co2)

//location/ switcher//////////////////////////////
terrain[x][y];
transitArray[x][y];


////////////
randomizer()

///////////////////////////////////////

///trigger for impede methods
impede()

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


hurricaner()

//location/ switcher//////////////////////////////
terrain[x][y];
transitArray[x][y];

*
*
*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



desertifier()

//location/ switcher//////////////////////////////
terrain[x][y];
transitArray[x][y];

*
*
*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



Smoker()

//location/ switcher//////////////////////////////
terrain[x][y];
transitArray[x][y];

*
*
*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


tornadoer()

//location/ switcher//////////////////////////////
terrain[x][y];
transitArray[x][y];

*
*
*
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////























    public int getImpede(){
//does it change terrain type?
//does it revert back to original terrain type?
//turns to rock or removes structure.




//range = distance from originating block * co2
//damage = subtract from health
//impede = does it block; and morph block

      int FLOODING(Co2) {
        int random;


        range = Co2 * random;
        if(random = 1) return range = +1;
        if(2)
        if(3)

        damage = Co2 * random;

        int EARTHQUAKES() {
          int random;


          range =

          damage = co2

          int DESERTIFICATION() {
            int random;


            range =

            damage = co2

            int FIRES() {
              int random;


              range =

              damage = co2

              int TORNADOES() {
                int random;
                range =
                damage = co2
      }







    }

}

/*
Flooding
int range = 2;
int damage = 10;
boolean impede;//yes but changes terrain type to river


Earthquakes
int range = 8;
int damage = 8
boolean impede//yes but changes terrain type to rock



Desertification
int range 10;
int damage 0;
boolean impede;//no; reduces bank -2 per turn per square;
*/
