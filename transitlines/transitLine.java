package transitlines;
//
public enum TransitLine {

    NULLTRANSIT(0, 0, 0, 0), ROAD(5, 8, 5, 2), BIKEPATH(2, 10, 5, 2), GASTRAIN(7, 12, 3, 3), ELECTRICTRAIN(10, 13, 2, 3), ORBITALTRAIN(12, 15, 1, 3);

    public int cost;//cost to builid one segment.
    public int value;//income produced per turn.
    public int co2;//Co2 produced per turn.
    int bridgecost;//Additional cost to build transitline over river.


    TransitLine(int Cost, int Value, int Co2, int bridgeCost){//Constructor for TransitLine.
        cost = Cost;
        value = Value;
        co2 = Co2;
        bridgecost = bridgeCost;

    }

    public int returnBridgeCost(){//returns cost of bridge. Used in maps.
        return bridgecost;
    }


}
