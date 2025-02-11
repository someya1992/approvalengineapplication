package com.ilm.approvalengineapplication.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class CreditScoreResponse {
    private Long loanId;
    private int cibilScore;
    private int equifaxScore;
    private int experianScore;
    private int consolidatedCreditScore;
    public int getConsolidatedCreditScore() { return consolidatedCreditScore; }
    // Getters and Setters
}
