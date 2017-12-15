package com.RentalCarsApplication;

import FileReaderWriting.ReadIn;
import Logic.Queries;
import Vehicles.Vehicle;
import Vehicles.VehicleFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.validator.ValidateWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Queries_test {

    @Test
    public void testOrderByPricePrint(){

        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle costly = VehicleFactory.getVehicle(100, "Expensive", 5, "XXXX", "Cars");
        Vehicle economic = VehicleFactory.getVehicle(50, "Expensive", 5, "XXXX", "Cars");
        vehicleList.add(costly); vehicleList.add(economic);

        Queries.runOrderByPrice(vehicleList);
    }

    @Test
    public void testRunOrderBySpecification(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Queries.runOrderBySpecification(vehicleList);
    }

    @Test
    public void testRunOSATIterator(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while(itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                Assert.assertNotNull(compareTo);
            }
        }

    }

    @Test
    public void testRunOSATWhileLoop(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while(itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                int lastElement = response.size() - 1;
                // if this is the first car type to be compared, automatically add it to the list
                if(lastElement <= 0){
                    response.add(compareTo);
                }
                // if the vehicle is of the same supplier, do not compare
                else if(response.get(lastElement).getSupplier().equals(compareTo.getSupplier())){
                    break;
                }
                // if car types are the same but supppliers are different
                else if(response.get(lastElement).getCarType() == compareTo.getCarType()){
                    break;
                }
            }
        }

    }

    @Test
    public void testRunOSATComparison(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while(itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                int lastElement = response.size() - 1;
                // if this is the first car type to be compared, automatically add it to the list
                if(lastElement <= 0){
                    response.add(compareTo);
                }
                // if the vehicle is of the same supplier, do not compare
                else if(response.get(lastElement).getSupplier().equals(compareTo.getSupplier())){
                    break;
                }
                // if car types are the same but supppliers are different
                else if(response.get(lastElement).getCarType() == compareTo.getCarType()){
                    // find the difference between ratings
                    double ratingDifference = response.get(lastElement).getRating() - compareTo.getRating();
                    // a negative answer means thecar being compared too has a higher rating.
                    boolean testDifference = false;
                    if(ratingDifference < 0){
                        testDifference = true;
                    }
                    Assert.assertEquals(true, testDifference);
                }
            }
        }

    }

    @Test
    public void testRunOSATArraySize(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while(itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                int lastElement = response.size() - 1;
                // if this is the first car type to be compared, automatically add it to the list
                if(lastElement < 0){
                    response.add(compareTo);
                }
                else if (response.get(lastElement).getCarType() != compareTo.getCarType() &&
                        compareTo.getCarType() == type){
                        response.add(compareTo);
                }
                // if the vehicle is of the same supplier, do not compare
                else if(response.get(lastElement).getSupplier().equals(compareTo.getSupplier())){
                   // Do nothing
                }
                // if car types are the same but supppliers are different
                else if(response.get(lastElement).getCarType() == compareTo.getCarType()){
                    // find the difference between ratings
                    double ratingDifference = response.get(lastElement).getRating() - compareTo.getRating();
                    // a negative answer means thecar being compared too has a higher rating.
                    if(ratingDifference < 0){
                        response.set(lastElement, compareTo);
                    }
                }
            }
        }
        Assert.assertEquals(7, response.size());
    }



    @Test
    public void testRunOSATEveryCarType(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        ArrayList<Vehicle> response = new ArrayList<>();

        // For every car type
        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = vehicleList.iterator();
            // compare it to every other vehicle
            while(itr.hasNext()) {
                Vehicle compareTo = (Vehicle) itr.next();
                int lastElement = response.size() - 1;
                // if this is the first car type to be compared, automatically add it to the list
                if(lastElement < 0){
                    response.add(compareTo);
                }
                else if (response.get(lastElement).getCarType() != compareTo.getCarType() &&
                        compareTo.getCarType() == type){
                    response.add(compareTo);
                }
                // if the vehicle is of the same supplier, do not compare
                else if(response.get(lastElement).getSupplier().equals(compareTo.getSupplier())){
                    // Do nothing
                }
                // if car types are the same but supppliers are different
                else if(response.get(lastElement).getCarType() == compareTo.getCarType()){
                    // find the difference between ratings
                    double ratingDifference = response.get(lastElement).getRating() - compareTo.getRating();
                    // a negative answer means thecar being compared too has a higher rating.
                    if(ratingDifference < 0){
                        response.set(lastElement, compareTo);
                    }
                }
            }
        }

        int typeCount = 0;

        for(Vehicle.CarType type: Vehicle.CarType.values()){
            Iterator itr = response.iterator();
            while(itr.hasNext()){
                Vehicle v = (Vehicle) itr.next();
                if(type == v.getCarType()){
                    typeCount++;
                    break;
                }
            }
        }
        Assert.assertEquals(6, typeCount);
    }

    @Test
    public  void testRunOrderByScore() {
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Collections.sort(vehicleList, Vehicle.scoreComparator);

        int index = 0;
        for(Vehicle v : vehicleList){
            String print = null;
            index++;
            print = index + ".    "
                    + v.getName() + " - "
                    + v.getScore() + " - "
                    + v.getRating() + " - "
                    + v.getSumOfScores();
            System.out.println(print);
        }

    }



}
