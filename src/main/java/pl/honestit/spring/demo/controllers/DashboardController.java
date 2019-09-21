package pl.honestit.spring.demo.controllers;

import org.aspectj.bridge.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.honestit.spring.demo.model.domain.User;
import pl.honestit.spring.demo.model.repositories.UserRepository;

import javax.validation.constraints.Email;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger("DashboardController");

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DashboardController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        log.error("ULALALLAa");
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String DashboardPage(Principal principal, Model model) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "dashboard-page";
    }

    @GetMapping("/profile-image")
    @ResponseBody
    public ResponseEntity<Resource> getProfileImage(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        byte[] file = user.getFile();

        ByteArrayResource resource = new ByteArrayResource(file);
        return ResponseEntity.ok(resource);
    }
}
