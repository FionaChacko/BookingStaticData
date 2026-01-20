package com.trust.StaticData.service;

import com.trust.StaticData.model.Cruise;
import com.trust.StaticData.repository.IStaticDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
@Slf4j
public class StaticDataServiceImpl implements IStaticDataService {
   @Autowired
    IStaticDataRepository staticDataRepository;

    @Override
    public Cruise saveBook(Cruise request) {
        return staticDataRepository.save(request);
    }

    @Override
    public List<Cruise> getAllBooking(int page, int size) {
        log.info("BookingService getAllBooking method starts");
        PageRequest pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Cruise> userPage = staticDataRepository.findAll(pageable);
        return new ArrayList<>(userPage.getContent());

    }
}
