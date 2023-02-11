package com.mousam.todo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AppUserRequest {
    private String name;
    private ItemRequest item_request;
}
