package com.trust.StaticData.controller;

import com.trust.StaticData.model.Cruise;
import com.trust.StaticData.service.StaticDataServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaticDataControllerTest {
    @Mock
    private StaticDataServiceImpl serviceImpl;

    @InjectMocks
    private StaticDataController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateStaticData() {
        Cruise request = new Cruise();
        request.setId(1);
        request.setName("Test Cruise");
        Cruise saved = new Cruise();
        saved.setId(1);
        saved.setName("Test Cruise");
        when(serviceImpl.saveBook(request)).thenReturn(saved);
        ResponseEntity<Cruise> response = controller.createStaticData(request);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saved, response.getBody());
        verify(serviceImpl, times(1)).saveBook(request);
    }

    @Test
    void testGetAllBook() {
        Cruise c1 = new Cruise();
        c1.setId(1);
        c1.setName("Cruise A");
        Cruise c2 = new Cruise();
        c2.setId(2);
        c2.setName("Cruise B");
        List<Cruise> list = Arrays.asList(c1, c2);
        when(serviceImpl.getAllBooking(0, 10)).thenReturn(list);
        ResponseEntity<List<Cruise>> response = controller.getAllBook(0, 10);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(c1));
        assertTrue(response.getBody().contains(c2));
        verify(serviceImpl, times(1)).getAllBooking(0, 10);
    }
    }
