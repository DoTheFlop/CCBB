package com.D104.ccbb.goods.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.D104.ccbb.goods.domain.Goods;

public interface GoodsRepo extends JpaRepository<Goods, Integer> {
}