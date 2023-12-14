package com.example.roomreservations.service;


import com.example.roomreservations.exception.NotEnoughMoneyException;
import com.example.roomreservations.model.Reservation;
import com.example.roomreservations.repository.ReservationRepository;
import com.example.roomreservations.request.DataForTransferRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
@NoArgsConstructor

public class TransactionService {

    private WebClient webClient;
    private ReservationRepository reservationRepository;


    @Autowired
    public TransactionService(WebClient webClient, ReservationRepository reservationRepository) {
        this.webClient = webClient;
        this.reservationRepository = reservationRepository;

    }

    public void deleteReservation(Long id,String accountNumber){
        Optional<Reservation> reservationFoundedById = reservationRepository.findById(id);
        Double balance = getAccountBalance(accountNumber);
        double amount = 0.2 * reservationFoundedById.orElseThrow().getPrice();
        String transactionType = "ONLINE_PAYMENT";

        if (balance == null || balance < amount)
        {
            throw new NotEnoughMoneyException();
        }

        DataForTransferRequest dataForTransferRequest = createDataForTransferRequest(accountNumber, amount, transactionType);
        executeTransaction(dataForTransferRequest);
        reservationRepository.deleteById(id);
    }

    private Double getAccountBalance(String accountNumber) {
        return webClient.get()
                .uri(builder -> UriComponentsBuilder.fromUriString("http://localhost:9090/accounts/balance")
                        .queryParam("accountNumber", accountNumber)
                        .build().toUri()
                )
                .retrieve()
                .bodyToMono(Double.class)
                .block();
    }

    private void executeTransaction(DataForTransferRequest dataForTransferRequest) {
        webClient.post().uri("http://roomreservations-bankservice-1:9090/transactions/roomreservationtransaction")
                .bodyValue(dataForTransferRequest)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    private static DataForTransferRequest createDataForTransferRequest(String accountNumber, double amount, String transactionType) {
        return new DataForTransferRequest(
                accountNumber,
                amount,
                transactionType);
    }

}
