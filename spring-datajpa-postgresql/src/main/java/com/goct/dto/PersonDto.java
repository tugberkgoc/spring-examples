package com.goct.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDto {

    private Long id;

    private String name;

    private String surname;

    private List<String> address;
}
