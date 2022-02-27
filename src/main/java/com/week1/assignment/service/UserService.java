package com.week1.assignment.service;

import com.week1.assignment.entity.User;
import com.week1.assignment.model.UserAddressResponseDTO;
import com.week1.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserAddressResponseDTO getUserAddress(String userName) {

        Optional<User> user = userRepository.findByUserName(userName);

        return transformToUserAddressDTO(user.get());
    }

    private UserAddressResponseDTO transformToUserAddressDTO(User user) {
        UserAddressResponseDTO userAddressResponse = new UserAddressResponseDTO();

        userAddressResponse.setId(user.getId());
        userAddressResponse.setUserName(user.getUserName());
        userAddressResponse.setFirstName(user.getFirstName());
        userAddressResponse.setLastName(user.getLastName());
        userAddressResponse.setPhone(user.getPhone());
        userAddressResponse.setEmail(user.getEmail());
        userAddressResponse.setAddress(user.getUserAddress().getAddress());
        userAddressResponse.setPostCode(user.getUserAddress().getPostCode());
        userAddressResponse.setDistrict(user.getUserAddress().getDistrict());
        userAddressResponse.setProvince(user.getUserAddress().getProvince());
        return userAddressResponse;
    }

}
