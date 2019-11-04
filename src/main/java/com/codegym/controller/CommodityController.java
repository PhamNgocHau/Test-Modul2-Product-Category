package com.codegym.controller;

import com.codegym.model.Commodity;
import com.codegym.service.CommodityService;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ProductService productService;

    @GetMapping("/commodity")
    public ModelAndView listCommodity(){
        Iterable<Commodity> commodities = commodityService.findAll();
        ModelAndView modelAndView = new ModelAndView("/commodity/list");
        modelAndView.addObject("commodity", commodities);
        return modelAndView;
    }

    @GetMapping("/create-commodity")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/commodity/create");
        modelAndView.addObject("commodity", new Commodity());
        return modelAndView;
    }

    @PostMapping("/create-commodity")
    public ModelAndView saveProvince(@ModelAttribute("commodity") Commodity commodity){
        commodityService.save(commodity);

        ModelAndView modelAndView = new ModelAndView("/commodity/create");
        modelAndView.addObject("commodity", new Commodity());
        modelAndView.addObject("message", "New commodity created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-commodity/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Commodity commodity=commodityService.findById(id);

            ModelAndView modelAndView = new ModelAndView("/commodity/edit");
            modelAndView.addObject("commodity", commodity);
            return modelAndView;

    }

    @PostMapping("/edit-commodity")
    public ModelAndView updateProvince(@ModelAttribute("commodity") Commodity commodity){
        commodityService.save(commodity);
        ModelAndView modelAndView = new ModelAndView("/commodity/edit");
        modelAndView.addObject("commodity", commodity);
        modelAndView.addObject("message", "Commodity updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-commodity/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Commodity commodity=commodityService.findById(id);
            ModelAndView modelAndView = new ModelAndView("/commodity/delete");
            modelAndView.addObject("commodity",commodity);
            return modelAndView;

    }

    @PostMapping("/delete-commodity")
    public String deleteProvince(@ModelAttribute("commodity") Commodity commodity){
        commodityService.remove(commodity.getId());
        return "redirect:commodity";
    }

}
