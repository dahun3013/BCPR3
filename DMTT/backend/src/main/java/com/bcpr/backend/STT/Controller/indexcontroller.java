package com.bcpr.backend.STT.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexcontroller {
    @GetMapping("/")
    public ModelAndView main(ModelAndView mav){
        mav.setViewName("index");
        return mav;
    }
}
