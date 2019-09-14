package pl.honestit.spring.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.honestit.spring.demo.model.domain.Advert;
import pl.honestit.spring.demo.model.domain.User;
import pl.honestit.spring.demo.model.repositories.AdvertRepository;
import pl.honestit.spring.demo.model.repositories.UserRepository;

import java.security.Principal;

@Controller
public class AdvertController {

    private static final Logger log = LoggerFactory.getLogger(AdvertController.class);

    private UserRepository userRepository;
    private AdvertRepository advertRepository;

    public AdvertController(UserRepository userRepository, AdvertRepository advertRepository) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
    }

    @PostMapping("/add-advert")
    public String processAddAdvert(String title, @RequestParam(required = false) String description, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        Advert advert = new Advert();
        advert.setTitle(title);
        advert.setDescription(description);
        advert.setOwner(user);

        log.info("Próba zapisu ogłoszenia: " + advert);
        advertRepository.save(advert);
        log.info("Zapisano ogłoszenie: " + advert);

        return "redirect:/";
    }
}
