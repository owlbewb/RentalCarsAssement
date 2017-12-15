package Vehicles;

import java.util.Comparator;


public class Vehicle {

    private double price;
    private String name;
    private double rating;
    private String sipp;
    private CarType carType;
    private DoorType doorType;
    private Transmission transmission;
    private Extras extras;
    private String supplier;
    private int score;
    private double sumOfScores;


    /**
     * Constructor.
     *
     * @param price    The price to rent the vehicle
     * @param name     Model name
     * @param rating   Supplier rating
     * @param sipp     Feature shorthand
     * @param supplier Provider of the renting service
     */
    public Vehicle(double price, String name, double rating, String sipp, String supplier) {
        this.price = price;
        this.name = name;
        this.rating = rating;
        this.sipp = sipp;
        this.supplier = supplier;
        interpretSIPP();
        calculateScore();
        this.sumOfScores = this.getScore() + this.getRating();
    }

    /**
     * Calcaulates a vehicles score. Scores are added based on
     * the following score breakdown.
     * <p>
     * Manual transmission – 1 point
     * Automatic transmission – 5 points
     * Air conditioned – 2 points
     */
    private void calculateScore() {
        int score = 0;

        if (this.transmission == Transmission.MANUAL)
            score = score + 1;
        if (this.transmission == Transmission.AUTOMATIC)
            score = score + 5;
        if (this.extras == Extras.AC)
            score = score + 2;

        this.score = score;
    }

    /**
     * Interprets SIPP code. Each classifier is mapped to a Enum, declared at the
     * bottom of this class.
     */
    private void interpretSIPP() {

        String sipp = this.sipp;

        // Car Type
        switch (sipp.charAt(0)) {
            case 'M':
                this.carType = CarType.MINI;
                break;
            case 'E':
                this.carType = CarType.ECONOMY;
                break;
            case 'C':
                this.carType = CarType.COMPACT;
                break;
            case 'I':
                this.carType = CarType.INTERMEDIATE;
                break;
            case 'S':
                this.carType = CarType.STANDARD;
                break;
            case 'F':
                this.carType = CarType.FULL_SIZE;
                break;
            case 'P':
                this.carType = CarType.PREMIUM;
                break;
            case 'L':
                this.carType = CarType.LUXURY;
                break;
            case 'X':
                this.carType = CarType.SPECIAL;
                break;
        }

        // Door Type
        switch (sipp.charAt(1)) {
            case 'B':
                this.doorType = DoorType.TWO_DOOR;
                break;
            case 'C':
                this.doorType = DoorType.FOUR_DOOR;
                break;
            case 'D':
                this.doorType = DoorType.FIVE_DOOR;
                break;
            case 'W':
                this.doorType = DoorType.ESTATE;
                break;
            case 'T':
                this.doorType = DoorType.CONVERTIBLE;
                break;
            case 'F':
                this.doorType = DoorType.SUV;
                break;
            case 'P':
                this.doorType = DoorType.PICK_UP;
                break;
            case 'V':
                this.doorType = DoorType.PASSENGER_VAN;
                break;
            case 'X':
                this.doorType = DoorType.SPECIAL;
                break;
        }

        // Transmission Type
        switch (sipp.charAt(2)) {
            case 'M':
                this.transmission = Transmission.MANUAL;
                break;
            case 'A':
                this.transmission = Transmission.AUTOMATIC;
                break;
        }

        // Transmission Type
        switch (sipp.charAt(3)) {
            case 'N':
                this.extras = Extras.NO_AC;
                break;
            case 'R':
                this.extras = Extras.AC;
                break;
        }
    }

    // To string
    @Override
    public String toString() {
        return "price = " + price + ", name = " + name + ", rating = " + rating + ", sipp = " + sipp + ", supplier = " + supplier;
    }

    // --- GETTERS AND SETTERS --- //

    public double getSumOfScores() {
        return sumOfScores;
    }

    public void setSumOfScores(double sumOfScores) {
        this.sumOfScores = sumOfScores;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSipp() {
        return sipp;
    }

    public void setSipp(String sipp) {
        this.sipp = sipp;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public DoorType getDoorType() {
        return doorType;
    }

    public void setDoorType(DoorType doorType) {
        this.doorType = doorType;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    // ---- COMPARATORS --- //


    /**
     * Comparator to sort by vehicle price
     */
    public static Comparator<Vehicle> priceComparator = new Comparator<Vehicle>() {
        /**
         * Compares two vehicles prices.
         * @param v1 The first vehicle
         * @param v2 The second vehicle
         * @return -1 if the first vehicle is less expensive than the second or 1 if the
         * first vehicle is more expensive.
         */
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            Double price1 = v1.getPrice();
            Double price2 = v2.getPrice();

            // ascending
            return price1.compareTo(price2);
        }
    };

    /**
     * Comparator to sort by vehicle score
     */
    public static Comparator<Vehicle> scoreComparator = new Comparator<Vehicle>() {
        /**
         * Compares two vehicle scores.
         * @param v1 The first vehicle
         * @param v2 the second vehicle
         * @return -1 if the second vehicle has a lower score, or 1 if the second
         * vehicle has a higher score.
         */
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            Double rating1 = v1.getSumOfScores();
            Double rating2 = v2.getSumOfScores();
            // descending
            return rating2.compareTo(rating1);
        }
    };


    // --- ENUMS ------ //
    public enum CarType {
        MINI, ECONOMY, COMPACT, INTERMEDIATE, STANDARD, FULL_SIZE, PREMIUM, LUXURY, SPECIAL
    }

    public enum DoorType {
        TWO_DOOR, FOUR_DOOR, FIVE_DOOR, ESTATE, CONVERTIBLE, SUV, PICK_UP, PASSENGER_VAN, SPECIAL
    }

    public enum Transmission {
        MANUAL, AUTOMATIC
    }

    public enum Extras {
        NO_AC, AC
    }


}


