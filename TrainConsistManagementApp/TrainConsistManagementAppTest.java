import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class UseCase11TrainConsistMgmntTest {

    @Test
    public void testRegex_ValidTrainID() {
        assertTrue(Pattern.matches("TRN-\\d{4}", "TRN-1234"));
    }

    @Test
    public void testRegex_InvalidTrainIDFormat() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRAIN12"));
        assertFalse(Pattern.matches("TRN-\\d{4}", "1234-TRN"));
    }

    @Test
    public void testRegex_ValidCargoCode() {
        assertTrue(Pattern.matches("PET-[A-Z]{2}", "PET-AB"));
    }

    @Test
    public void testRegex_InvalidCargoCodeFormat() {
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-ab"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET123"));
    }

    @Test
    public void testRegex_TrainIDDigitLengthValidation() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-123"));
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-12345"));
    }

    @Test
    public void testRegex_CargoCodeUppercaseValidation() {
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-ab"));
    }

    @Test
    public void testRegex_EmptyInputHandling() {
        assertFalse(Pattern.matches("TRN-\\d{4}", ""));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", ""));
    }

    @Test
    public void testRegex_ExactPatternMatch() {
        assertFalse(Pattern.matches("TRN-\\d{4}", "TRN-1234X"));
        assertFalse(Pattern.matches("PET-[A-Z]{2}", "PET-AB1"));
    }
}
