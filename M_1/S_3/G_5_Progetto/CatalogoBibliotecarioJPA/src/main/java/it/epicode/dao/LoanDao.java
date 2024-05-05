package it.epicode.dao;

import it.epicode.entities.Loan;

import java.util.List;

public interface LoanDao {

    void addLoan(Loan loan);
    List<Loan> getLoanByCardNumber(int card_number);
    List<Loan> getExpiredOrNotReturnedLoans();
}
