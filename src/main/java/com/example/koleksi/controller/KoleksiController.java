package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.repository.KategoriRepository;
import com.example.koleksi.service.KoleksiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/koleksi")
public class KoleksiController {

    private final KoleksiService koleksiService;
    private final KategoriRepository kategoriRepository;

    public KoleksiController(KoleksiService koleksiService, KategoriRepository kategoriRepository) {
        this.koleksiService = koleksiService;
        this.kategoriRepository = kategoriRepository;
    }

    @GetMapping
    public String list(@RequestParam(value = "q", required = false) String q,
                       @RequestParam(value = "status", required = false) String status,
                       Model model) {
        List<Koleksi> items;
        if (q != null && !q.isEmpty()) items = koleksiService.searchByName(q);
        else if (status != null && !status.isEmpty()) items = koleksiService.findByStatus(status);
        else items = koleksiService.findAll();

        model.addAttribute("items", items);
        model.addAttribute("categories", kategoriRepository.findAll());
        return "koleksi/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("koleksi", new Koleksi());
        model.addAttribute("categories", kategoriRepository.findAll());
        return "koleksi/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Koleksi koleksi) {
        koleksiService.save(koleksi);
        return "redirect:/koleksi";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        koleksiService.findById(id).ifPresent(k -> model.addAttribute("koleksi", k));
        model.addAttribute("categories", kategoriRepository.findAll());
        return "koleksi/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        koleksiService.deleteById(id);
        return "redirect:/koleksi";
    }
}
