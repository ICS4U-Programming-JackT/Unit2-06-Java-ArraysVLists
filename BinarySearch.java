import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Program that generates random integers, sorts them,
 * and performs a Binary search for user-input values.
 *
 * @author Jack
 * @version 1.0
 * @since 2025-10-02
 */
public final class ArraysVsLists
 {


    /** Constant for minimum random number. */
    private static final int MIN = 0;


    /** Constant for maximum random number. */
    private static final int MAX = 100;


    /** Private constructor to prevent instantiation. */
    private BinarySearch() {
        throw new IllegalStateException("Utility Class");
    }


    /**
     * Returns index of target in array or -1.
     * @param target number to search for
     * @param array array to search in
     * @return index or -1
     */
    public static int binarySearch(final int target, final int[] array) {
        // Initialize high low mid and final index
        int high = array.length - 1;
        int low = 0;
        int mid = (high + low) / 2;
        int fIndex = -1;

        //While high is bigger than or equal to low, continue
        while (high >= low) {
            // Check proximity of mid point to target
            // adjust low and high to reduce range
            if (target > array[mid]) {
                low = mid + 1;
            } else if (target < array[mid]) {
                high = mid - 1;
            }
            //Readjust mid after changes
            mid = (high + low) / 2;

            //Check if target is mid point
            if (target == array[mid]) {
                fIndex = mid;
                break;
            }
        }
        // Return either -1 or target index
        return fIndex;
    }


    /**
     * Generates an array of 10 random integers between MIN and MAX.
     * @return generated array
     */
    public static int[] setupArray() {
        final Random random = new Random();
        final int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = random.nextInt(MAX - MIN + 1) + MIN;
        }
        return array;
    }


    /**
     * Main entry point.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        // Setup array
        final int[] sorted = setupArray();
        Arrays.sort(sorted);


        // User interaction
        System.out.println("Array: " + Arrays.toString(sorted));
        System.out.println("Enter a number to search for (or 'q' to quit)");

        // Setup scanner
        final Scanner scanner = new Scanner(System.in);


        // Loop for user input
        while (true) {
            System.out.print("Enter target: ");
            final String targetString = scanner.nextLine();


            // If user decides to quit
            if (targetString.equalsIgnoreCase("q")) {
                System.out.println("Thanks for playing!");
                break;
            }


            // Try to parse input and search
            try {
                // Define target as int and final position
                final int targetInt = Integer.parseInt(targetString);
                final int index = binarySearch(targetInt, sorted);
                // Print if target is in array or not
                if (index == -1) {
                    System.out.println(
                        "The number you entered is not in the array."
                    );
                } else {
                    System.out.println(
                        "The index of your target is " + index + "."
                    );
                }
            } catch (NumberFormatException e) {
                // If input is not a valid integer
                System.out.println(
                    "Invalid input. Please enter a number or 'q' to quit."
                );
            }
        }


        // Close the scanner
        scanner.close();
    }
}
