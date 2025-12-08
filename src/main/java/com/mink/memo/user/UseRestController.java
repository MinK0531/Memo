package com.mink.memo.user;

import com.mink.memo.user.domain.User;
import com.mink.memo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @PostMapping("/login-process")
    public Map<String,String> login(
            @RequestParam String loginId,
            @RequestParam String password,
            HttpServletRequest request){
        // 사용자 정보 얻어오기
        User user = userService.getUser(loginId,password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null){
            resultMap.put("result", "success");
            //세션에 사용자 정보 저장
            // 요청한 대상 클라이언트에 대응되는 세션 다루는 객체
            HttpSession session = request.getSession();
            // 세션은 해당 클라이언트의 요청을 손쉽게 사용기능
            // 요청마자 자주 사용되는 사용자 정보기 있다면 저장
            // user PK, 이름
            session.setAttribute("userId",user.getId());
            session.setAttribute("userName",user.getName());

        }else {
            resultMap.put("result", "fail");
        }

        return resultMap;
    }
}
