package com.southwind.mmall002.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall002.entity.User;
import com.southwind.mmall002.service.CartService;
import com.southwind.mmall002.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 景德
 * @since 2021-10-06
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @PostMapping("/register")
    public  String register(User user, Model model){

        boolean flag = false;
        try {
            flag = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",e.getClass().getSimpleName());
            return "register";
        }
        if (flag==true){
                return "login";
            }
            return "register";

    }

    @PostMapping("/login")
    public  String login(String loginName, String password, HttpSession session) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null) {
            return "login.html";
        } else {
            session.setAttribute("user", user);
            return "redirect:/productCategory/list";
        }
    }

   @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login.html";
    }

    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

