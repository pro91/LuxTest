package pl.honestit.spring.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.honestit.spring.demo.model.domain.User;
import pl.honestit.spring.demo.model.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DashboardController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
    @PostMapping
    public String processRegistrationPage(String username, String password, String firstName, String lastName) {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);

        List<User> users = userRepository.findAllByUsername(username);
        if (users.isEmpty()) {
            userRepository.save(user);
        }
        return "dashboard-page";
    }
}
