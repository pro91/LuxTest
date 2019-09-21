package pl.honestit.spring.demo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.honestit.spring.demo.model.domain.User;
import pl.honestit.spring.demo.model.repositories.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController {

    private static final Logger log = LoggerFactory.getLogger("DashboardController");

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public HomePageController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String prepareHomePage(Model model) {
        return "home-page";
    }


    @PostMapping
    public String processRegistrationPage(String birthdate, String email, String username, String password, String firstName, String lastName, @RequestParam MultipartFile file) throws IOException {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setBirthDate(LocalDate.parse(birthdate));
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        user.setFile(file.getBytes());
        user.setEmail(email);

        List<User> users = userRepository.findAllByUsername(username);
        if (users.isEmpty()) {
            userRepository.save(user);
            return "redirect:/dashboard";
        }
        else {
            return "home-page";
        }
    }
}
