package com.example.admin.repository;

import com.example.admin.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PartnerRepositoryTest {

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void  create() throws Exception{
        //given
        String name = "Partner01";
        String status = "REGISTERED";
        String address = "서울시 강남구";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createBy = "AdminServer";
        Long categoryId = 1L;
        //when
        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);
        partner.setCreatedAt(createdAt);
        partner.setCreatedBy(createBy);
      //  partner.setCategoryId(categoryId);

        Partner partner1 = partnerRepository.save(partner);
        //then
        Assertions.assertNotNull(partner1);
    }

    @Test
    public void  read() throws Exception{
        //given

        //when

        //then
    }
}