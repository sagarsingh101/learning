package com.sagar.hld.parking;

import java.util.Comparator;
import java.util.Objects;

/**
 * Created by sagarsingh on 2020-07-17
 */
public abstract class ParkingSpot implements Comparator<ParkingSpot> {
    private final VehicleType vehicleType;
    private final int id;
    private boolean isFree;
    private Vehicle vehicle;

    public ParkingSpot(VehicleType vehicleType, int position) {
        this.vehicleType = vehicleType;
        this.id = position;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean reserve(Vehicle vehicle) {
        if (isFree()) {
            this.vehicle = vehicle;
            this.isFree = false;
            return true;
        }
        return false;
    }

    public boolean removeVehicle() {
        if (!isFree()) {
            this.vehicle = null;
            this.isFree = true;
            return true;
        }
        return false;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int compare(ParkingSpot o1, ParkingSpot o2) {
        return o2.getId() - o1.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
