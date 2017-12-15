package com.RentalCarsApplication;

import FileReaderWriting.ReadIn;
import Logic.Queries;
import Vehicles.Vehicle;
import Vehicles.VehicleFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vehicle_test {


    @Test
    public void testOrderByPriceComparatorPOSITIVE(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle costly = VehicleFactory.getVehicle(10000, "Expensive", 5, "MBMN", "Cars");
        Vehicle economic = VehicleFactory.getVehicle(5, "Expensive", 5, "MBMN", "Cars");
        vehicleList.add(costly); vehicleList.add(economic);

        Collections.sort(vehicleList, Vehicle.priceComparator);

        Assert.assertEquals(economic, vehicleList.get(0));
    }

    @Test
    public void testOrderByPriceComparatorNEGATIVE(){
        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle costly = VehicleFactory.getVehicle(10000, "Expensive", 5, "MBMN", "Cars");
        Vehicle economic = VehicleFactory.getVehicle(5, "Expensive", 5, "MBMN", "Cars");
        vehicleList.add(costly); vehicleList.add(economic);

        Collections.sort(vehicleList, Vehicle.priceComparator);

        Assert.assertNotEquals(costly, vehicleList.get(0));
    }

    @Test
    public void testCalculateScore(){

        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Assert.assertEquals(11.9, vehicleList.get(0).getSumOfScores(), 0.01);
    }

    @Test
    public void testInterpretSIPP(){

        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle v = vehicleList.get(0);

        Assert.assertTrue( v.getCarType() == Vehicle.CarType.COMPACT
                            && v.getDoorType() == Vehicle.DoorType.FIVE_DOOR
                            && v.getTransmission() == Vehicle.Transmission.MANUAL
                            && v.getExtras() == Vehicle.Extras.AC);
    }

    @Test
    public void testCompareScoreDescending(){

        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle v1 = vehicleList.get(0);
        Vehicle v2 = vehicleList.get(1);

        Double rating1 = v1.getSumOfScores();
        Double rating2 = v2.getSumOfScores();
        // descending
        Assert.assertEquals(1, rating2.compareTo(rating1));


    }

    @Test
    public void testCompareScoreAscending(){

        ReadIn readIn = new ReadIn();
        readIn.readInJson("sample.json");
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList = readIn.getVehicleList();

        Vehicle v1 = vehicleList.get(0);
        Vehicle v2 = vehicleList.get(1);

        Double rating1 = v1.getSumOfScores();
        Double rating2 = v2.getSumOfScores();
        // descending
        Assert.assertEquals(-1, rating1.compareTo(rating2));


    }

}
