package com.ilm.approvalengineapplication.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public
class LoanApplicationResponse {
    private Long id;
    private int age;
    private double income;
    private String homeOwnership;
    private int empLength;
    private String loanIntent;
    private String loanGrade;
    private double loanAmount;
    private double loanInterestRate;
    private double loanPercentIncome;
    private boolean personDefaultInFile;
    private int personCreditHistLength;
    public double getIncome() { return income; }
    public int getAge() { return age; }
    public boolean isPersonDefaultInFile() { return personDefaultInFile; }
    public double getLoanAmount() { return loanAmount; }
    // Getters and Setters
}