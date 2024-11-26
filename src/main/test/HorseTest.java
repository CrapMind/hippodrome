import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    void shouldThrowExceptionWhenNameIsNullAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(null, 1, 2));
        assertEquals(exception.getMessage(), "Name cannot be null.");
    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t", "\n"})
    void shouldThrowExceptionWhenFirstParamIsBlankAndMessageIsCorrect(String emptyName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse(emptyName, 1, 2));
        assertEquals(exception.getMessage(), "Name cannot be blank.");
    }

    @Test
    void shouldThrowExceptionWhenSecondParamIsNegativeAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Horse", -1, 2));
        assertEquals(exception.getMessage(), "Speed cannot be negative.");
    }

    @Test
    void shouldThrowExceptionWhenThirdParamIsNegativeAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Horse", 2, -2));
        assertEquals(exception.getMessage(), "Distance cannot be negative.");
    }


}
