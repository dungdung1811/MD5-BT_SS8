package com.ra.service;

import com.ra.dto.reponse.UserReponseDTO;
import com.ra.entity.Orders;
import com.ra.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(Integer id);
    List<UserReponseDTO> findAll();
    Page<User> getpagiNation(Pageable pageable);


}
