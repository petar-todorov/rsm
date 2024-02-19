package secondLargestFinder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecondLargestFinder {

    public static void secondOne(String input) {
        // Split the input string into an array of strings
        String[] items = input.split(",");
        // Check if the array has less than 2 unique elements, which is a corner case
        if (items.length < 2) {
            System.out.println("Input: " + input + " -> Output: Error, list must contain at least two distinct elements.");
            return;
        }

        Set<Integer> uniqueNumbers = new HashSet<>();
        int[] numbers = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            // Parse each string to an integer
            numbers[i] = Integer.parseInt(items[i].trim());
            uniqueNumbers.add(numbers[i]);
        }

        // Handle the case where all elements are the same
        if (uniqueNumbers.size() < 2) {
            System.out.println("Input: " + input + " -> Output: Error, all elements are the same.");
            return;
        }

        // Sort the array
        Arrays.sort(numbers);

        // Find the second largest element by iterating from the second-to-last element backwards
        Integer secondLargest = null;
        for (int i = numbers.length - 2; i >= 0; i--) {
            if (numbers[i] != numbers[numbers.length - 1]) {
                secondLargest = numbers[i];
                break;
            }
        }

        if (secondLargest == null) {
            System.out.println("Unable to find the second largest element due to unexpected input.");
        } else {
            // Print the second largest element
            System.out.println("Input: " + input + " -> Output: " + secondLargest);
        }
    }

    public static void main(String[] args) {
        // Test the function with different scenarios
        secondOne("1,2,3,4,5,6,45");
        secondOne("2,2,2");
        secondOne("100");
        // Add more test cases as necessary
    }
}
