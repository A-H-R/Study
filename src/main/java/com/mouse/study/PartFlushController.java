package com.mouse.study;
/*
 *created by mouse on 2020/2/28
 */


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Controller
public class PartFlushController {

    @GetMapping("/partflush")
    public String toPartFlush(Model model){
        model.addAttribute("message", "初始化");
        model.addAttribute("myword", "我也不知道说啥");
        return "partFlush";
    }
    @GetMapping("/part1Flush")
    public String flushPart1(Model model){
        //  通过model返回前端刷新需要的信息
        model.addAttribute("message", "我第一部分刷新成功了");
        model.addAttribute("myword", "我想让我的东西成功");
        return "partFlush :: part";
    }


}
