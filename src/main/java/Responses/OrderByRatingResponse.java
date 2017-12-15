package Responses;

import Vehicles.Vehicle;

/**
 * Class used to model the response to Order By Rating request
 */

public class OrderByRatingResponse {

    private String name;
    private Vehicle.CarType carType;
    private String supplier;
    private double rating;

    /**
     * Models the response format of the Order by Rating request
     *
     * @param name     the brand or model of the vehicle
     * @param carType  the type of car the vehicle is
     * @param supplier the supplier of the vehicle
     * @param rating   the review ratings of the supplier
     */
    public OrderByRatingResponse(String name, Vehicle.CarType carType, String supplier, double rating) {
        this.name = name;
        this.carType = carType;
        this.supplier = supplier;
        this.rating = rating;
    }
}
