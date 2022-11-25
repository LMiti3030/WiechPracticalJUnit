package healthycoderapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ActivityCalculatorTest {

    /** TDD - write Unit tests that represent the business requirement
     * need to make sure that all are satisfied
     * write as if activity calculator i
     */

    @Test
    void should_ReturnBad_When_AverageBelow20(){
        //given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 1;

        //when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        //then
        assertEquals("bad", actual);
    }

    @Test
    void should_ReturnAverage_When_AvgBetween20And40(){

        //given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 3;

        //when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        //then
        assertEquals("average", actual);

    }

    @Test
    void should_returnGood_When_AvgAbove40(){

        //given
        int weeklyCardioMin = 40;
        int weeklyWorkoutSessions = 7;

        //when
        String actual = ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        //then
        assertEquals("good", actual);

    }

    @Test
    void should_ThrowException_When_InputBelowZero(){

        //given
        int weeklyCardioMin = -3;
        int weeklyWorkoutSessions = -7;

        //when
        Executable executable = ()-> ActivityCalculator.rateActivityLevel(weeklyCardioMin, weeklyWorkoutSessions);

        //then
        assertThrows(RuntimeException.class, executable);

    }



}
