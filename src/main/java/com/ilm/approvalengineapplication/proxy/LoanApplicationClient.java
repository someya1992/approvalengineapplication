package com.ilm.approvalengineapplication.proxy;

import com.ilm.approvalengineapplication.response.LoanApplicationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "loan-app-service")
public
interface LoanApplicationClient {
    @GetMapping("/api/loans/{loanId}")
    LoanApplicationResponse getLoanApplication(@PathVariable("loanId") Long loanId);
}