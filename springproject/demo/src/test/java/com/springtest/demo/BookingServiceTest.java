package com.springtest.demo;

import com.springtest.demo.model.BookingModel;
import com.springtest.demo.repository.BookingRepository;
import com.springtest.demo.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {
@TestConfiguration
    static class BookingServiceTestConfiguration{

        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;
    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "SzzS";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(days, 10);
    }

    @Before
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2023-09-19");
        LocalDate checkOut = LocalDate.parse("2023-09-29");
        BookingModel bookingModel = new BookingModel("1", "SzzS", checkIn, checkOut, 3);

        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName()))
                .thenReturn(Optional.of(bookingModel));
    }
}
