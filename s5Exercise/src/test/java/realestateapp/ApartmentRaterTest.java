package realestateapp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApartmentRaterTest {

    @ParameterizedTest(name = "area = {0}, price = {1}, expected = {2}")
    @CsvSource(value = {"0.0, 20000.0, -1", "86.0, 430000.5, 0", "90.0, 675000.5, 1", "50.0, 450000.5, 2"})
    void shouldReturnCorrectRating_When_CorrectApartment(Double area, BigDecimal price, Integer rating){
        //given
        Apartment apartment = new Apartment(area, price);
        int expected = rating;

        //when
        int actual = ApartmentRater.rateApartment(apartment);

        //then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("no area apt")
    void should_ReturnErrorValue_When_IncorrectApartment(){
        //given
        Apartment apartment = new Apartment(0.0, new BigDecimal(20000.0));
        int expected = -1;

        //when
        int actual = ApartmentRater.rateApartment(apartment);

        //then
        assertEquals(expected, actual);

    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList(){
        //given
        List<Apartment> apartmentList = List.of(
                new Apartment(86.0, new BigDecimal(430000.5)),
                new Apartment(90.0, new BigDecimal(675000.5)),
                new Apartment(50.0, new BigDecimal(450000.5)));

        double expected = 1.0;

        //when
        double actual = ApartmentRater.calculateAverageRating(apartmentList);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList(){
        //given
        List<Apartment> apartmentList = new ArrayList<>();

        //when
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartmentList);

        //then
        assertThrows(RuntimeException.class, executable);
    }

}
