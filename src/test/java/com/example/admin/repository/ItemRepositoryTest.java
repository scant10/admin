package com.example.admin.repository;

import com.example.admin.model.entity.Item;
import com.example.admin.model.enumclass.ItemStatus;
import com.example.admin.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

  @Test
  public void  create() throws Exception{
      //given
      Item item = new Item();
      item.setStatus(ItemStatus.REGISTERED);
      item.setName("삼성 노트북");
      item.setTitle("삼성 노트북 A100");
      item.setContent("2019년형 노트북 입니다.");
      item.setPrice(BigDecimal.valueOf(90000));
      item.setBrandName("삼성");
      item.setRegisteredAt(LocalDateTime.now());
      item.setCreatedAt(LocalDateTime.now());
      item.setCreatedBy("Partner01");
   //   item.setPartnerId(1L);
      //when
      Item newItem = itemRepository.save(item);

      //then
      Assertions.assertNotNull(newItem);
  }


}