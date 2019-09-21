package pl.honestit.spring.demo.controllers;

import org.aspectj.bridge.Message;
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
    public String processRegistrationPage(LocalDate birthdate, String email, String username, String password, String firstName, String lastName, @Email  @RequestParam MultipartFile file) throws IOException {

        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActive(true);
        user.setFile(file.getBytes());
        user.setBirthDate(birthdate);
        user.setEmail(email);

        List<User> users = userRepository.findAllByUsername(username);
        if (users.isEmpty()) {
            userRepository.save(user);
        }
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
