package com.example.admin.controller.api;

import com.example.admin.ifs.CrudInterface;
import com.example.admin.model.network.Header;
import com.example.admin.model.network.request.ItemApiRequest;
import com.example.admin.model.network.response.ItemApiResponse;
import com.example.admin.service.ItemApiLogicService;
import com.example.admin.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@Slf4j
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")  // /api/item
    public Header<ItemApiResponse> create(@RequestBody  Header<ItemApiRequest> request) {
        log.info("{}",request);
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")  // /api/item/1 .... 1000
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("id : {}",id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")    // /api/item
    public Header<ItemApiResponse> update(@RequestBody  Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/item/1 .... 1000
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
