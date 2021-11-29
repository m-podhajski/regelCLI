import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.collect.Maps;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputOutputTest
{

    @Test
    public void readExamples()
    {
        System.setIn(new ByteArrayInputStream("7\n+\nsdsdsd\n-\nsdfsdfs\n=\n\n".getBytes()));
        Map<String, Boolean> actual = InputOutput.readExamples();
        Map<String, Boolean> expected = new HashMap<>();
        expected.put("7", true);
        expected.put("sdsdsd", false);
        assertTrue(Maps.difference(expected, actual).areEqual());
    }

    @Test
    public void printPredictions()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        InputOutput.printPredictions("src/test/resources/predictions.txt");
        String expected = "Predicted regexps:\n" +
                          "concat(repeat(<num>,3),<A>)\n" +
                          "concat(repeatrange(<num>,1,3),<A>)\n" +
                          "concat(repeat(<num>,3),<let>)\n" +
                          "concat(repeatatleast(<num>,3),<A>)\n" +
                          "concat(repeatatleast(<num>,3),<let>)\n" +
                          "endwith(<A>)\n";
        assertEquals(expected, outContent.toString());
    }
}