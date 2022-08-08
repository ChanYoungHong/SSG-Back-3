package com.spharosacademy.project.SSGBack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/search/{searchWord}")
    String searchList(@PathVariable String searchWord){
        return null;
    }
}
