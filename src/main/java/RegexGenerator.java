import java.io.*;

import resnax.so.Run;

class RegexGenerator
{

    private static String normalizeSketch(String sketch)
    {
        sketch = sketch.replace("<space>", "< >");
        sketch = sketch.replace("<-lrb->", "<(>");
        sketch = sketch.replace("<-rrb->", "<)>");
        sketch = sketch.replace("\\\\", "\\");
        while (sketch.contains("upper"))
        {
            int position = sketch.indexOf("upper");
            char upperCharacter = sketch.charAt(position + 5);
            sketch = sketch.replace("upper" + upperCharacter, "" + Character.toUpperCase(upperCharacter));
        }
        return sketch;
    }

    static void generatePredictions()
    {

        try
        {
            System.setOut(new PrintStream(new File("predictions.txt")));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("regex/data/_tmp/200-pred-test.txt")))
        {
            String line = br.readLine();
            Integer size = Integer.valueOf(line.split("\\s+")[1]);
            for (int i = 0; i < size; i++)
            {
                String sketch = br.readLine().split("\\s+")[0];
                sketch = normalizeSketch(sketch);
                String[] runArguments = { "inputs.txt",
                                          "logs",
                                          sketch,
                                          "1",
                                          "1",
                                          "0" };
                Run.run(runArguments);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error generating predictions");
        }
    }

}
