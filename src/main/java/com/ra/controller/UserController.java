package com.ra.controller;
import com.ra.dto.reponse.UserReponseDTO;
import com.ra.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;
//    @GetMapping
//    public ResponseEntity<List<UserReponseDTO>> getAllUser(){
//        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> paginate(@RequestParam(defaultValue = "0")int page,
                                                         @RequestParam(defaultValue = "2")int limit){
        Pageable pageable = PageRequest.of(page,limit);
        Page<User> userPage = userService.getpagiNation(pageable);
        Map<String,Object> data= new HashMap<>();
        data.put("users",userPage.getContent());
        data.put("total",userPage.getSize());
        data.put("totalElement",userPage.getTotalElements());
        data.put("totalPages",userPage.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);
    }
}
