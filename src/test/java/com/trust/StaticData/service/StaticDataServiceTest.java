package com.trust.StaticData.service;

import com.trust.StaticData.model.Cruise;
import com.trust.StaticData.repository.IStaticDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaticDataServiceTest {
    @InjectMocks
    private StaticDataServiceImpl serviceImpl;
    @Mock
    private IStaticDataRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBook() {
        Cruise cruise = new Cruise();
        cruise.setId(1);
        cruise.setName("Test Cruise");
        when(repository.save(cruise)).thenReturn(cruise);
        Cruise result = serviceImpl.saveBook(cruise);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Cruise", result.getName());
        verify(repository, times(1)).save(cruise);
    }

    @Test
    void testGetAllBooking() {
        Cruise c1 = new Cruise();
        c1.setId(1);
        c1.setName("Cruise A");
        Cruise c2 = new Cruise();
        c2.setId(2);
        c2.setName("Cruise B");
        List<Cruise> cruises = Arrays.asList(c1, c2);
        Page<Cruise> cruisePage = new PageImpl<>(cruises);
        PageRequest pageable = PageRequest.of(0, 10);
      //  when(repository.findAll(pageable)).thenReturn(cruisePage);
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(cruises));
        List<Cruise> result = serviceImpl.getAllBooking(0, 10);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c2));
     //   verify(repository, times(1)).findAll(pageable);
        verify(repository, times(1)).findAll(any(Pageable.class));
    }
}



