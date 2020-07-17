package com.sagar.hld.parking;

/**
 * Created by sagarsingh on 2020-07-17
 */
public interface ParkingLot {
    boolean isParkingPossible(Vehicle vehicle);

    boolean parkVehichle(Vehicle vehicle);

    void removeVehicle(ParkingSpot parkingSpot);

    void printSummarry();
}
