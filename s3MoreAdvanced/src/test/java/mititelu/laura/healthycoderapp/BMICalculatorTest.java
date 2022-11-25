package mititelu.laura.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import mititelu.laura.healthycoderapp.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "prod";

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all unit tests.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all unit tests");
    }

    @Test
    void test() {
        assertTrue(BMICalculator.isDietRecommended(81.2, 1.65));
    }

    @Nested
    class IsDietRecommendedTests{
        @Test
        void should_ReturnFalse_When_DietNotRecommended() {
            //GIVEN /ARRANGE
            double weight = 50.0;
            double height = 1.92;

            //WHEN /ACT
            boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

            //THEN /ASSERT
            assertFalse(isRecommended);

        }

        @Test
        void should_ThrowArithmeticException_When_HeightZero() {
            //GIVEN /ARRANGE
            double weight = 50.0;
            double height = 0;

            //WHEN /ACT
            //potentially executable = lambda expression that calls the method that can throw an exception
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            //THEN /ASSERT
            assertThrows(ArithmeticException.class, executable);

        }

        /**
         *     scenario when we change a single value each time a test is run
         *     weight value changed and height unchanged
         *     @ValueSource - pass the array with the test values @ValueSource(doubles = {70.0, 89.0, 95.0, 110})
         *     @ValueSource - just for one parameter that changes
         *     the method must have a parameter
         *     the values from the valuesource will be automatically injected into the parameter
         *     use the parameter in the method body
         *     will execute all tests cases
         * @param coderWeight
         */
        @ParameterizedTest
        @ValueSource(doubles = {89.0, 95.0, 110.0})
        void should_ReturnTrue_When_DietRecommended(Double coderWeight) {
            //GIVEN /ARRANGE
            double weight = coderWeight;
            double height = 1.72;

            //WHEN /ACT
            boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

            //THEN /ASSERT
            assertTrue(isRecommended);

        }

        /**
         * Scenario: both variables change
         * need to use @CsvSource annotation - stands for comma separated values
         * inside () must specify the value keyword + array of strings
         * each string contains a group of values, separated with commas for a single test case
         *
         * @param coderWeight
         * @param coderHeight
         * @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
         * weight 89, height 1.72 and so on
         * 2 param corresponding to the values
         * to show the test cases in more developer friendly way
         * @ParameterizedTest(name="weight={0}, height={1}")
         * numbers inside{} refer to  the numbers passed in the test case- first value has index 0 and so on
         */
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        void should_ReturnTrue_When_DietRecommended_2(Double coderWeight, Double coderHeight) {
            //GIVEN /ARRANGE
            double weight = coderWeight;
            double height = coderHeight;

            //WHEN /ACT
            boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

            //THEN /ASSERT
            assertTrue(isRecommended);

        }

        /**
         * Reading test values from a csv file found in test/resources folder
         * Junit5 will automatically start to look the file in test/resources folder
         * the first line is a header line => numLinesToSkip = 1
         * the first field will be injected into coderweight, the 2nd one in coderheight
         * @param coderWeight
         * @param coderHeight
         */
        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void should_ReturnTrue_When_DietRecommended_3(Double coderWeight, Double coderHeight) {
            //GIVEN /ARRANGE
            double weight = coderWeight;
            double height = coderHeight;

            //WHEN /ACT
            boolean isRecommended = BMICalculator.isDietRecommended(weight, height);

            //THEN /ASSERT
            assertTrue(isRecommended);

        }
    }

    @Nested
    class FindCoderWithWorstBMITests{
        @Test
        void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
            //given
            List<mititelu.laura.healthycoderapp.Coder> coders = new ArrayList<>();
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
        void should_ReturnNullWorstBMI_When_CoderListEmpty() {
            //given
            List<Coder> coders = new ArrayList<>();


            //when
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertNull(coderWorstBMI);
        }






        /**
         * When running tests within a time limit
         * exmpl Want the run within a second
         * Can write performance tests
         */

        @Test
        void should_ReturnCoderWithWorstBMIIn1ms_When_CoderListHas1_000Elements() {
            //given
            List<Coder> coders = new ArrayList<>();
            for(int i = 1; i < 1_000 ; i++){
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            //when
            Executable  executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(5), executable);
        }

        /**
         * Test will be skipped if the assumption fails
         * The test will not fail, but be skipped
         * assumeTrue(this.environment.equals("prod"));
         */
        @Test
        void should_ReturnCoderWithWorstBMIIn1ms_When_CoderListHas1_000Elements_OnlyInProd() {
            //given
            assumeTrue(BMICalculatorTest.this.environment.equals("prod"));
            List<Coder> coders = new ArrayList<>();
            for(int i = 1; i < 1_000 ; i++){
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            //when
            Executable  executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            //then
            assertTimeout(Duration.ofMillis(5), executable);
        }
    }

    @Nested
    class GetBMIScoresTests{
        @Test
        void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
            //given
            List<mititelu.laura.healthycoderapp.Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double[] expected = {18.52, 29.59, 19.53};
            //double[] expected = {18.00, 29.59, 19.53};

            //when
            double[] bmiScores = BMICalculator.getBMIScores(coders);

            //then
            //assertEquals(expected, bmiScores);
            assertArrayEquals(expected, bmiScores);
        }
    }



}