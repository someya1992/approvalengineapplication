package com.ilm.approvalengineapplication.controller;

import com.ilm.approvalengineapplication.response.CreditScoreResponse;
import com.ilm.approvalengineapplication.response.DocumentResponse;
import com.ilm.approvalengineapplication.response.LoanApplicationResponse;
import com.ilm.approvalengineapplication.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/approval")
class ApprovalController {
    private final LoanService loanService;

    public ApprovalController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/approve-loan/{loanId}")
    public String approveLoan(@PathVariable Long loanId) {
        log.info("Processing approval for loanId: {}", loanId);

        LoanApplicationResponse loan = loanService.getLoanApplication(loanId);
        CreditScoreResponse creditScore = loanService.getCreditScore(loanId);
        DocumentResponse documents = loanService.getDocuments(loanId);

        boolean complianceCheck = loan.getAge() >= 18 && !loan.isPersonDefaultInFile();
        boolean regulatoryCheck = loan.getLoanAmount() <= loan.getIncome() * 10;
        boolean creditCheck = creditScore.getConsolidatedCreditScore() > 650;
        boolean documentCheck = documents.isDocumentsUploaded();

        boolean isApproved = complianceCheck && regulatoryCheck && creditCheck && documentCheck;
        
        String decision = isApproved ? "Approved" : "Rejected";
        log.info("Loan decision for loanId {}: {}", loanId, decision);
        return decision;
    }
}