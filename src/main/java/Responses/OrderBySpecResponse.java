package Responses;

import Vehicles.Vehicle;

/**
 * Class used to model a response for the Order By Specification Request
 */

public class OrderBySpecResponse {

    private String name;
    private String sipp;
    private Vehicle.CarType carType;
    private Vehicle.DoorType doorType;
    private Vehicle.Transmission transmission;
    private String fuel = "PETROL";
    private Vehicle.Extras extras;

    /**
     * Models the response format of the Order by Specification request
     *
     * @param name         the brand/model of the vehicle
     * @param sipp         classifer shorthand
     * @param carType      The type of car
     * @param doorType     the type of door
     * @param transmission the type of transmission
     * @param extras       If there are any extras
     */
    public OrderBySpecResponse(String name, String sipp, Vehicle.CarType carType, Vehicle.DoorType doorType, Vehicle.Transmission transmission, Vehicle.Extras extras) {
        this.name = name;
        this.sipp = sipp;
        this.carType = carType;
        this.doorType = doorType;
        this.transmission = transmission;
        this.extras = extras;
    }
}
