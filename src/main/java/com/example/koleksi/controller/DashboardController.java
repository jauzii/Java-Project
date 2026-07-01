package com.example.koleksi.controller;

import com.example.koleksi.model.Koleksi;
import com.example.koleksi.service.KoleksiService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
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
    public String dashboard(Model model,
                             @RequestParam(value = "needLogin", required = false) String needLogin) {

        boolean isLoggedIn = isUserLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);

        // Kalau guest mencoba akses halaman yang butuh login,
        // SecurityConfig akan redirect ke sini dengan ?needLogin
        // -> tampilkan alert peringatan di dashboard.
        if (needLogin != null) {
            model.addAttribute("needLoginWarning", true);
        }

        if (isLoggedIn) {
            // ── USER SUDAH LOGIN: tampilkan data asli ──
            int totalValue = koleksiService.totalValue();
            long countDijual = koleksiService.countByStatus("Dijual");
            long countTerjual = koleksiService.countByStatus("Terjual");
            List<Koleksi> allItems = koleksiService.findAll();
            long totalKoleksi = allItems.size();
            Optional<Koleksi> mostExpensive = koleksiService.findMostExpensive();

            List<Koleksi> recentItems = allItems.stream()
                    .limit(5)
                    .collect(Collectors.toList());

            model.addAttribute("totalValue", totalValue);
            model.addAttribute("countDijual", countDijual);
            model.addAttribute("countTerjual", countTerjual);
            model.addAttribute("totalKoleksi", totalKoleksi);
            model.addAttribute("mostExpensive", mostExpensive.orElse(null));
            model.addAttribute("recentItems", recentItems);
        } else {
            // ── GUEST: jangan expose data asli, kosongkan semua ──
            model.addAttribute("totalValue", 0);
            model.addAttribute("countDijual", 0L);
            model.addAttribute("countTerjual", 0L);
            model.addAttribute("totalKoleksi", 0L);
            model.addAttribute("mostExpensive", null);
            model.addAttribute("recentItems", Collections.emptyList());
        }

        model.addAttribute("activePage", "dashboard");

        return "dashboard";
    }

    /**
     * Cek apakah user benar-benar sudah login (bukan anonymous).
     * Spring Security secara default memberi "anonymous authentication"
     * ke semua request yang belum login, jadi authentication.isAuthenticated()
     * SELALU true. Cara yang benar adalah cek apakah principal-nya anonymous.
     */
    private boolean isUserLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return false;
        }
        return auth.isAuthenticated()
                && !"anonymousUser".equals(auth.getPrincipal());
    }
}