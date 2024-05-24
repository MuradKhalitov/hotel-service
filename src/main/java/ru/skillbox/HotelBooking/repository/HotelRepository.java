package ru.skillbox.HotelBooking.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skillbox.HotelBooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
}
