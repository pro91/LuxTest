package pl.honestit.spring.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegistrationController {

//    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public RegistrationController() {
    }

    @GetMapping
    public String prepareRegistrationPage(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);
        return "registration-form";
    }

    @PostMapping
    public String processRegistrationPage(String username, String password, String firstName, String lastName, @RequestParam MultipartFile file) throws IOException {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        user.setFile(file.getBytes());

        List<User> users = userRepository.findAllByUsername(username);
        if (users.isEmpty()) {
            userRepository.save(user);
        }
        return "redirect:/login";
    }
}
