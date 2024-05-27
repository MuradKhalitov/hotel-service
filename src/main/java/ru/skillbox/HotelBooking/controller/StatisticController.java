package ru.skillbox.HotelBooking.controller;


import ru.skillbox.HotelBooking.exception.NoStatisticDataException;
import ru.skillbox.HotelBooking.service.StatisticService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public abstract class StatisticController
{
    public void downloadStatistic(HttpServletResponse response)
            throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        try {
            getUserStatisticService().download(response.getWriter());
            response.setContentType("text/csv");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + getFileName() + "\"");
        } catch (NoStatisticDataException e) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    protected abstract StatisticService<?, ?> getUserStatisticService();
    protected abstract String getFileName();
}
