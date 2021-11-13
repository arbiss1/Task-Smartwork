package task.smartwork.arbis.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import task.smartwork.arbis.domain.PhoneBook;
import task.smartwork.arbis.domain.User;
import task.smartwork.arbis.repository.PhoneBookRepository;
import task.smartwork.arbis.repository.UserRepository;
import task.smartwork.arbis.service.NameService;
import org.springframework.web.bind.annotation.*;
import task.smartwork.arbis.service.PhoneBookService;
import task.smartwork.arbis.service.UserService;
import javax.validation.Valid;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/phoneBook")
@Api(value="Phone Book", description="Phone Book CRUD operations")
public class RestController {

    @Autowired
    PhoneBookService phoneBookService;

    @Autowired
    NameService nameService;

    @Autowired
    PhoneBookRepository phoneBookRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/createPhoneBook")
    public ResponseEntity<PhoneBook> createPhoneBook (@RequestBody PhoneBook phoneBook) throws Exception {

        if(phoneBook.getType().equals("Work") || phoneBook.getType().equals("Cellphone") || phoneBook.getType().equals("Home")){
            phoneBook.setType(phoneBook.getType());
        }else {
            return new ResponseEntity("Type must be {Work , Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }
        //save name
        nameService.saveName(phoneBook);

        //save phoneBook
        PhoneBook newPhoneBook = new PhoneBook();
        newPhoneBook.setName(phoneBook.getName());
        newPhoneBook.setNumber(phoneBook.getNumber());
        newPhoneBook.setType(phoneBook.getType());
        phoneBookService.savePhoneBook(newPhoneBook);

        //return response in json format
        return new ResponseEntity(newPhoneBook, HttpStatus.OK);
    }

    @DeleteMapping("/deletePhoneBookById")
    public ResponseEntity<PhoneBook> deletePhoneBookById(@RequestBody PhoneBook phoneBook){
        PhoneBook deletePhoneBook = phoneBookRepository.findById(phoneBook.getId());
        if(deletePhoneBook == null) {
            return new ResponseEntity("Phone book is not found",HttpStatus.BAD_REQUEST);
        }
        phoneBookService.deletePhoneBook(deletePhoneBook);
        return new ResponseEntity(phoneBook, HttpStatus.OK);
    }

    @PutMapping("/editPhoneBookById")
    public ResponseEntity<PhoneBook> editPhoneBookById(@RequestBody PhoneBook phoneBook){
        if(phoneBook.getType().equals("Work") || phoneBook.getType().equals("Cellphone") || phoneBook.getType().equals("Home")){
                PhoneBook editPhoneBook = phoneBookRepository.findById(phoneBook.getId());
                if(editPhoneBook == null) {
                    return new ResponseEntity("Phone book is not found",HttpStatus.BAD_REQUEST);
                 }
                editPhoneBook.setName(phoneBook.getName());
                editPhoneBook.setType(phoneBook.getType());
                editPhoneBook.setNumber(phoneBook.getNumber());
                phoneBookService.savePhoneBook(editPhoneBook);
                return new ResponseEntity<>(editPhoneBook, HttpStatus.OK);
        }else {
            return new ResponseEntity("Type must be {Work , Cellphone or Home}",HttpStatus.BAD_REQUEST);
        }
    }

    //building interface
    @RequestMapping("/login")
    public ModelAndView showUserSignin() {
        ModelAndView modelAndView = new ModelAndView("signinUser.html");
        return modelAndView;

    }

    @RequestMapping("/procces-login")
    public ModelAndView showUserPage(Model model, User user, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("signinUser.html");
        ModelAndView errorMav = new ModelAndView("redirect:/phoneBook/login");
        if (!userService.isUserValid(user)) {
            String message = "Username or password is incorrect !";
            model.addAttribute("noUsernameExists", message);
            return modelAndView;
        }else {
        return errorMav;
        }
    }

    @GetMapping("/register")
    public ModelAndView showRegistrationForm(Model model) {
        ModelAndView mav = new ModelAndView("signup_form");
        User user = new User();
        model.addAttribute("user", user);
        return mav;
    }

    @PostMapping("/process_register")
    public String processRegister(@ModelAttribute(name = "user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "signup_form";

        if (userService.isUsernamePresent(user)) {
            String message = "Username already exists !";
            model.addAttribute("nonUniqueUsername", message);
            return "signup_form";
        } else {
            User newUser = new User();
            newUser.setPassword(user.getPassword());
            newUser.setEnabled(true);
            newUser.setRoles("USER");
            newUser.setType(user.getType());
            newUser.setNumber(user.getNumber());
            newUser.setUsername(user.getUsername());
            newUser.setName(user.getName());
            userRepository.save(newUser);
        }
        return "signinUser";
    }

}
