package com.sagar.hld.parking;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by sagarsingh on 2020-07-17
 */
public class ParkingLevel {
    private final PriorityQueue<SmallParkingSpot> smallParkingSpots;
    private final PriorityQueue<MediumParkingSpot> mediumParkingSpots;
    Set<ParkingSpot> reservedSpots;
    private final int level;

    public ParkingLevel(List<ParkingSpot> parkingSpots, int level) {
        this.level = level;
        this.smallParkingSpots = new PriorityQueue<>();
        this.mediumParkingSpots = new PriorityQueue<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            switch (parkingSpot.getVehicleType()) {
                case SMALL:
                    smallParkingSpots.add((SmallParkingSpot) parkingSpot);
                    break;
                case MEDIUM:
                    mediumParkingSpots.add((MediumParkingSpot) parkingSpot);
                    break;
            }

        }
        reservedSpots = new HashSet<>();
    }

    public boolean isParkingAvailable(VehicleType vehicleType) {
        switch (vehicleType) {
            case SMALL:
                return !smallParkingSpots.isEmpty();
            case MEDIUM:
                return !mediumParkingSpots.isEmpty();
        }
        return false;
    }

    public boolean park(Vehicle vehicle) {
        switch (vehicle.getVehicleType()) {
            case SMALL:
                if (!smallParkingSpots.isEmpty()) {
                    return reserveSpot(smallParkingSpots.poll(), vehicle);
                }
                break;
            case MEDIUM:
                if (!mediumParkingSpots.isEmpty()) {
                    return reserveSpot(mediumParkingSpots.poll(), vehicle);
                }
        }
        throw new RuntimeException("Parking unavalaible");
    }

    private boolean reserveSpot(ParkingSpot parkingSpot, Vehicle vehicle) {
        return parkingSpot.reserve(vehicle);
    }

    public boolean removeVehicle(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle();
        switch (parkingSpot.getVehicleType()) {
            case SMALL:
                smallParkingSpots.add((SmallParkingSpot) parkingSpot);
                return reservedSpots.remove(parkingSpot);
            case MEDIUM:
                mediumParkingSpots.add((MediumParkingSpot) parkingSpot);
                return reservedSpots.remove(parkingSpot);
        }
        return false;
    }


}
