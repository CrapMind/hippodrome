import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    void shouldThrowExceptionWhenParamIsNullAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Hippodrome(null));
        assertEquals(exception.getMessage(), "Horses cannot be null.");
    }

    @Test
    void shouldThrowExceptionWhenListIsEmptyAndMessageIsCorrect() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                ()-> new Hippodrome(new ArrayList<>()));
        assertEquals(exception.getMessage(), "Horses cannot be empty.");
    }

    @Test
    void getHorsesShouldReturnTheSameObjectsAndSequence() {
        ArrayList<Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            testHorses.add(new Horse("test horse" + i, i, i));
        }
        Hippodrome testHippodrome = new Hippodrome(testHorses);
        assertEquals(testHippodrome.getHorses(), testHorses);
    }

    @Test
    void shouldCallMoveMethodOnAllHorsesInList() {
        List<Horse> testHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            testHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(testHorses);

        hippodrome.move();
        testHorses.forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinnerShouldReturnHorseWithMaxDistance_Randomized() {
        Horse maxHorse = new Horse("Max", 10, 100); // Winner
        List<Horse> horses = new ArrayList<>(List.of(
                new Horse("Horse1", 5, 20),
                new Horse("Horse2", 6, 50),
                new Horse("Horse3", 7, 30),
                maxHorse // Winner with max distance
        ));

        Collections.shuffle(horses); // shuffle List for more accurate results

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(maxHorse, hippodrome.getWinner());
    }
}
