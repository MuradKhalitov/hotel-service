package ru.skillbox.HotelBooking.repository;

import ru.skillbox.HotelBooking.model.UserStatistic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticRepository extends MongoRepository<UserStatistic, String>
{
}
