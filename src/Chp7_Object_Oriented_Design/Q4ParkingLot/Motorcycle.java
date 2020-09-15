package Chp7_Object_Oriented_Design.Q4ParkingLot;

public class Motorcycle extends Vehicle {
    public Motorcycle(){
        spotsNeeded = 1;
        size = VehicleSize.Motorcycle;
    }

    public boolean canFitInSpot(ParkingSpot spot){
        return true;
    }

    public void print(){
        System.out.print("M");
    }
}
