package FileReaderWriting;

import Vehicles.Vehicle;
import Vehicles.VehicleFactory;
import com.google.gson.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class ReadIn {

   private List<Vehicle> vehicleList;

    public ReadIn() {
        vehicleList = new ArrayList<Vehicle>();
    }

    /**
     * Reads in a JSON Vehicle object given a filepath. Adds to vehicleList.
     * @param fp filepath to .json
     */
    public void readInJson(String fp) throws JsonParseException{
        JsonParser parser = new JsonParser();
        InputStream in = getClass().getClassLoader().getResourceAsStream(fp);
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
    }

    /**
     * Getter for vehicleList
     * @return vehicleList
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
