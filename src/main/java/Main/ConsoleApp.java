package Main;

import FileReaderWriting.ReadIn;
import Logic.Queries;
import Vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Main console navigation occurs in this class.
 * Also, contains List<Vehicle> vehicleList which stores the data parsed from JSON.
 */

public class ConsoleApp {

    // Parsed JSON data as Object
    List<Vehicle> vehicleList;

    public ConsoleApp(String fp) {

        System.out.println("> Welcome < ");
        System.out.flush();
        ReadIn readin = new ReadIn();
        readin.readInJson(fp);
        vehicleList = readin.getVehicleList();
        //while(true){
        eventLoop();
        //}
    }

    /**
     * Controls the flow of the console application.
     * User input the number of the option they want to see.
     */
    private void eventLoop() {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;

        try {
            System.out.println("Json parsed successfully...\n\n");
            System.out.flush();

            do {
                System.out.println("What information do you wish to see?");
                System.out.println("> 1: Vehicles ordered by price <");
                System.out.println("> 2: Vehicles specifications <");
                System.out.println("> 3: Vehicles type by supplier rating<");
                System.out.println("> 4: Vehicles based on score <");
                System.out.println("Please choose an option: ");

                line = in.readLine().toLowerCase();

                if (line.contains("1")) {
                    System.out.println("\n> Vehicles ordered by price <");
                    Queries.runOrderByPrice(vehicleList);
                }
                if (line.contains("2")) {
                    System.out.println("\n> Vehicles specifications <");
                    Queries.runOrderBySpecification(vehicleList);
                }
                if (line.contains("3")) {
                    System.out.println("\n Vehicles type by supplier rating");
                    Queries.runOrderBySupplierAndType(vehicleList);
                }
                if (line.contains("4")) {
                    System.out.println("\n Vehicles based on score");
                    Queries.runOrderByScore(vehicleList);
                }

            } while (!line.startsWith("quit") || !line.startsWith("exit"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
