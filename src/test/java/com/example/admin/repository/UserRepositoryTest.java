package com.example.admin.repository;

import com.example.admin.model.entity.User;
import com.example.admin.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void  create() throws Exception{
        //given
        String account = "Test01";
        String password = "Test01";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "011-1111-2222";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String creatBy = "AdminServer";

//        User user = new User();
//        user.setAccount(account);
//        user.setPassword(password);
//        user.setPhoneNumber(phoneNumber);
//        user.setStatus(status);
//        user.setEmail(email);
//        user.setRegisteredAt(registeredAt);

        User user = User.builder()
                .account(account)
                .password(password)
                .phoneNumber(phoneNumber)
                .status(status)
                .email(email)
                .registeredAt(registeredAt)
                .build();
        //when
        User newUser = userRepository.save(user);

        //then
        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void  read() throws Exception{
        //given
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("011-1111-2222");
        //when
        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("---------------주문묶음-----------------");
                System.out.println("수령인 : " + orderGroup.getRevName());
                System.out.println("수령지 : " + orderGroup.getRevAddress());
                System.out.println("총금액 : " + orderGroup.getTotalPrice());
                System.out.println("총수량 : " + orderGroup.getTotalQuantity());


                System.out.println("---------------주문상세-----------------");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문상품 : " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문의 상태 : " + orderDetail.getStatus());
                    System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());

                });

            });
        }
        //then
        Assertions.assertNotNull(user);
    }
}