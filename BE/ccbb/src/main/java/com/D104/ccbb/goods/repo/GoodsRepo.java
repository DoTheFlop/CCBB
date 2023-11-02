package com.D104.ccbb.goods.repo;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.D104.ccbb.goods.domain.Goods;

public interface GoodsRepo extends JpaRepository<Goods, Integer> {

	List<Goods> findByEventId_EventId(int eventId);

	@Lock(LockModeType.PESSIMISTIC_WRITE) // 비망락
	Optional<Goods> findGoodsByGoodsId(int goodsId);
	// 상품 ID를 입력받아 해당 상품을 찾고, 찾은 상품에 write lock
	// 을 걸어 다른 트랜잭션에서 해당 상품을 수정하는 것을 막는다.
	// 이를 통해 동시성 문제를 해결할 수 있습니다.

	// 한놈이 응모중이면 다른놈이 응모를 하지못함
	// 테스트 10개 동시에 보냈는데 다 됐으면 된거임

}
