package pl.honestit.spring.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.honestit.spring.demo.model.domain.Advert;
import pl.honestit.spring.demo.model.repositories.AdvertRepository;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    private AdvertRepository advertRepository;

    public HomePageController(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @GetMapping
    public String prepareHomePage(Model model) {
        List<Advert> allAdverts = advertRepository.findAllByOrderByPostedDesc();
        model.addAttribute("adverts", allAdverts);
        return "home-page";
    }
}
