package com.mink.memo.user;

import com.mink.memo.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController  //@Controller + @ResponseBody
public class UseRestController {

    private final UserService userService;


    public UseRestController(UserService userService){
        this.userService = userService;
    }

    //회원 가입API
    @PostMapping("/join-process")
    public Map<String,String> join(
            @RequestParam String loginId
            , @RequestParam String password
            , @RequestParam String name
            , @RequestParam String email){

        Map<String, String> resultMap = new HashMap<>();

        if(userService.createUser(loginId, password, name ,email)){
            resultMap.put("result", "success");
        }else {
            resultMap.put("result", "fail");
        }

        return resultMap;

    }
}
