package com.example.demo.repository;

import com.example.demo.model.SequenceData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemorySequenceDataRepository implements SequenceDataRepository {
    
    private final ConcurrentHashMap<Long, SequenceData> dataStore = new ConcurrentHashMap<>();
    
    @Override
    public void save(SequenceData sequenceData) {
        if (sequenceData == null || sequenceData.getSequenceNumber() == null) {
            throw new IllegalArgumentException("SequenceData and sequence number cannot be null");
        }
        dataStore.put(sequenceData.getSequenceNumber(), sequenceData);
    }
    
    @Override
    public Optional<SequenceData> findBySequenceNumber(Long sequenceNumber) {
        if (sequenceNumber == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(dataStore.get(sequenceNumber));
    }
    
    @Override
    public List<SequenceData> findAll() {
        return new ArrayList<>(dataStore.values());
    }
    
    @Override
    public long count() {
        return dataStore.size();
    }
    
    @Override
    public void clear() {
        dataStore.clear();
    }
}
