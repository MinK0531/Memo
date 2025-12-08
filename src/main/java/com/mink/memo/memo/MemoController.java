package com.mink.memo.memo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/memo")
@Controller
public class MemoController {

    @GetMapping("/list")
    public String list(){
        return "memo/list";
    }



}
