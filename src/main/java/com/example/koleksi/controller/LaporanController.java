package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.service.KoleksiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LaporanController {

    private final KoleksiService koleksiService;

    public LaporanController(KoleksiService koleksiService) {
        this.koleksiService = koleksiService;
    }

    @GetMapping("/laporan")
    public String laporan(Model model) {
        List<Koleksi> terjual = koleksiService.findByStatus("Terjual");
        int totalKeuntungan = terjual.stream()
                .mapToInt(k -> (k.getHargaJual() == null ? 0 : k.getHargaJual()) - (k.getHargaBeli() == null ? 0 : k.getHargaBeli()))
                .sum();

        model.addAttribute("terjual", terjual);
        model.addAttribute("totalKeuntungan", totalKeuntungan);
        return "laporan";
    }
}
