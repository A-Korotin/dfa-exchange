package com.dfa.dfaserver.invest.mvc;

import com.dfa.dfaserver.invest.controller.order.LimitOrderController;
import com.dfa.dfaserver.invest.domain.order.BuyLimitOrder;
import com.dfa.dfaserver.invest.domain.order.LimitOrder;
import com.dfa.dfaserver.invest.service.order.LimitOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LimitOrderMvcTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LimitOrderService limitOrderService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void positiveGetOrderTest() throws Exception {
        UUID id = UUID.randomUUID();
        LimitOrder order = new BuyLimitOrder();
        order.setId(id);
        order.setPrice(1);

        given(limitOrderService.findById(id)).willReturn(Optional.of(order));

        mvc.perform(get("/api/v1/orders/limit/"+id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andDo(print());
    }

    @Test
    public void negativeGetOrderTest() throws Exception {
        UUID id = UUID.randomUUID();

        given(limitOrderService.findById(id)).willReturn(Optional.empty());

        mvc.perform(get("/api/v1/orders/limit/"+id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andDo(print());
    }

}
