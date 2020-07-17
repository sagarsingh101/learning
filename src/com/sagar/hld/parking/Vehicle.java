package com.sagar.hld.parking;

/**
 * Created by sagarsingh on 2020-07-17
 */
public class Vehicle {
    private final VehicleType vehicleType;
    private final int id;

    public Vehicle(VehicleType vehicleType, int id) {
        this.vehicleType = vehicleType;
        this.id = id;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public int getId() {
        return id;
    }
}
