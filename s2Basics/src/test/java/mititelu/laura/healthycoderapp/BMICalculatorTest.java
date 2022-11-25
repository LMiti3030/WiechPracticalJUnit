package mititelu.laura.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BMICalculatorTest {

    @Test
    void test(){
        assertTrue(BMICalculator.isDietRecommended(81.2,1.65));
    }

    //Should name methods with should_ + when_
    //pot adauga si given
    @Test
    void should_ReturnTrue_When_DietRecommended() {
        //GIVEN /ARRANGE
        double weight = 89.0;
        double height = 1.72;

        //WHEN /ACT
        boolean isRecommended = mititelu.laura.healthycoderapp.BMICalculator.isDietRecommended(weight, height);

        //THEN /ASSERT
        assertTrue(isRecommended);

    }

    @Test
    void should_ReturnFalse_When_DietNotRecommended(){
        //GIVEN /ARRANGE
        double weight = 50.0;
        double height = 1.92;

        //WHEN /ACT
        boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

        //THEN /ASSERT
        assertFalse(isRecommended);

    }

    @Test
    void should_ThrowArithmeticException_When_HeightZero(){
        //GIVEN /ARRANGE
        double weight = 50.0;
        double height = 0;

        //WHEN /ACT
        //potentially executable = lambda expression that calls the method that can throw an exception
        Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

        //THEN /ASSERT
        assertThrows(ArithmeticException.class, executable);

    }

    @Test
    void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty(){
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));

        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertAll(
                () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                () -> assertEquals(98, coderWorstBMI.getWeight())
        );
    }

    @Test
    void should_ReturnNullWorstBMI_When_CoderListEmpty(){
        //given
        List<Coder> coders = new ArrayList<>();


        //when
        Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

        //then
        assertNull(coderWorstBMI);
    }

    @Test
    void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
        //given
        List<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80, 60));
        coders.add(new Coder(1.82, 98.0));
        coders.add(new Coder(1.82, 64.7));
        double[] expected = {18.52, 29.59, 19.53};
        //double[] expected = {18.00, 29.59, 19.53};

        //when
        double[] bmiScores = BMICalculator.getBMIScores(coders);

        //then
        //assertEquals(expected, bmiScores);
        assertArrayEquals(expected,bmiScores);
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all unit tests.");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all unit tests");
    }

}