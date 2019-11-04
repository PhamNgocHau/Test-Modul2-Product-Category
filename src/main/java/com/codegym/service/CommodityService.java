package com.codegym.service;

import com.codegym.model.Commodity;

public interface CommodityService {
    Iterable<Commodity> findAll();

    Commodity findById(Long id);

    void save(Commodity commodity);

    void remove(Long id);
}
