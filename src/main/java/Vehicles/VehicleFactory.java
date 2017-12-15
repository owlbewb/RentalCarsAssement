package Vehicles;

/**
 * used to create Vehicle objects
 */

public interface VehicleFactory {

    /**
     * Returns a new Vehicle object
     *
     * @param price    the price of renting the vehicle
     * @param name     the brand and model
     * @param rating   the supplier review score
     * @param sipp     classifier shorthand
     * @param supplier the supplier of the service
     * @return a new Vehicle Object
     */
    static Vehicle getVehicle(double price, String name, double rating, String sipp, String supplier) {
        return new Vehicle(price, name, rating, sipp, supplier);
    }

}
