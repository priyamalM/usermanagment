package com.apps.usermanagment;

import lombok.Data;

import java.util.List;

@Data
public class PageableUserDto {
    public List<Integer> totalPages;
    public List<UserDto> userDtoList;
}
