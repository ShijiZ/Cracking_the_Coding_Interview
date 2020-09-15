package Chp7_Object_Oriented_Design.Q4ParkingLot;

public class Car extends Vehicle {
    public Car(){
        spotsNeeded = 1;
        size = VehicleSize.Compact;
    }

    public boolean canFitInSpot(ParkingSpot spot){
        return spot.getSize() == VehicleSize.Large || spot.getSize() == VehicleSize.Compact;
    }

    public void print(){
        System.out.print("C");
    }
}
