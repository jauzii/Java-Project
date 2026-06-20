package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.service.KoleksiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DashboardController {

    private final KoleksiService koleksiService;

    public DashboardController(KoleksiService koleksiService) {
        this.koleksiService = koleksiService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        int totalValue = koleksiService.totalValue();
        long countDijual = koleksiService.countByStatus("Dijual");
        long countTerjual = koleksiService.countByStatus("Terjual");
        List<Koleksi> allItems = koleksiService.findAll();
        long totalKoleksi = allItems.size();
        Optional<Koleksi> mostExpensive = koleksiService.findMostExpensive();

        // Ambil 5 koleksi terbaru
        List<Koleksi> recentItems = allItems.stream()
                .limit(5)
                .collect(Collectors.toList());

        model.addAttribute("totalValue", totalValue);
        model.addAttribute("countDijual", countDijual);
        model.addAttribute("countTerjual", countTerjual);
        model.addAttribute("totalKoleksi", totalKoleksi);
        model.addAttribute("mostExpensive", mostExpensive.orElse(null));
        model.addAttribute("recentItems", recentItems);
        model.addAttribute("activePage", "dashboard");

        return "dashboard";
    }
}