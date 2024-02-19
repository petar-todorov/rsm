package secondLargestFinder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SecondLargestFinderTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void testNormalCase() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("1,3,4,5,6,2");
        assertEquals("Input: 1,3,4,5,6,2 -> Output: 5", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testMinimumNumberOfElements() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("100,200");
        assertEquals("Input: 100,200 -> Output: 100", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWithSingleElement() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("5");
        assertEquals("Input: 5 -> Output: Error, list must contain at least two distinct elements.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWithAllElementsTheSame() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("2,2,2");
        assertEquals("Input: 2,2,2 -> Output: Error, all elements are the same.", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWithNegativeNumbers() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("-10,-20,-30");
        assertEquals("Input: -10,-20,-30 -> Output: -20", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWithMaxAndMinIntValues() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne(Integer.MIN_VALUE + "," + Integer.MAX_VALUE);
        assertEquals("Input: " + Integer.MIN_VALUE + "," + Integer.MAX_VALUE + " -> Output: " + Integer.MIN_VALUE, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testNonUniqueElementsExceptOne() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("1,1,1,2");
        assertEquals("Input: 1,1,1,2 -> Output: 1", outputStreamCaptor.toString().trim());
    }

    @Test
    public void testWithMultipleNonUniqueMaxValues() {
        System.setOut(new PrintStream(outputStreamCaptor));
        SecondLargestFinder.secondOne("5,5,3,3,2,2");
        assertEquals("Input: 5,5,3,3,2,2 -> Output: 3", outputStreamCaptor.toString().trim());
    }

    // Resets System.out to its original state after each test
    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
