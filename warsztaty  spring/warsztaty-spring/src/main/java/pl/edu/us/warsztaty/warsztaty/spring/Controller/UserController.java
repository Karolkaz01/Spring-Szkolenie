package pl.edu.us.warsztaty.warsztaty.spring.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserNotFoundException;
import pl.edu.us.warsztaty.warsztaty.spring.model.User;
import pl.edu.us.warsztaty.warsztaty.spring.model.UserList;
import pl.edu.us.warsztaty.warsztaty.spring.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserList getAllUsers(){
        return new UserList(userService.getAllUsers());
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable("name") String name) {

        return userService.getUserByName(name)
                .orElseThrow(() -> new UserNotFoundException("User [" + name + "] not found!"));


//        if ("Karol".equals(name)){
//            return new User("Karol", "Kaziród", 20);
//        } else if ("Aleksander".equals(name)) {
//            return new User("Aleksander","Kwaśniewski",2137);
//        }
//
//        throw new UserNotFoundException("User [" + name + "] not found!");
    }

//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable("name") Long id){
//        return userService.getUserById(id);
//    }



    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
