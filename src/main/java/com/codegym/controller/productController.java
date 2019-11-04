package com.codegym.controller;

import com.codegym.model.Commodity;
import com.codegym.model.Product;
import com.codegym.service.CommodityService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class productController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CommodityService commodityService;

    @ModelAttribute("commodity")
    public Iterable<Commodity> commodities(){
        return commodityService.findAll();
    }

    @GetMapping("/product")
    public ModelAndView listProducts(@RequestParam("s") Optional<String> s,@PageableDefault(value = 3) Pageable pageable){
        Page<Product> products;
        if(s.isPresent()){
            products = productService.findAllByFirstNameContaining(s.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("product", products);
        return modelAndView;
    }

    @GetMapping("/create-product")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create-product")
    public ModelAndView saveCustomer(@ModelAttribute("product") Product product){
       productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Product product = productService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;


    }

    @PostMapping("/edit-product")
    public ModelAndView updateCustomer(@ModelAttribute("product") Product product){
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "product updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Product product= productService.findById(id);

            ModelAndView modelAndView = new ModelAndView("/product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;


    }

    @PostMapping("/delete-product")
    public String deleteCustomer(@ModelAttribute("product")Product product){
        productService.remove(product.getId());
        return "redirect:product";
    }
}
