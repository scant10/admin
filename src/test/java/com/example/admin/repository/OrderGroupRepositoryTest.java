package com.example.admin.repository;

import com.example.admin.model.entity.OrderGroup;
import com.example.admin.model.enumclass.OrderType;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderGroupRepositoryTest {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void  create() throws Exception{
        //given
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setOrderType(OrderType.ALL);
        orderGroup.setRevAddress("서울시 강남구");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(90000));
        orderGroup.setTotalQuantity(1);
       // orderGroup.setUserId(1L);

        OrderGroup save = orderGroupRepository.save(orderGroup);
        //when
        Assertions.assertNotNull(save);
        //then
    }
}