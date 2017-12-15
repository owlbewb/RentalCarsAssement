package Logic;

import Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class executes the query logic required to return the appropriate results.
 */

public class Queries {

    /**
     * Returns a given list of vehicles ordered in ascending order by price
     *
     * @param list the list of vehicles to be sorted
     */
    public static void runOrderByPrice(List<Vehicle> list) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) list;
        Collections.sort(vehicleList, Vehicle.priceComparator);
        for (Vehicle v : vehicleList) {
            int index = vehicleList.indexOf(v) + 1;
            String name = v.getName();
            double price = v.getPrice();
            System.out.println(index + ".    " + name + " - " + price);
        }
        System.out.println();
    }

    /**
     * Prints out all the specifications of vehicles in a given list
     *
     * @param list a list of vehicles.
     */
    public static void runOrderBySpecification(List<Vehicle> list) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) list;
        Iterator itr = vehicleList.iterator();
        int index = 0;

        while (itr.hasNext()) {
            Vehicle v = (Vehicle) itr.next();
            index++;
            String response = null;
            response = index + ".  "
                    + v.getName() + " - "
                    + v.getSipp() + " - "
                    + v.getCarType() + " - "
                    + v.getDoorType() + " - "
                    + v.getTransmission() + " - "
                    + "PETROL - " + v.getExtras();
            System.out.println(response);
        }
        System.out.println();
    }

    /**
     * Prints out all vehicles ordered by supplier rating and grouped by car type
     *
     * @param list
     */
    public static void runOrderBySupplierAndType(List<Vehicle> list) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) list;
        ArrayList<Vehicle> response = new ArrayList<>();

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

        // Printing
        int index = 0;
        for (Vehicle v : response) {
            String print = null;
            index++;
            print = index + ".    "
                    + v.getName() + " - "
                    + v.getCarType() + " - "
                    + v.getSupplier() + " - "
                    + v.getRating();
            System.out.println(print);
        }
    }

    /**
     * Sorts the array of vehicles by Score. The results are printed to the terminal.
     *
     * @param vehicleList A list of Vehicles.
     */
    public static void runOrderByScore(List<Vehicle> vehicleList) {

        Collections.sort(vehicleList, Vehicle.scoreComparator);

        int index = 0;
        for (Vehicle v : vehicleList) {
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
