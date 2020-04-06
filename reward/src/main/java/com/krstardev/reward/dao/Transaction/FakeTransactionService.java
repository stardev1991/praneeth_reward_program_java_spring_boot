package com.krstardev.reward.dao.Transaction;

import com.krstardev.reward.model.Reward;
import com.krstardev.reward.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Repository("fakeTransactions")
public class FakeTransactionService implements TransactionDao {

    private static long transaction_id = 0;
    private static List<Transaction> DB = new ArrayList<>();

    @Override
    public Transaction insertTransaction(Transaction transaction) {
        Transaction transactionToAdd = new Transaction(++transaction_id,
                transaction.getUser(), transaction.getPrice(), transaction.getDate());
        DB.add(transactionToAdd);
        return transactionToAdd;
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction update) {
        return selectTransactionById(id)
                .map(transaction -> {
                    int indexOfTransactionToUpdate = DB.indexOf(transaction);
                    Transaction transactionToUpdate = new Transaction(id, update.getUser(), update.getPrice(), update.getDate());
                    DB.set(indexOfTransactionToUpdate, transactionToUpdate);
                    return transactionToUpdate;
                })
                .orElse(null);
    }

    @Override
    public int deleteTransactionById(Long id) {
        Optional<Transaction> transaction = selectTransactionById(id);
        if(!transaction.isPresent()) {
            return 0;
        }

        DB.remove(transaction.get());
        return 1;
    }

    @Override
    public Optional<Transaction> selectTransactionById(Long id) {
        return DB.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        return DB;
    }

    @Override
    public List<Transaction> selectTransactionsByUserId(Long id) {
        return DB.stream()
                .filter(transaction -> transaction.getUser() == id)
                .collect(Collectors.toList());
    }

    @Override
    public Number getPointsById(Long id) {
        return selectTransactionById(id)
                .map(transaction -> getPoint(transaction))
                .orElse(0);
    }

    @Override
    public Reward getPointsByUserId(Long id) {

        AtomicReference<Double> month1 = new AtomicReference<>((double) 0);
        AtomicReference<Double> month2 = new AtomicReference<>((double) 0);
        AtomicReference<Double> month3 = new AtomicReference<>((double) 0);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        DB.stream()
                .filter(transaction -> transaction.getUser().equals(id))
                .forEach(transaction -> {
                    cal.setTime(transaction.getDate());

                    double point = getPoint(transaction).doubleValue();

                    if(year == cal.get(Calendar.YEAR)) {
                        if(month == cal.get(Calendar.MONTH)) {
                            month1.set(month1.get() + point);
                        } else if(month == (cal.get(Calendar.MONTH) + 1)) {
                            month2.set(month2.get() + point);
                        } else if(month == (cal.get(Calendar.MONTH) + 2)) {
                            month3.set(month3.get() + point);
                        }
                    }
                });

        return new Reward(month1.get(), month2.get(), month3.get(), id);
    }

    Number getPoint(Transaction transaction) {
        double price = transaction.getPrice();
        if(price < 50) {
            return 0;
        } else if(price < 100) {
            return price - 50;
        } else {
            return ((price - 100) * 2 + 50);
        }
    }

}
