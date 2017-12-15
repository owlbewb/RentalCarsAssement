package API;

import FileReaderWriting.ReadIn;
import Responses.OrderByPriceResponse;
import Responses.OrderByRatingResponse;
import Responses.OrderByScoreResponse;
import Responses.OrderBySpecResponse;
import Vehicles.Vehicle;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static Main.Main.FILEPATH;

/**
 * Class used for REST API extension. Returns JSON object equivalents of the
 * console line prints from Queries.java class.
 * <p>
 * JSON responses are modeled individually for each request. These responses can be
 * found in the Responses package.
 */


@Path("/api/query")
public class QueriesAPI {

    static List<Vehicle> vehicleList;


    /**
     * Returns a JSON object representing
     * a given list of vehicles ordered in ascending order by price
     *
     * @param list the list of vehicles to be sorted
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String orderByPrice(List<Vehicle> list) {
        ReadIn readIn = new ReadIn();
        readIn.readInJson(FILEPATH);
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();
        Collections.sort(vehicleList, Vehicle.priceComparator);
        List<OrderByPriceResponse> response = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            response.add(new OrderByPriceResponse(v.getName(), v.getPrice()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        return json;
    }

    /**
     * Returns a JSON obejct with all the specifications of vehicles in a given list
     *
     * @param list a list of vehicles.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String orderBySpecification(List<Vehicle> list) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) list;
        List<OrderBySpecResponse> response = new ArrayList<>();

        for (Vehicle v : vehicleList) {
            response.add(new OrderBySpecResponse(
                    v.getName(),
                    v.getSipp(),
                    v.getCarType(),
                    v.getDoorType(),
                    v.getTransmission(),
                    v.getExtras()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        return json;
    }

    /**
     * Returns a JSON object with vehicles ordered by supplier rating and
     * grouped by car type
     *
     * @param list
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String orderBySupplierAndType(List<Vehicle> list) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) list;
        ArrayList<Vehicle> response = new ArrayList<>();
        ArrayList<OrderByRatingResponse> jsonResponse = new ArrayList<>();

        // For every car type
        for (Vehicle.CarType type : Vehicle.CarType.values()) {
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while (itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                int lastElement = response.size() - 1;
                // if this is the first car type to be compared, automatically add it to the list
                if (lastElement < 0) {
                    response.add(compareTo);
                } else if (response.get(lastElement).getCarType() != compareTo.getCarType() &&
                        compareTo.getCarType() == type) {
                    response.add(compareTo);
                }
                // if the vehicle is of the same supplier, do not compare
                else if (response.get(lastElement).getSupplier().equals(compareTo.getSupplier())) {
                    // Do nothing
                }
                // if car types are the same but supppliers are different
                else if (response.get(lastElement).getCarType() == compareTo.getCarType()) {
                    // find the difference between ratings
                    double ratingDifference = response.get(lastElement).getRating() - compareTo.getRating();
                    // a negative answer means thecar being compared too has a higher rating.
                    if (ratingDifference < 0) {
                        response.set(lastElement, compareTo);
                    }
                }
            }
        }

        for (Vehicle v : response) {
            jsonResponse.add(new OrderByRatingResponse(v.getName(), v.getCarType(), v.getSupplier(), v.getRating()));
        }

        Gson gson = new Gson();
        String json = gson.toJson(jsonResponse);
        return json;
    }

    /**
     * Returns a JSON Object with Vehicles ordered by score.
     *
     * @param vehicleList A list of Vehicles.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String orderByScore(List<Vehicle> vehicleList) {

        Collections.sort(vehicleList, Vehicle.scoreComparator);
        ArrayList<OrderByScoreResponse> response = new ArrayList<>();

        for (Vehicle v : vehicleList) {
            response.add(new OrderByScoreResponse(v.getName(), v.getScore(), v.getRating(), v.getSumOfScores()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        return json;
    }

}
