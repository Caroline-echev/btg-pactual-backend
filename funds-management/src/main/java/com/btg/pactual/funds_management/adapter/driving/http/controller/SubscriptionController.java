package com.btg.pactual.funds_management.adapter.driving.http.controller;

import com.btg.pactual.funds_management.adapter.driving.http.dto.request.SubscriptionRequest;
import com.btg.pactual.funds_management.adapter.driving.http.dto.request.UnsubscribeRequest;
import com.btg.pactual.funds_management.adapter.driving.http.mapper.IFundDtoMapper;
import com.btg.pactual.funds_management.domain.api.ISubscriptionServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriptions")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SubscriptionController {
    private final ISubscriptionServicePort subscriptionServicePort;
    private final IFundDtoMapper fundDtoMapper;

    @PostMapping("/subscribe")
    public ResponseEntity subscribe(@RequestBody @Valid SubscriptionRequest suscriptionRequest) {
        subscriptionServicePort.subscribeToFund(suscriptionRequest.getUserId(), suscriptionRequest.getFundId(), suscriptionRequest.getIsSMS(), suscriptionRequest.getAmount());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity unsubscribe(@RequestBody @Valid UnsubscribeRequest unsubscribeRequest) {
        subscriptionServicePort.unsubscribeToFund(unsubscribeRequest.getUserId(), unsubscribeRequest.getFundId(), unsubscribeRequest.isSMS());
        return ResponseEntity.ok().build();
    }


}
