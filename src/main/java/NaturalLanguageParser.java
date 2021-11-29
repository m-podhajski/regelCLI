import edu.stanford.nlp.sempre.Master;
import fig.exec.Execution;
import regex.TestDemo;

class NaturalLanguageParser
{

    private static String[] generateSketchArguments = { "-languageAnalyzer",
                                                        "corenlp.CoreNLPAnalyzer",
                                                        "-parser",
                                                        "SketchParser",
                                                        "-Grammar.inPaths",
                                                        "dataset/regex.grammar",
                                                        "-SimpleLexicon.inPaths",
                                                        "dataset/regex.lexicon",
                                                        "-FeatureExtractor.featureDomains",
                                                        "rule",
                                                        "span",
                                                        "context",
                                                        "bigram",
                                                        "phrase-category",
                                                        "-Builder.inParamsPath",
                                                        "pretrained_models/pretrained_so/params",
                                                        "-Parser.beamSize",
                                                        "200" };

    static String parseNaturalLanguage(String naturalLanguage)
    {
        System.out.println("Processing....");

        StringBuilder normalizedNaturalLanguageBuilder = new StringBuilder();
        normalizedNaturalLanguageBuilder.append("0\t");
        boolean quotes = false;
        for (char c : naturalLanguage.toCharArray())
        {
            if (c == '\"')
            {
                if (quotes)
                {
                    normalizedNaturalLanguageBuilder.append(" rightquoatation");
                }
                else
                {
                    normalizedNaturalLanguageBuilder.append("leftquoatation ");
                }
                quotes = !quotes;
            }
            else if (Character.isUpperCase(c) && quotes)
            {
                normalizedNaturalLanguageBuilder.append("upper").append(Character.toLowerCase(c));
            }
            else
            {
                normalizedNaturalLanguageBuilder.append(c);
            }
        }
        return normalizedNaturalLanguageBuilder.toString();
    }

    static boolean generateSketches()
    {
        TestDemo t = new TestDemo();
        t.dataset = "_tmp";
        try
        {
            Execution.run(generateSketchArguments, "Test", t, Master.getOptionsParser());

        }
        catch (ExitTrappedException exception)
        {
            return true;
        }
        return false;
    }
}
