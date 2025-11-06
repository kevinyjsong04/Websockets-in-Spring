package com.example.demo.service;

import com.example.demo.model.SequenceData;

import java.util.List;
import java.util.Optional;

public interface SequenceService {
    
    SequenceData generateNext();
    
    Optional<SequenceData> getBySequenceNumber(Long sequenceNumber);
    
    List<SequenceData> getAllSequenceData();
    
    long getCount();
    
    void reset();
}
