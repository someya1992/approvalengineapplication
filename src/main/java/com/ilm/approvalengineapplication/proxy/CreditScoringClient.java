package com.ilm.approvalengineapplication.proxy;

import com.ilm.approvalengineapplication.response.CreditScoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "credit-score-service")
public interface CreditScoringClient {
    @GetMapping("/credit-score/{loanId}")
    CreditScoreResponse getCreditScore(@PathVariable("loanId") Long loanId);
}