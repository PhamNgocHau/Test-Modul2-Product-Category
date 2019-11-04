package com.codegym.repository;

import com.codegym.model.Commodity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommodityRepository extends PagingAndSortingRepository<Commodity, Long> {
}
