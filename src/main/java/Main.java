import java.io.*;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main
{

    private static void forbidSystemExitCall()
    {
        final SecurityManager securityManager = new SecurityManager()
        {
            @Override
            public void checkPermission(Permission permission)
            {
                if ("exitVM.0".equals(permission.getName()))
                {
                    throw new ExitTrappedException();
                }
            }
        };
        System.setSecurityManager(securityManager);
    }

    private static void clearOoutputs()
    {
        System.setOut(new PrintStream(new OutputStream()
        {
            public void write(int b)
            {
            }
        }));
    }


    public static void main(String[] args)
    {
        PrintStream stdout = System.out;
        PrintStream stderr = System.err;
        forbidSystemExitCall();
        String naturalLanguage = InputOutput.readNaturalLanguage();
        String parsedNaturalLanguage = NaturalLanguageParser.parseNaturalLanguage(naturalLanguage);
        if (!InputOutput.saveNaturalLanguage(parsedNaturalLanguage))
        {
            return;
        }
        clearOoutputs();
        if (NaturalLanguageParser.generateSketches())
        {
            System.setOut(stdout);
            System.out.println("OK");
        }
        Map<String, Boolean> examples = new HashMap<>();

        while (true)
        {
            examples.putAll(InputOutput.readExamples());
            InputOutput.saveInputsFile(examples, naturalLanguage);
            RegexGenerator.generatePredictions();
            System.setOut(stdout);
            System.setErr(stderr);
            InputOutput.printPredictions("predictions.txt");
            System.out.println("Is any regex correct? [y/n]");
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("y"))
            {
                break;
            }
        }
    }
}
