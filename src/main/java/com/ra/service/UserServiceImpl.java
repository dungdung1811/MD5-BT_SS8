package com.ra.service;

import com.ra.dto.reponse.UserReponseDTO;
import com.ra.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findById(Integer id) {
        return null;
    }
    @Override
    public List<UserReponseDTO> findAll() {
        return userRepository.findAll().stream().map(user-> UserReponseDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .address(user.getAddress())
                .build()).toList();
    }
    @Override
    public Page<User> getpagiNation(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
