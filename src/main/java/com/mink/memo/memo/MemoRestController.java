package com.mink.memo.memo;

import com.mink.memo.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/memo")
@RestController
public class MemoRestController {

    private final MemoService memoService;

    public MemoRestController(MemoService memoService){
        this.memoService = memoService;
    }

    @PostMapping("write-process")
    public Map<String,String> write(
            @RequestParam String title,
            @RequestParam String contents,
            @RequestParam(required = false) MultipartFile imageFile,
            HttpSession session){

        // 로그인한 사용자의 PK
        long userId = (Long)session.getAttribute("userId");

        Map<String, String> resultMap = new HashMap<>();
        if(memoService.createMemo(userId, title,contents,imageFile)){
            resultMap.put("result", "success");
        }else {
            resultMap.put("result", "fail");
        }
        return resultMap;
    }
}
