package com.example.demo.repository;

import com.example.demo.model.SequenceData;

import java.util.List;
import java.util.Optional;

public interface SequenceDataRepository {
    
    void save(SequenceData sequenceData);
    
    Optional<SequenceData> findBySequenceNumber(Long sequenceNumber);
    
    List<SequenceData> findAll();
    
    long count();
    
    void clear();
}
