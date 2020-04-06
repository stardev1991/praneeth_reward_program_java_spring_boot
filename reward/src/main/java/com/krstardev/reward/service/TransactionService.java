package com.krstardev.reward.service;

import com.krstardev.reward.dao.Transaction.TransactionDao;
import com.krstardev.reward.model.Reward;
import com.krstardev.reward.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(@Qualifier("fakeTransactions") TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionDao.insertTransaction(transaction);
    }

    public Transaction updateTransaction(Long id, Transaction update) {
        return transactionDao.updateTransaction(id, update);
    }

    public int deleteTransactionById(Long id) {
        return transactionDao.deleteTransactionById(id);
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionDao.selectTransactionById(id);
    }

    public List<Transaction> getTransactionByUserId(Long id) {
        return transactionDao.selectTransactionsByUserId(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.selectAllTransactions();
    }

    public double getPointsById(Long id) {
        return transactionDao.getPointsById(id).doubleValue();
    }

    public Reward getPointsByUserId(Long id) {
        return transactionDao.getPointsByUserId(id);
    }

}
