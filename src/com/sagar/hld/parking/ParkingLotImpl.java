package com.sagar.hld.parking;

import java.util.SortedSet;

/**
 * Created by sagarsingh on 2020-07-17
 */
public class ParkingLotImpl implements ParkingLot {
    SortedSet<ParkingLevel> parkingLevels;
    private static ParkingLot Insatance;

    private ParkingLotImpl(SortedSet<ParkingLevel> parkingLevels) {
        this.parkingLevels = parkingLevels;
    }

    @Override
    public boolean isParkingPossible(Vehicle vehicle) {
        for (ParkingLevel parkingLevel : parkingLevels) {
            if (parkingLevel.isParkingAvailable(vehicle.getVehicleType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean parkVehichle(Vehicle vehicle) {
        for (ParkingLevel parkingLevel : parkingLevels) {
            if (parkingLevel.isParkingAvailable(vehicle.getVehicleType())) {
                return parkingLevel.park(vehicle);
            }
        }
        return false;
    }

    @Override
    public void removeVehicle(ParkingSpot parkingSpot) {

    }

    @Override
    public void printSummarry() {

    }
}
