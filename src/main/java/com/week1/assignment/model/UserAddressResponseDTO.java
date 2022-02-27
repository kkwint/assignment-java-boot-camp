package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class UserAddressResponseDTO {

    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String postCode;
    private String district;
    private String province;

}
