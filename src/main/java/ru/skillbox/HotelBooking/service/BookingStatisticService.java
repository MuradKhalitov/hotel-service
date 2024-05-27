package ru.skillbox.HotelBooking.service;

import ru.skillbox.HotelBooking.dto.event.NewBookingEvent;
import ru.skillbox.HotelBooking.model.BookingStatistic;
import ru.skillbox.HotelBooking.repository.BookingStatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingStatisticService extends StatisticService<BookingStatistic, String>
{
    private final BookingStatisticRepository bookingStatisticRepository;

    @Override
    protected List<BookingStatistic> findAll() {
        return bookingStatisticRepository.findAll();
    }

    @KafkaListener(
            topics = "${app.kafka.kafkaMessageNewBookingTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
    )
    public void listen(@Payload NewBookingEvent event) {
        log.info("Received message: {}", event);
        BookingStatistic bookingStatistic = BookingStatistic.builder()
                .id(UUID.randomUUID().toString())
                .userId(event.getUserId())
                .arrivalDate(event.getArrivalDate())
                .departureDate(event.getDepartureDate())
                .build();
        bookingStatisticRepository.save(bookingStatistic);
    }
}
