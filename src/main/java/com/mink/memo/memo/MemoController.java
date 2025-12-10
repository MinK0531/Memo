package com.mink.memo.memo;

import com.mink.memo.memo.domain.Memo;
import com.mink.memo.memo.service.MemoService;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/memo")
@Controller
public class MemoController {

    private  final MemoService memoService;

    public MemoController(MemoService memoService){
        this.memoService = memoService;
    }

    @GetMapping("/list")
    public String list(
            Model model,
            HttpSession session){

        long userId = (Long)session.getAttribute("userId");

        List<Memo> memoList = memoService.getMemoList(userId);
        model.addAttribute("memoList",memoList);
        return "memo/list";
    }

    @GetMapping("/write")
    public String writeForm(){
        return "memo/form";
    }

    @GetMapping("/detail")
    public String detail(
            @RequestParam long id,
            Model model){

        Memo memo = memoService.getMemo(id);

        model.addAttribute("memo",memo);

        return "memo/detail";
    }





}
