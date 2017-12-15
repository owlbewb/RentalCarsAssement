package Responses;

/**
 * Class used to model the response for a Order By Score Request
 */

public class OrderByScoreResponse {

    private String name;
    private double score;
    private double rating;
    private double sum;

    /**
     * Models the response format of the Order by Score request
     *
     * @param name   the brand and model of the vehicle
     * @param score  the score gained through what components are available on the vehicle
     * @param rating the supplier review rating
     * @param sum    the sum of score and rating
     */
    public OrderByScoreResponse(String name, double score, double rating, double sum) {
        this.name = name;
        this.score = score;
        this.rating = rating;
        this.sum = sum;
    }
}
