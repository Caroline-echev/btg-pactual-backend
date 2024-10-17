package com.btg.pactual.funds_management.adapter.driving.http.controller;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.FundResponse;
import com.btg.pactual.funds_management.adapter.driving.http.dto.response.SubscriptionResponse;
import com.btg.pactual.funds_management.adapter.driving.http.mapper.ISubscriptionDtoMapper;
import com.btg.pactual.funds_management.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {
    private final IUserServicePort userServicePort;
    private final ISubscriptionDtoMapper subscriptionDtoMapper;

    @GetMapping
    public ResponseEntity<List<SubscriptionResponse>> getSubscriptionsByUserId(@RequestParam String userId) {
        List<SubscriptionResponse> subscriptionResponses = userServicePort.getSubscriptionsByUserId(userId).stream()
                .map(subscriptionDtoMapper::mapToDtoResponse)
                .toList();
        return ResponseEntity.ok(subscriptionResponses);
    }
}
