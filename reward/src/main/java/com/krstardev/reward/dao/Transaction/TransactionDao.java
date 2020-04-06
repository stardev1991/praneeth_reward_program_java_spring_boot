package com.krstardev.reward.dao.Transaction;

import com.krstardev.reward.model.Reward;
import com.krstardev.reward.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {

    Transaction insertTransaction(Transaction transaction);

    Transaction updateTransaction(Long id, Transaction transaction);

    int deleteTransactionById(Long id);

    Optional<Transaction> selectTransactionById(Long id);

    List<Transaction> selectAllTransactions();

    List<Transaction> selectTransactionsByUserId(Long id);

    Number getPointsById(Long id);

    Reward getPointsByUserId(Long id);

}
