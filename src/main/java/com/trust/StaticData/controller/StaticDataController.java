package com.trust.StaticData.controller;

import com.trust.StaticData.model.Cruise;
import com.trust.StaticData.service.StaticDataServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/cruise")
public class StaticDataController {
    @Autowired
    StaticDataServiceImpl serviceImpl;
    @PostMapping
    public ResponseEntity<Cruise> createStaticData(@Valid @RequestBody Cruise request){
        log.info("Static controller: createBooking starts with request {}",request);
        Cruise response = serviceImpl.saveBook(request);
        log.info("Static Controller: createBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Cruise>> getAllBook(@RequestHeader(value = "X-Page-Number", defaultValue = "0") int page,
                                                         @RequestHeader(value = "X-Page-Size", defaultValue = "10") int size){
        log.info("Booking controller: getBooking starts for fetch all bookings");
        List<Cruise> response = serviceImpl.getAllBooking(page,size);
        log.info("BookingController: getAllBooking ends with response {}",response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
