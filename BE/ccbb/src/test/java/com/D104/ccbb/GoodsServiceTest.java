// package com.D104.ccbb;
//
// import com.D104.ccbb.goods.controller.GoodsController;
// import com.D104.ccbb.goods.repo.GoodsRepo;
// import com.D104.ccbb.goods.service.GoodsService;
// import com.D104.ccbb.user.repository.UserRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Profile;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.transaction.annotation.Transactional;
//
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.TimeUnit;
//
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
// @ExtendWith(SpringExtension.class)
// @SpringBootTest
// @AutoConfigureMockMvc
// public class GoodsServiceTest {
//
// 	@Autowired
// 	private MockMvc mockMvc;
//
// 	@Autowired
// 	private GoodsController goodsController;
//
// 	@Autowired
// 	private GoodsService goodsService;
//
// 	@Autowired
// 	private GoodsRepo goodsRepo;
//
// 	@Autowired
// 	private UserRepository userRepository;
//
// 	@BeforeEach
// 	public void setup() {
// 		// 상품과 유저를 생성하고 초기화하는 코드를 여기에 작성하세요.
// 	}
//
// 	@Test
// 	@Transactional
// 	public void entryGoodsTest() throws Exception {
// 		ExecutorService service = Executors.newFixedThreadPool(10);
// 		for (int i = 0; i < 10; i++) {
// 			service.execute(() -> {
// 				try {
// 					MvcResult result = mockMvc.perform(post("/event/goods/entry")
// 							.contentType(MediaType.APPLICATION_JSON)
// 							.param("goodsId", "6")
// 							.header("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBY2Nlc3NUb2tlbiIsImVtYWlsIjoiY2JjaGFuNjQ0QG5hdmVyLmNvbSIsImV4cCI6MTY5ODkwOTg2NH0.rVUj5Pn7VggyDsoE1wTrC3KerpjX5PtnCGTYx-31j8DoV5sSNqmQNy054wiOi4nCvmMWg4Mc8iWA8kJGbtGsJQ"))
// 						.andDo(print())
// 						.andExpect(status().isOk())
// 						.andReturn();
//
// 					String content = result.getResponse().getContentAsString();
// 					System.out.println(content);
// 				} catch (Exception e) {
// 					e.printStackTrace();
// 				}
// 			});
// 		}
//
// 		service.shutdown();
// 		service.awaitTermination(60, TimeUnit.SECONDS);
// 	}
// }
