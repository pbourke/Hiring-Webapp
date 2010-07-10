package com.pb.hiring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LayoutController {
    @RequestMapping(method=RequestMethod.GET, value="/layout/{name}")
    public String getLayout(@PathVariable("name") final String name) {
        return "_layout/" + name;        
    }
}
