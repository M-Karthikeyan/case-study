package com.redsky.productservice.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.redsky.productservice.domain.Price;


@Repository
public interface PriceRepository extends CassandraRepository<Price, Long> {

}
