package com.krstardev.reward.api;

import com.krstardev.reward.model.Transaction;
import com.krstardev.reward.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction addTransaction(@Valid @NotNull @RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping(path = "{id}")
    public Transaction getTransactionById(@PathVariable("id") Long id) {
        return transactionService.getTransactionById(id)
                .orElse(null);
    }

    @GetMapping(path = "user/{id}")
    public List<Transaction> getTransactionByUserId(@PathVariable("id") Long id) {
        return transactionService.getTransactionByUserId(id);
    }

    @DeleteMapping(path = "{id}")
    public String deleteTransaction(@PathVariable("id") Long id) {
        if(transactionService.deleteTransactionById(id) > 0) {
            return "Success";
        }
        return "Failure";
    }

    @PutMapping(path = "{id}")
    public Transaction updateTransaction(@PathVariable("id") Long id,
                                  @Valid @NotNull  @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

}
