package ru.skillbox.HotelBooking.controller;

import ru.skillbox.HotelBooking.service.BookingStatisticService;
import ru.skillbox.HotelBooking.service.StatisticService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/api/v1/statistic/booking")
@RequiredArgsConstructor
public class BookingStatisticController extends StatisticController
{
    protected final BookingStatisticService bookingStatisticService;

    @Override
    @GetMapping
    public void downloadStatistic(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info("был вызван метод GET /api/v1/statistic/booking");
        super.downloadStatistic(response);
    }

    @Override
    protected StatisticService<?, ?> getUserStatisticService() {
        return bookingStatisticService;
    }

    @Override
    protected String getFileName() {
        return "booking.csv";
    }
}
