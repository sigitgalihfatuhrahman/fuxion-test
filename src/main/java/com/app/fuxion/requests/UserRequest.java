package com.app.fuxion.requests;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String address;
    private Integer exported;
}
