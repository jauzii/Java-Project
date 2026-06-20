package com.example.koleksi.controller;

import com.example.koleksi.model.Kategori;
import com.example.koleksi.repository.KategoriRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kategori")
@SuppressWarnings("null")
public class KategoriController {

    private final KategoriRepository kategoriRepository;

    public KategoriController(KategoriRepository kategoriRepository) {
        this.kategoriRepository = kategoriRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", kategoriRepository.findAll());
        model.addAttribute("activePage", "kategori");
        return "kategori/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("kategori", new Kategori());
        return "kategori/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Kategori kategori) {
        kategoriRepository.save(kategori);
        return "redirect:/kategori";
    }
}