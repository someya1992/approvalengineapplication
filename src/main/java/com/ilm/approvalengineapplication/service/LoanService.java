package com.ilm.approvalengineapplication.service;

import com.ilm.approvalengineapplication.proxy.CreditScoringClient;
import com.ilm.approvalengineapplication.proxy.DocumentManagementClient;
import com.ilm.approvalengineapplication.proxy.LoanApplicationClient;
import com.ilm.approvalengineapplication.response.CreditScoreResponse;
import com.ilm.approvalengineapplication.response.DocumentResponse;
import com.ilm.approvalengineapplication.response.LoanApplicationResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public
class LoanService {
    private final LoanApplicationClient loanApplicationClient;
    private final CreditScoringClient creditScoringClient;
    private final DocumentManagementClient documentManagementClient;

    public LoanService(LoanApplicationClient loanApplicationClient, CreditScoringClient creditScoringClient, DocumentManagementClient documentManagementClient) {
        this.loanApplicationClient = loanApplicationClient;
        this.creditScoringClient = creditScoringClient;
        this.documentManagementClient = documentManagementClient;
    }

    @CircuitBreaker(name = "loanService", fallbackMethod = "fallbackLoanApplication")
    public LoanApplicationResponse getLoanApplication(Long loanId) {
        return loanApplicationClient.getLoanApplication(loanId);
    }

    @CircuitBreaker(name = "creditService", fallbackMethod = "fallbackCreditScore")
    public CreditScoreResponse getCreditScore(Long loanId) {
        return creditScoringClient.getCreditScore(loanId);
    }

    @CircuitBreaker(name = "documentService", fallbackMethod = "fallbackDocuments")
    public DocumentResponse getDocuments(Long loanId) {
        return documentManagementClient.getDocuments(loanId);
    }

    public LoanApplicationResponse fallbackLoanApplication(Long loanId, Throwable t) {
        log.error("LoanApplicationService failed for loanId: {}", loanId, t);
        LoanApplicationResponse defaultResponse = new LoanApplicationResponse();
        defaultResponse.setId(loanId);
        defaultResponse.setAge(18);
        defaultResponse.setIncome(50000.0);
        defaultResponse.setHomeOwnership("RENT");
        defaultResponse.setEmpLength(1);
        defaultResponse.setLoanIntent("Personal");
        defaultResponse.setLoanGrade("C");
        defaultResponse.setLoanAmount(10000.0);
        defaultResponse.setLoanInterestRate(15.0);
        defaultResponse.setLoanPercentIncome(20.0);
        defaultResponse.setPersonDefaultInFile(false);
        defaultResponse.setPersonCreditHistLength(2);
        return defaultResponse;
    }

    public CreditScoreResponse fallbackCreditScore(Long loanId, Throwable t) {
        log.error("CreditScoringService failed for loanId: {}", loanId, t);
        CreditScoreResponse defaultResponse = new CreditScoreResponse();
        defaultResponse.setLoanId(loanId);
        defaultResponse.setCibilScore(600);
        defaultResponse.setEquifaxScore(620);
        defaultResponse.setExperianScore(580);
        defaultResponse.setConsolidatedCreditScore(600);
        return defaultResponse;
    }

    public DocumentResponse fallbackDocuments(Long loanId, Throwable t) {
        log.error("DocumentManagementService failed for loanId: {}", loanId, t);
        DocumentResponse defaultResponse = new DocumentResponse();
        defaultResponse.setLoanId(loanId);
        defaultResponse.setDocumentsUploaded(false);
        return defaultResponse;
    }

}