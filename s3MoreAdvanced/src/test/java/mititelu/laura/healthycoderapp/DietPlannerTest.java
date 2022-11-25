package mititelu.laura.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private mititelu.laura.healthycoderapp.DietPlanner dietPlanner;

    @BeforeEach
    //for each test create a new diet planner instance
    void setUp(){
        dietPlanner = new DietPlanner(20,30,50);
    }

    @Test
    void should_ReturnCorrectDietPlan_When_CorrectCoder(){
        //given
        Coder coder = new Coder(1.82, 75.0, 26, mititelu.laura.healthycoderapp.Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        //assertEquals(expected, actual); -> fails, 2 different objects in the memory
        //use assertAll and inside check for equality in each single field
        //what if we override the equals method?
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }

    @AfterEach
    void afterEach(){
        System.out.println("A unit test was finished");
    }


    /**
     * Repeat test several times for same method
     * exmpl random generation and want to make sure all of them are legit
     * also when dealing with multiple threads
     * @RepeatedTest(10) - @Repeated test annotation + number of repetition
     * Each repetition is treated as a separate unit test => means that @BeforeEach and @AfterEach will be executed
     * can also customize the name shown :@RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
     * Only use repeated tests when they make sense (randomization + multiple threads)
     */
    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDietPlan_When_CorrectCoder_Repeated(){
        //given
        Coder coder = new Coder(1.82, 75.0, 26, mititelu.laura.healthycoderapp.Gender.MALE);
        DietPlan expected = new DietPlan(2202,110,73,275);

        //when
        DietPlan actual = dietPlanner.calculateDiet(coder);

        //then
        //assertEquals(expected, actual); -> fails, 2 different objects in the memory
        //use assertAll and inside check for equality in each single field
        //what if we override the equals method?
        assertAll(
                () -> assertEquals(expected.getCalories(), actual.getCalories()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getProtein(), actual.getProtein()),
                () -> assertEquals(expected.getCarbohydrate(), actual.getCarbohydrate())
        );
    }
}