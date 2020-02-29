package com.example.admin.service;

import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.entity.OrderGroup;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.OrderGroupApiRequest;
import com.example.admin.model.network.response.OrderGroupApiResponse;
import com.example.admin.repository.OrderGroupRepository;
import com.example.admin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        OrderGroup newOrderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getOne(body.getId()))
                .build();

        OrderGroup saveOrderGroup = orderGroupRepository.save(newOrderGroup);
        return response(saveOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        return orderGroupRepository.findById(id)
                .map(this::response)
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        return orderGroupRepository.findById(body.getId()).map(orderGroup -> {
            orderGroup
                    .setStatus(body.getStatus())
                    .setOrderType(body.getOrderType())
                    .setRevAddress(body.getRevAddress())
                    .setRevName(body.getRevName())
                    .setPaymentType(body.getPaymentType())
                    .setTotalPrice(body.getTotalPrice())
                    .setTotalQuantity(body.getTotalQuantity())
                    .setOrderAt(body.getOrderAt())
                    .setArrivalDate(body.getArrivalDate())
                    .setUser(userRepository.getOne(body.getId()));
            return  orderGroup;
        })
                .map(changeOrderGroup -> orderGroupRepository.save(changeOrderGroup))
                .map(this::response)

                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                return Header.OK();
                }

        )
                .orElseGet(()->Header.ERROR("데이터 없음"));

    }

    public Header<OrderGroupApiResponse> response(OrderGroup orderGroup)
    {
        OrderGroupApiResponse data = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .build();

        return Header.OK(data);

    }
}
