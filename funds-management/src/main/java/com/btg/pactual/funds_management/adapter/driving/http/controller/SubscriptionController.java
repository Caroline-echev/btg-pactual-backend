package com.btg.pactual.funds_management.adapter.driving.http.controller;

import com.btg.pactual.funds_management.adapter.driving.http.dto.request.SuscriptionRequest;
import com.btg.pactual.funds_management.domain.api.ISubscriptionServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final ISubscriptionServicePort subscriptionServicePort;

    @PostMapping("/")
    public void  subscribe(@RequestBody @Valid SuscriptionRequest suscriptionRequest) {
        subscriptionServicePort.subscribeToFund(suscriptionRequest.getUserId(), suscriptionRequest.getFundId(), suscriptionRequest.isSMS(), suscriptionRequest.getAmount());
    }

}
