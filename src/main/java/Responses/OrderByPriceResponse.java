package Responses;

/**
 * Used to model a response for the OrderByPrice GET Request.
 */

public class OrderByPriceResponse {

    private double price;
    private String name;

    /**
     * Models the response format of the Order by price request
     *
     * @param name  the model and brand of the vehicle
     * @param price the price to rent
     */
    public OrderByPriceResponse(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
