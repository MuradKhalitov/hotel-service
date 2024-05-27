package ru.skillbox.HotelBooking.controller;

import ru.skillbox.HotelBooking.service.StatisticService;
import ru.skillbox.HotelBooking.service.UserStatisticService;
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
@RequestMapping("/api/v1/statistic/users")
@RequiredArgsConstructor
public class UserStatisticController extends StatisticController
{
    private final UserStatisticService userStatisticService;

    @Override
    @GetMapping
    public void downloadStatistic(HttpServletResponse response) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        log.info("был вызван метод GET /api/v1/statistic/booking");
        super.downloadStatistic(response);
    }

    protected StatisticService<?, ?> getUserStatisticService() {
        return userStatisticService;
    }

    @Override
    protected String getFileName() {
        return "users.csv";
    }
}
