package com.trust.StaticData.service;

import com.trust.StaticData.model.Cruise;

import java.util.List;

public interface IStaticDataService {
    Cruise saveBook(Cruise request);

    List<Cruise> getAllBooking(int page, int size);
}
