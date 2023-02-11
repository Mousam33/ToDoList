package com.mousam.todo.services;

import com.mousam.todo.dto.ItemRequest;
import com.mousam.todo.models.Item;
import com.mousam.todo.models.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;

    public Item addItem(ItemRequest itemRequest) {
        Item item;
        if (itemRequest.getId() == null) {
            item = new Item(itemRequest.getUser_name(),
                    itemRequest.getName(),
                    itemRequest.getIs_active() == null ? Boolean.TRUE : itemRequest.getIs_active());
            itemRepo.save(item);
        } else {
            item = itemRepo.findById(itemRequest.getId())
                    .orElse(new Item(itemRequest.getUser_name(),
                            itemRequest.getName(),
                            itemRequest.getIs_active() == null ? Boolean.TRUE : itemRequest.getIs_active()));
        }
        return item;
    }

/*
    public List<Item> getAll() {
        return itemRepo.findAll();
    }

    public ResponseEntity<?> delItem(ItemRequest itemRequest) {
        try {
            Item item = itemRepo.findById(itemRequest.getId())
                    .orElseThrow(() -> new IllegalStateException("Item not found"));
            itemRepo.delete(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }


    public ResponseEntity<?> updateItem(ItemRequest itemRequest) {
        try {
            Item item = itemRepo.findById(itemRequest.getId())
                    .orElseThrow(() -> new IllegalStateException("Item not found"));
            item.setName(itemRequest.getName());
            item.setIs_active(itemRequest.getIs_active());
            itemRepo.save(item);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
        }
    }
*/
}
