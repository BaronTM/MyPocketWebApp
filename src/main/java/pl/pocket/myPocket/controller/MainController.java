package pl.pocket.myPocket.controller;

import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pocket.myPocket.controller.repository.UserRepository;
import pl.pocket.myPocket.model.entities.ExpenseCategory;
import pl.pocket.myPocket.model.RegistrationForm;
import pl.pocket.myPocket.model.Session;
import pl.pocket.myPocket.model.entities.RevenueCategory;
import pl.pocket.myPocket.model.entities.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session session;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @RequestMapping("/loggedin")
    public String loggedin(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        User userFromRepository = userRepository.getUserFromRepository(userName);
        session.setUser(userFromRepository);
        return app(model);
    }

    @RequestMapping("/app")
    public String app(Model model) {
        User user = session.getUser();
        if (user == null) return "/login?error=nouser";
        model.addAttribute("user", user);
        return "app/app.html";
    }

    @RequestMapping("/login")
    public String loginPage(Model model) {
        RegistrationForm registrationForm = new RegistrationForm();
        model.addAttribute("registrationForm", registrationForm);
        return "login.html";
    }

    @RequestMapping(value = "/registration")
    @Transactional
    public String register(RegistrationForm registrationForm, Model model) {
        boolean userExists = userRepository.checkIfUserExists(registrationForm.getUserName());
        boolean passwordLength = registrationForm.getPassword().length() >= 8;
        boolean passwordConfirmed = registrationForm.getPassword().equals(registrationForm.getPasswordConfirmation());
        if (!userExists && passwordLength && passwordConfirmed) {
            try {
                User user = new User(registrationForm.getUserName(), passwordEncoder.encode(registrationForm.getPassword()));
                userRepository.persistUser(user);
                model.addAttribute("registrationPassed", "yes");
                return "/login";
            } catch (Exception e) {
                model.addAttribute("registrationPassed", "no");
                return "login.html";
            }
        } else {
            model.addAttribute("registrationPassed", "no");
            return "login.html";
        }
    }

    @RequestMapping(value = "/isusernameenabled")
    @ResponseBody
    public String isUsernameEnabled(@RequestParam(name = "username") String userName) {
        boolean exists = userRepository.checkIfUserExists(userName);
        if (exists) return "exists";
        else return "free";
    }

    @RequestMapping(value = "/getexpences")
    @ResponseBody
    public String getExpences() {
        User user = session.getUser();
        if (user == null) return "";
        Map<ExpenseCategory, Double> expencesMap = user.getWallet().getExpencesMap();

        Collection<Double> values = expencesMap.values();
        Double[] doubles = values.toArray(new Double[values.size()]);
        double[] doublePrimitives = ArrayUtils.toPrimitive(doubles);

        List<String> colorsList = expencesMap.keySet().stream().map(e -> e.getColor()).collect(Collectors.toList());
        String[] colors = colorsList.toArray(new String[colorsList.size()]);

        List<String> labelsList = expencesMap.keySet().stream().map(e -> e.getCategoryName()).collect(Collectors.toList());
        String[] labels = labelsList.toArray(new String[labelsList.size()]);

        Object[] data = new Object[3];
        data[0] = doublePrimitives;
        data[1] = colors;
        data[2] = labels;

        String json = new Gson().toJson(data);
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/getrevenues")
    @ResponseBody
    public String getRevenues() {
        User user = session.getUser();
        if (user == null) return "";
        Map<RevenueCategory, Double> revenueMap = user.getWallet().getRevenueMap();

        Collection<Double> values = revenueMap.values();
        Double[] doubles = values.toArray(new Double[values.size()]);
        double[] doublePrimitives = ArrayUtils.toPrimitive(doubles);

        List<String> colorsList = revenueMap.keySet().stream().map(e -> e.getColor()).collect(Collectors.toList());
        String[] colors = colorsList.toArray(new String[colorsList.size()]);

        List<String> labelsList = revenueMap.keySet().stream().map(e -> e.getCategoryName()).collect(Collectors.toList());
        String[] labels = labelsList.toArray(new String[labelsList.size()]);

        Object[] data = new Object[3];
        data[0] = doublePrimitives;
        data[1] = colors;
        data[2] = labels;

        String json = new Gson().toJson(data);
        System.out.println(json);
        return json;
    }
}
