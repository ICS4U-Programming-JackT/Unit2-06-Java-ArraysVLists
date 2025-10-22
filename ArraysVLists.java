import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Program that reads integers from a file, calculates and displays
 * the mean and median of the list, and terminates upon encountering
 * a non-integer value.
 *
 * @author Jack
 * @version 1.0
 * @since 2025-10-21 */
public final class ArraysVLists {

    /** Private constructor to prevent instantiation. */
    private ArraysVLists() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Main entry point.
     * @param numbers array containing numbers
     * @return mean
     */
    public static float getMean(final int[] numbers) {
        // Calculate Mean
        long sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        float mean = sum / numbers.length;

        return mean;
    }

    /**
     * Main entry point.
     * @param numbers array containing numbers
     * @return median
     */
    public static float getMedian(final int[] numbers) {
        // Sort the list for Median calculation
        Arrays.sort(numbers);

        // Calculate Median
        float median;
        int size = numbers.length;
        if (size % 2 == 1) {
            // Odd number of elements
            median = numbers[size / 2];
        } else {
            // Even number of elements
            int mid1 = numbers[size / 2 - 1];
            int mid2 = numbers[size / 2];
            median = (mid1 + mid2) / 2.0f;
        }

        return median;
    }

    /**
     * Main entry point.
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        // Create scanner to detect user input
        Scanner fileNumberScanner = new Scanner(System.in);

        System.out.print("Which file do you want to enter? (1, 2 or 3): ");

        // Get file number
        String fileNum = fileNumberScanner.nextLine();

        try {
            // Create file, scanner and empty array list
            File file = new File("input" + fileNum + ".txt");
            Scanner fileScanner = new Scanner(file);

            ArrayList<Integer> numbers = new ArrayList<>();

            // Iterate through every line
            while (fileScanner.hasNextLine()) {
                // Get next line
                String line = fileScanner.nextLine();
                try {
                    // Get line as an int and add to arraylist
                    int lineInt = Integer.parseInt(line);
                    numbers.add(lineInt);
                } catch (NumberFormatException error) {
                    // Clear numbers list and break
                    // to indicate error and termination
                    numbers.clear();
                    break; // Terminate reading the file
                }
            }

            // Convert Arraylist to array
            int[] numbersAsArray = new int[numbers.size()];
            for (int i = 0; i < numbersAsArray.length; i++) {
                numbersAsArray[i] = numbers.get(i);
            }

            float mean = getMean(numbersAsArray);
            float median = getMedian(numbersAsArray);

            if (numbersAsArray.length > 0) {
                // Display Mean and Median
                System.out.println("\nResults for input"
                + fileNum + ".txt");
                System.out.printf("Mean: %.2f%n", mean);
                System.out.printf("Median: %.2f%n", median);
                System.out.println("Total numbers read: "
                + numbersAsArray.length);
            } else {
                // We check if the file exists or
                // if the file is empty and tell user
                if (file.exists() && file.length() == 0) {
                     System.out.println("Error: The file is empty.");
                } else {
                    System.out.println("\nError: Reading terminated! Your file "
                    + "contains a non-integer "
                    + "value and cannot be processed further.");
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException error) {
            // File wasn't found
            System.out.println("\nError: The file input" + fileNum
            + ".txt was not found."
            + " Please ensure it exists in the same directory.");
        } finally {
            fileNumberScanner.close();
        }
    }
}
