package realestateapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomApartmentGeneratorTest {



    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
        //given
        RandomApartmentGenerator randomApartmentGenerator = new RandomApartmentGenerator();
        double minArea = 30.0;
        double maxArea = minArea * 4.0;
        BigDecimal minPrice = new BigDecimal(3000.0).multiply(new BigDecimal(minArea));
        BigDecimal maxPrice = new BigDecimal(maxArea).multiply(new BigDecimal(4.0)).multiply(new BigDecimal(3000));

        //when
        Apartment apartment = randomApartmentGenerator.generate();

        //then
        assertAll(
                () -> {
                    assertTrue(minArea <= apartment.getArea() && maxArea >= apartment.getArea());
                },
                () -> {
                    assertTrue(minPrice.compareTo(apartment.getPrice()) <= 0
                            && maxPrice.compareTo(apartment.getPrice()) >= 0);
                }
        );

    }

    @RepeatedTest(10)
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {

        //given
        double minArea = 50.0;
        BigDecimal minPricePerSquareMeter =new BigDecimal(5000.0);


        RandomApartmentGenerator randomApartmentGenerator = new RandomApartmentGenerator(minArea, minPricePerSquareMeter);

        double maxArea = minArea * 4.0;
        BigDecimal minPrice = minPricePerSquareMeter.multiply(new BigDecimal(minArea));
        BigDecimal maxPrice = new BigDecimal(maxArea).multiply(new BigDecimal(4.0)).multiply(minPricePerSquareMeter);

        //when
        Apartment apartment = randomApartmentGenerator.generate();

        //then
        assertAll(
                () -> {
                    assertTrue(minArea <= apartment.getArea() && maxArea >= apartment.getArea());
                },
                () -> {
                    assertTrue(minPrice.compareTo(apartment.getPrice()) <= 0
                            && maxPrice.compareTo(apartment.getPrice()) >= 0);
                }
        );

    }

}
