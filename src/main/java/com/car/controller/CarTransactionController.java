package com.car.controller;

import com.car.entity.CarTransaction;
import com.car.service.CarTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transactions")
public class CarTransactionController {

    private final CarTransactionService service;

    public CarTransactionController(CarTransactionService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("transactions", service.findAll());
        return "transaction/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("transaction", new CarTransaction());
        return "transaction/add-form";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute CarTransaction transaction) {
        service.save(transaction);
        return "redirect:/transactions/list";
    }
}
