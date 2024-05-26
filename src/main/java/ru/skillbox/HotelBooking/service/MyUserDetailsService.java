package ru.skillbox.HotelBooking.service;

import ru.skillbox.HotelBooking.config.MyUserDetails;
import ru.skillbox.HotelBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "не найден."));
    }
}
