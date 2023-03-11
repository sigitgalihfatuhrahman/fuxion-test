package com.app.fuxion.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String address;
    private Integer exported;
    private String fileName;
    private String documentFile;

    public UserDto(String name, String email, String address,Integer exported, String fileName, String documentFile) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.exported = exported;
        this.fileName = fileName;
        this.documentFile = documentFile;
    }

    public UserDto(String name, String email, String address, Integer exported) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.exported = exported;
    }
}
