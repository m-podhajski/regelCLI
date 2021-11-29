import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class InputOutput
{

    static String readNaturalLanguage()
    {
        System.out.println("Natural language:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    static boolean saveNaturalLanguage(String naturalLanguage)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("regex/data/_tmp/src-test.txt", "UTF-8");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error saving natural language - file src-test.txt not found");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Error saving natural language");
        }
        if (writer == null)
        {
            return false;
        }
        writer.println(naturalLanguage);
        writer.close();
        return true;
    }

    static Map<String, Boolean> readExamples()
    {
        Scanner scanner = new Scanner(System.in);
        Map<String, Boolean> examples = new HashMap<>();
        while (true)
        {
            System.out.println("Add example (enter to finish):");
            String example = scanner.nextLine();
            if (example.isEmpty())
            {
                break;
            }
            System.out.println("+/-");
            String positiveNeagtive = scanner.nextLine();
            if (positiveNeagtive.equals("+"))
            {
                examples.put(example, true);
            }
            else if (positiveNeagtive.equals("-"))
            {
                examples.put(example, false);
            }
        }
        return examples;
    }

    static boolean saveInputsFile(Map<String, Boolean> examples,
                                  String naturalLanguage)
    {
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter("inputs.txt", "UTF-8");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error saving inputs - file inputs.txt not found");
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println("Error saving inputs");
        }
        if (writer == null)
        {
            return false;
        }
        writer.println("// natural language");
        writer.println(naturalLanguage);
        writer.println();
        writer.println("// example");
        for (String example : examples.keySet())
        {
            writer.println("\"" + example + "\"," + (examples.get(example) ? "+" : "-"));
        }
        writer.println();
        writer.println("// gl");
        writer.println("na");
        writer.close();
        return true;
    }

    static void printPredictions(String predictionsFile)
    {
        HashMap<String, Float> predictions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(predictionsFile)))
        {
            Pattern r = Pattern.compile("(.*): .*null`(.*)`false");
            String line = br.readLine();
            while (line != null && !line.isEmpty())
            {
                Matcher m = r.matcher(line);
                if (m.matches())
                {
                    String regex = m.group(1);
                    Float time = Float.parseFloat(m.group(2));
                    predictions.put(regex, time);
                }
                line = br.readLine();
            }
            List<String> sortedPredictions =
                    predictions.entrySet()
                               .stream()
                               .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.naturalOrder()))
                               .map(Map.Entry::getKey)
                               .collect(Collectors.toList());

            System.out.println("Predicted regexps:");
            for (String prediction : sortedPredictions)
            {
                System.out.println(prediction);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error printing predictions");
        }
    }
}
