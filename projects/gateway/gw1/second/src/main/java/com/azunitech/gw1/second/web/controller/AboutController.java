package com.azunitech.gw1.second.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AboutController {
  @GetMapping("/about")
  public String about() {
    return "This is first service";
  }
}
