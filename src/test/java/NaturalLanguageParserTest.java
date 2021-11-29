import org.junit.Test;
import static org.junit.Assert.*;

public class NaturalLanguageParserTest
{

    @Test
    public void parseNaturalLanguage()
    {
        String output = NaturalLanguageParser.parseNaturalLanguage("regular expression that allow a \"A\" after at least 3 digits");
        assertEquals(output, "0\tregular expression that allow a leftquoatation uppera rightquoatation after at least 3 digits");
    }
}