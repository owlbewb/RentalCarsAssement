package com.RentalCarsApplication;

import Vehicles.Vehicle;
import Vehicles.VehicleFactory;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonParse_test {

    public static final String FILEPATH = "sample.json";

    @Test
    public void testStreamInput(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        Assert.assertNotNull(root);
    }

    @Test
    public void testJsonRoot(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        Assert.assertNotNull(root);
    }

    @Test
    public void testJsonGetArray(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        Assert.assertNotNull(vehicleList);
    }

    @Test
    public void testCountVehicleList(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        Assert.assertEquals(31, vehicleList.size());
    }

    @Test
    public void testAccessArray(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for(JsonElement jsonElement : vehicleList){
            JsonObject vehicle = jsonElement.getAsJsonObject();
            Assert.assertNotNull(vehicle);
        }
    }


    @Test
    public void testVehicleSIPPSAccess(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for(JsonElement jsonElement : vehicleList){
            JsonObject vehicle = jsonElement.getAsJsonObject();
            String sipp =  vehicle.get("sipp").toString();
            Assert.assertEquals("\"CMDR\"", sipp);
        }
    }

    @Test
    public void testVehicleNameAccess(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for(JsonElement jsonElement : vehicleList){
            JsonObject vehicle = jsonElement.getAsJsonObject();
            String name =  vehicle.get("name").toString();
            Assert.assertEquals("\"Ford Galaxy\"", name);
        }
    }

    @Test
    public void testVehicleConstructor(){
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray vehicleList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for(JsonElement jsonElement : vehicleList){
            JsonObject obj = jsonElement.getAsJsonObject();
            Vehicle vehicle = VehicleFactory.getVehicle(
                obj.get("price").getAsDouble(),
                obj.get("name").getAsString(),
                obj.get("rating").getAsDouble(),
                obj.get("sipp").getAsString(),
                obj.get("supplier").getAsString());
            Vehicle testVehicle = VehicleFactory.getVehicle(157.85,"Ford Focus",8.9,"CDMR","Hertz");
            Assert.assertEquals(vehicle, testVehicle);
        }
    }

    @Test
    public void testVehicleArray() {

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray rootList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for (JsonElement jsonElement : rootList) {
            JsonObject obj = jsonElement.getAsJsonObject();
            Vehicle vehicle = VehicleFactory.getVehicle(
                    obj.get("price").getAsDouble(),
                    obj.get("name").getAsString(),
                    obj.get("rating").getAsDouble(),
                    obj.get("sipp").getAsString(),
                    obj.get("supplier").getAsString());
            vehicleList.add(vehicle);
        }
        Assert.assertNotNull(vehicleList);

    }

    @Test
    public void testVehicleArraySize() {

        List<Vehicle> vehicleList = new ArrayList<Vehicle>();

        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(FILEPATH);
        Reader reader = new InputStreamReader(in);
        JsonElement root = parser.parse(reader);
        JsonObject rootObj = root.getAsJsonObject();
        JsonArray rootList = rootObj.getAsJsonObject("Search").getAsJsonArray("VehicleList");
        for (JsonElement jsonElement : rootList) {
            JsonObject obj = jsonElement.getAsJsonObject();
            Vehicle vehicle = VehicleFactory.getVehicle(
                    obj.get("price").getAsDouble(),
                    obj.get("name").getAsString(),
                    obj.get("rating").getAsDouble(),
                    obj.get("sipp").getAsString(),
                    obj.get("supplier").getAsString());
            vehicleList.add(vehicle);
        }
        Assert.assertEquals(31, vehicleList.size());
    }



}
