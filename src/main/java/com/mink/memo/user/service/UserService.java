package com.mink.memo.user.service;

import com.mink.memo.common.MD5HashingEncoder;
import com.mink.memo.user.repository.UserRepository;
import com.mink.memo.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // final 붙으면 변경 불가능 초기화 해야함
    private final UserRepository userRepository;

//    @Autowired
    // 클래스 내에 생성자가 의존성 주입을 위한 생성자가 유일한 경우 @Autowired 생략가능
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email){

        String encodedPassword = MD5HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId ,encodedPassword ,name, email);

        if(count == 1){
            return true;
        }else {
            return false;
        }

    }
    public User getUser(String loginId, String password){

        String encodedPassword = MD5HashingEncoder.encode(password);

        User user = userRepository.selectUser(loginId,encodedPassword);
        return user;

    }
}
