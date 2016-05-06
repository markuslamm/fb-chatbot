package io.chatbot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Controller
public final class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String clientListPage(final Model model) {
        model.addAttribute("serverTime", LocalDateTime.now());
        return "index";
    }
}
