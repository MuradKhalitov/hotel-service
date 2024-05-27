package ru.skillbox.HotelBooking.repository;

import ru.skillbox.HotelBooking.model.BookingStatistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookingStatisticRepository extends MongoRepository<BookingStatistic, String>
{
}
