import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

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
                ()-> new Horse("Test", -1, 2));
        assertEquals(exception.getMessage(), "Speed cannot be negative.");
    }

    @Test
    void shouldThrowExceptionWhenThirdParamIsNegativeAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Horse("Test", 2, -2));
        assertEquals(exception.getMessage(), "Distance cannot be negative.");
    }

    @Test
    void getNameShouldReturnStringPassedToConstructorAsTheFirstParam() {
        String testNameParam = "Test";
        Horse horse = new Horse(testNameParam, 2, 2);
        assertEquals(horse.getName(), testNameParam);
    }

    @Test
    void getSpeedShouldReturnNumberPassedToConstructorAsTheSecondParam() {
        double testSpeedParam = 22;
        Horse horse = new Horse("Test", testSpeedParam, 2);
        assertEquals(horse.getSpeed(), testSpeedParam);
    }

    @Test
    void getDistanceShouldReturnNumberPassedToConstructorAsTheThirdParam() {
        double testDistanceParam = 545;
        Horse horse = new Horse("Test", 2, testDistanceParam);
        assertEquals(horse.getDistance(), testDistanceParam);
    }

    @Test
    void moveShouldCallGetRandomDoubleWithCorrectParameters() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", 2, 2);

            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
    @ParameterizedTest
    @CsvSource({
            "10, 100, 0.5, 105.0", // speed=10, distance=100, randomValue=0.5 -> expected distance=105.0
            "20, 200, 0.8, 216.0", // speed=20, distance=200, randomValue=0.8 -> expected distance=216.0
            "5, 50, 0.3, 51.5"    // speed=5, distance=50, randomValue=0.3 -> expected distance=51.5
    })
    void moveShouldUpdateDistanceCorrectly(double speed, double distance, double randomValue, double expectedDistance) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            Horse horse = new Horse("Test", speed, distance);

            horse.move();

            assertEquals(expectedDistance, horse.getDistance(), 0.0001); // added delta for more accurate results
        }
    }
}
