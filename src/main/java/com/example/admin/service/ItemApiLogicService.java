package com.example.admin.service;

import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.entity.Item;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.ItemApiRequest;
import com.example.admin.model.network.response.ItemApiResponse;
import com.example.admin.repository.ItemRepository;
import com.example.admin.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {

        ItemApiRequest body = request.getData();

        Item item = Item.builder()
                .status(body.getStatus())
                .name(body.getName())
                .title(body.getTitle())
                .content(body.getContent())
                .brandName(body.getBrandName())
                .price(body.getPrice())
                .registeredAt(body.getRegisteredAt())
                .partner(partnerRepository.getOne(body.getPartnerId()))
                .build();

        Item newItem = itemRepository.save(item);

        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

     return itemRepository.findById(id)
             .map(item -> response(item))
             .orElseGet(()->Header.ERROR("데이터없음"));

    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {

        ItemApiRequest itemApiRequest = request.getData();

       return itemRepository.findById(itemApiRequest.getId())
                .map(item -> {
                    item.setName(itemApiRequest.getName())
                            .setTitle(itemApiRequest.getTitle())
                            .setContent(itemApiRequest.getContent())
                            .setStatus(itemApiRequest.getStatus())
                            .setBrandName(itemApiRequest.getBrandName())
                            .setRegisteredAt(itemApiRequest.getRegisteredAt())
                            .setUnregisteredAt(itemApiRequest.getUnregisteredAt())
                            .setPrice(itemApiRequest.getPrice())
                            ;
                    return item;
                })
                .map(newItem->itemRepository.save(newItem)                )
                .map(item -> response(item))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return itemRepository.findById(id)
            .map(item -> {
                        itemRepository.delete(item);
                        return Header.OK();
        })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header<ItemApiResponse> response(Item item)
    {
        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .brandName(item.getBrandName())
                .status(item.getStatus())
                .price(item.getPrice())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();

        return Header.OK(itemApiResponse);

    }
}
