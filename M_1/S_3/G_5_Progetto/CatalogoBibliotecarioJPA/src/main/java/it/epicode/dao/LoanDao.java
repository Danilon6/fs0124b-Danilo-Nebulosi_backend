package it.epicode.dao;

import it.epicode.entities.Loan;

import java.util.List;

public interface LoanDao extends Dao {

    void addLoan(Loan loan);
    List<Loan> getAllLoans();
    List<Loan> getLoanByCardNumber(int card_number);
    List<Loan> getExpiredOrNotReturnedLoans();
}
