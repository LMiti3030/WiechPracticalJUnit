package mititelu.laura.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    //for each test create a new diet planner instance
    void setUp(){
        dietPlanner = new DietPlanner(20,30,50);
    }

    @Test
    void should_ReturnCorrectDietPlan_When_CorrectCoder(){
        //given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
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

}