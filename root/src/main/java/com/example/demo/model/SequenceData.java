package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class SequenceData {
    
    private final Long sequenceNumber;
    private final String randomString;
    private final LocalDateTime timestamp;
    
    public SequenceData(Long sequenceNumber, String randomString) {
        this.sequenceNumber = sequenceNumber;
        this.randomString = randomString;
        this.timestamp = LocalDateTime.now();
    }
    
    public Long getSequenceNumber() {
        return sequenceNumber;
    }
    
    public String getRandomString() {
        return randomString;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SequenceData that = (SequenceData) o;
        return Objects.equals(sequenceNumber, that.sequenceNumber);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(sequenceNumber);
    }
    
    @Override
    public String toString() {
        return "SequenceData{" +
                "sequenceNumber=" + sequenceNumber +
                ", randomString='" + randomString + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
