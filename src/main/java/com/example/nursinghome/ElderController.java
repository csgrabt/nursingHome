package com.example.nursinghome;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/elders")
@RestController
public class ElderController {
    private ElderService elderService;


}
