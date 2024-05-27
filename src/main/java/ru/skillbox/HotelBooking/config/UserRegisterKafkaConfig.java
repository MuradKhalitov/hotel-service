package ru.skillbox.HotelBooking.config;

import ru.skillbox.HotelBooking.dto.event.UserRegisterEvent;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRegisterKafkaConfig extends KafkaConfig<UserRegisterEvent>
{

}
