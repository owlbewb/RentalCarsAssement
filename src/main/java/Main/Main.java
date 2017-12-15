package Main;

/**
 * Entry point for program.
 *
 * Filepath can be specified here in case evaluation is done with a different .json file.
 *
 * @Author Daniel Abreu
 */

public class Main {

    public static final String FILEPATH = "data.json";

    public static void main(String[] args) {

        new ConsoleApp(FILEPATH);

    }

}
