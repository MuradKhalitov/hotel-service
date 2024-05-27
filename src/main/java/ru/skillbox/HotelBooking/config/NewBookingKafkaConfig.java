package ru.skillbox.HotelBooking.config;

import ru.skillbox.HotelBooking.dto.event.NewBookingEvent;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewBookingKafkaConfig extends KafkaConfig<NewBookingEvent>
{
}
