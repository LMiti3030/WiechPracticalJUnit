package realestateapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolutionRandomApartmentGeneratorTest {

    private static final double MAX_MULTIPLIER = 4.0;

    @Nested
    class GeneratorDefaultParamsTests{

        private RandomApartmentGenerator randomApartmentGenerator;

        @BeforeEach
        void setup(){
            this.randomApartmentGenerator = new RandomApartmentGenerator();
        }

        @RepeatedTest(10)
        void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice(){
            //given
            double minArea = 30.0;
            double maxArea = 30.0 * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            //when
            Apartment apartment = randomApartmentGenerator.generate();

            //then
            BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }
    }

    @Nested
    class GeneratorCustomParamsTests{

        private RandomApartmentGenerator randomApartmentGenerator;
        private double minArea = 20.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(1500.0);

        @BeforeEach
        void setup(){
            this.randomApartmentGenerator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);
        }

        @RepeatedTest(10)
        void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice(){
            //given
            double minArea = this.minArea;
            double maxArea = minArea * MAX_MULTIPLIER;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(MAX_MULTIPLIER));

            //when

            //when
            Apartment apartment = randomApartmentGenerator.generate();

            //then
            BigDecimal maxApartmentPrice = new BigDecimal(apartment.getArea()).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = new BigDecimal(apartment.getArea()).multiply(minPricePerSquareMeter);
            assertAll(
                    () -> assertTrue(apartment.getArea() >= minArea),
                    () -> assertTrue(apartment.getArea() <= maxArea),
                    () -> assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0),
                    () -> assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0)
            );
        }

    }

}
