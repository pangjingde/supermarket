package com.southwind.mmall002.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.mmall002.entity.Cart;
import com.southwind.mmall002.entity.Product;
import com.southwind.mmall002.entity.User;
import com.southwind.mmall002.mapper.CartMapper;
import com.southwind.mmall002.service.CartService;
import com.southwind.mmall002.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService addressService;

    @Autowired
    private CartMapper cartMapper;
    @GetMapping("/add/{productId}/{price}/{quantity}")
    public  String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ){
        ModelAndView modelAndView=new ModelAndView();

        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price*quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        boolean flag = cartService.save(cart);
        user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        if (flag==true){
            return "redirect:/cart/findAllCart";
        }else {
            return  "redirect:/productCategory/list";
        }


    }



    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User) session.getAttribute("user");
        modelAndView.addObject("list",cartService.findAllCartVOByUserId(user.getId()));
         user = (User) session.getAttribute("user");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;

    }

    @GetMapping("/deleteById/{id}")
    public  String deleteById(@PathVariable("id") Integer id){
        cartService.removeById(id);
        return "redirect:/cart/findAllCart";

    }

    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement2");
        User user = (User) session.getAttribute("user");;
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        modelAndView.addObject("addressList",addressService.list(wrapper));
        return modelAndView;
    }

    @PostMapping("/update/{id}/{quantity}/{cost}")
    @ResponseBody
    public  String  updateDate(@PathVariable("id") Integer id,
                              @PathVariable("quantity") Integer quantity,
                               @PathVariable("cost") Float cost){
        Cart cart = cartService.getById(id);
        cart.setQuantity(quantity);
        cart.setCost(cost);
        if(cartService.updateById(cart)){
            return "success";
        }else {
            return "fail";
        }


    }
}

