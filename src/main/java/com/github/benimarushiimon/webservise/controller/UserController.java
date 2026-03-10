package com.github.benimarushiimon.webservise.controller;

import com.github.benimarushiimon.webservise.service.UserService;
import com.github.benimarushiimon.webservise.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/add")
    public String showFormForAdd(Model model) {
        model.addAttribute("user", new UserDto());
        return "add-form";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserDto> userDtos = userService.findAllDto();
        model.addAttribute("users", userDtos);
        return "user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute UserDto userDto) {
        userService.create(userDto);
        return "redirect:/ui/users";
    }

    @GetMapping("/delete/id/{id}")
    public String deleteUserForId(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/ui/users";
    }
}
