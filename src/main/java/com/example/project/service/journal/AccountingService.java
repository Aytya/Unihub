package com.example.project.service.journal;

import com.example.project.model.journal.FinanceCabinet;
import com.example.project.repository.journal.AccountingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountingService {

    public final AccountingRepository accountingRepository;

    public List<FinanceCabinet> getAllStudentsFeeRecord(){
        List<FinanceCabinet> financeCabinets = new ArrayList<>();
        financeCabinets.addAll((Collection<? extends FinanceCabinet>) accountingRepository.findAll());
        return financeCabinets;
    }


    public FinanceCabinet getById(@PathVariable Long id) {
        Optional<FinanceCabinet> financeCabinet = accountingRepository.findById(id);
        if (financeCabinet.isPresent()) {
            return financeCabinet.get();
        } else throw new RuntimeException("something went wrong");
    }

    public FinanceCabinet saveFinanceCabinet(FinanceCabinet financeCabinet) {
        return accountingRepository.save(financeCabinet);
    }
}
