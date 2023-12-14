package com.example.roomreservations;

import com.example.roomreservations.model.Guest;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.model.Room;
import com.example.roomreservations.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableCaching

public class RoomreservationsApplication {
    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }



    public static void main(String[] args) {
       // SpringApplication.run(RoomreservationsApplication.class, args);
        ApplicationContext context = SpringApplication.run(RoomreservationsApplication.class, args);






    }


}
