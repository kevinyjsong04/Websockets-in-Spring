package com.example.demo.controller;

import com.example.demo.model.SequenceData;
import com.example.demo.service.SequenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sequences")
public class SequenceController {
    
    private final SequenceService sequenceService;
    
    public SequenceController(SequenceService sequenceService) {
        this.sequenceService = sequenceService;
    }
    
    @GetMapping
    public ResponseEntity<List<SequenceData>> getAllSequences() {
        List<SequenceData> sequences = sequenceService.getAllSequenceData();
        return ResponseEntity.ok(sequences);
    }
    
    @GetMapping("/{sequenceNumber}")
    public ResponseEntity<SequenceData> getSequenceByNumber(@PathVariable Long sequenceNumber) {
        return sequenceService.getBySequenceNumber(sequenceNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        long count = sequenceService.getCount();
        return ResponseEntity.ok(count);
    }
    
    @DeleteMapping
    public ResponseEntity<Void> reset() {
        sequenceService.reset();
        return ResponseEntity.noContent().build();
    }
}
