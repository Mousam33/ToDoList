package com.mousam.todo.services;

import com.mousam.todo.dto.AppUserRequest;
import com.mousam.todo.dto.ItemRequest;
import com.mousam.todo.models.AppUser;
import com.mousam.todo.models.AppUserRepo;
import com.mousam.todo.models.Item;
import com.mousam.todo.models.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepo appUserRepo;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemRepo itemRepo;

    public List<AppUser> getALl() {
        return appUserRepo.findAll();
    }

    public AppUser addAppUser(AppUserRequest appUserRequest) throws IllegalStateException {
        if(appUserRequest.getName().isBlank() || appUserRequest.getName().isEmpty())
            throw new IllegalStateException("Must provide username");
        AppUser appUser = new AppUser(appUserRequest.getName());
        appUserRepo.save(appUser);
        return appUser;
    }

    public AppUser addOrUpdateItem(AppUserRequest appUserRequest) throws IllegalStateException {
        AppUser appUser = appUserRepo.findById(appUserRequest.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        ItemRequest itemRequest = appUserRequest.getItem_request();
        if (!(itemRequest == null)) {
            itemRequest.setUser_name(appUser.getUser_name());
            Item item = itemService.addItem(itemRequest);
            if (!item.getName().equals(itemRequest.getName())) item.setName(itemRequest.getName());
            if (!item.getIs_active().equals(itemRequest.getIs_active()))
                item.setIs_active(itemRequest.getIs_active());
            itemRepo.save(item);
        }
        appUserRepo.save(appUser);
        return appUser;
    }

    public AppUser delItem(AppUserRequest appUserRequest) throws IllegalStateException {
        AppUser appUser = appUserRepo.findById(appUserRequest.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        ItemRequest itemRequest = appUserRequest.getItem_request();
        Item item = itemRepo.findById(itemRequest.getId())
                .orElseThrow(() -> new IllegalStateException("Item not found"));
        if (!(itemRequest == null) && item.getUser_name().equals(appUser.getUser_name())) itemRepo.delete(item);
        appUserRepo.save(appUser);
        return appUser;
    }

    public AppUser getItems(AppUserRequest appUserRequest) throws IllegalStateException {
        return appUserRepo.findById(appUserRequest.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

/*
    public Item findOrAddItem(ItemRequest itemRequest) {
        Item item = itemRepo.findById(itemRequest.getId()).orElse(itemService.addItem(itemRequest));
        if(!item.getName().equals(itemRequest.getName())) item.setName(itemRequest.getName());
        if(item.getIs_active() != itemRequest.getIs_active()) item.setIs_active(itemRequest.getIs_active());
        itemRepo.save(item);
        return item;
    }
*/
}
