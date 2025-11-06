package com.example.demo.service;

import com.example.demo.model.SequenceData;
import com.example.demo.repository.SequenceDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SequenceServiceImpl implements SequenceService {
    
    private final SequenceDataRepository repository;
    private final AtomicLong sequenceCounter;
    
    public SequenceServiceImpl(SequenceDataRepository repository) {
        this.repository = repository;
        this.sequenceCounter = new AtomicLong(0);
    }
    
    @Override
    public SequenceData generateNext() {
        long nextSequence = sequenceCounter.incrementAndGet();
        String randomString = generateUniqueString();
        
        SequenceData sequenceData = new SequenceData(nextSequence, randomString);
        repository.save(sequenceData);
        
        return sequenceData;
    }
    
    @Override
    public Optional<SequenceData> getBySequenceNumber(Long sequenceNumber) {
        return repository.findBySequenceNumber(sequenceNumber);
    }
    
    @Override
    public List<SequenceData> getAllSequenceData() {
        return repository.findAll();
    }
    
    @Override
    public long getCount() {
        return repository.count();
    }
    
    @Override
    public void reset() {
        sequenceCounter.set(0);
        repository.clear();
    }
    
    private String generateUniqueString() {
        return UUID.randomUUID().toString();
    }
}
