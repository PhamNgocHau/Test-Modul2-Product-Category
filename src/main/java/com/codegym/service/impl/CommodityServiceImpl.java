package com.codegym.service.impl;

import com.codegym.model.Commodity;
import com.codegym.repository.CommodityRepository;
import com.codegym.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;

public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Iterable<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public Commodity findById(Long id) {
        return commodityRepository.findOne(id);
    }

    @Override
    public void save(Commodity commodity) {
        commodityRepository.save(commodity);
    }

    @Override
    public void remove(Long id) {
        commodityRepository.delete(id);
    }
}
