package com.RentalCarsApplication;

import Responses.OrderByPriceResponse;
import Responses.OrderByRatingResponse;
import Responses.OrderByScoreResponse;
import Responses.OrderBySpecResponse;
import FileReaderWriting.ReadIn;
import Vehicles.Vehicle;
import com.google.gson.Gson;
import org.junit.Test;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Path("/api/test")
public class API_test {

    static List<Vehicle> vehicleList;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByPrice(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();
        Collections.sort(vehicleList, Vehicle.priceComparator);
        Gson gson = new Gson();
        String json = gson.toJson(vehicleList);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByPriceCorrectFields(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();
        Collections.sort(vehicleList, Vehicle.priceComparator);
        List<OrderByPriceResponse> response = new ArrayList<>();
        for(Vehicle v: vehicleList){
            response.add(new OrderByPriceResponse(v.getName(),v.getPrice()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderBySpecAccess(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();
        Collections.sort(vehicleList, Vehicle.priceComparator);
        Gson gson = new Gson();
        String json = gson.toJson(vehicleList);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderBySpecCorrectFields(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();
        Collections.sort(vehicleList, Vehicle.priceComparator);
        List<OrderBySpecResponse> response = new ArrayList<>();
        for(Vehicle v: vehicleList){
            response.add(new OrderBySpecResponse(v.getName(),
                    v.getSipp(),
                    v.getCarType(),
                    v.getDoorType(),
                    v.getTransmission(),
                    v.getExtras()));
        }
        Gson gson = new Gson();
        String json = gson.toJson(response);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByRatingAccess(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()) {
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

        Gson gson = new Gson();
        String json = gson.toJson(response);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByRatingCorrectFields(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();
        ArrayList<OrderByRatingResponse> jsonResponse = new ArrayList<>();


        // Method Logic
        for(Vehicle.CarType type: Vehicle.CarType.values()) {
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

        for(Vehicle v: response){
            jsonResponse.add(new OrderByRatingResponse(v.getName(),v.getCarType(),v.getSupplier(),v.getRating()));
        }

        Gson gson = new Gson();
        String json = gson.toJson(jsonResponse);
        System.out.println(json);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByScoreAccess(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Collections.sort(vehicleList, Vehicle.scoreComparator);

        Gson gson = new Gson();
        String json = gson.toJson(vehicleList);
        System.out.println(json);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Test
    public void testOrderByScoreCorrectField(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Collections.sort(vehicleList, Vehicle.scoreComparator);
        ArrayList<OrderByScoreResponse> response = new ArrayList<>();

        for(Vehicle v : vehicleList){
            response.add(new OrderByScoreResponse(v.getName(), v.getScore(), v.getRating(), v.getSumOfScores()));
        }

        Gson gson = new Gson();
        String json = gson.toJson(response);
        System.out.println(json);
    }



}
