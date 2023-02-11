package com.mousam.todo.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ItemRequest {
    private Long id;
    private String user_name;
    private String name;
    private Boolean is_active;
}
