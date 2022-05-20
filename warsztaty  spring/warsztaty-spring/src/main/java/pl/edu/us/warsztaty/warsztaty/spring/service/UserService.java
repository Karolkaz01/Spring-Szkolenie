package pl.edu.us.warsztaty.warsztaty.spring.service;

import org.springframework.stereotype.Service;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserAlreadyExistException;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserNotFoundException;
import pl.edu.us.warsztaty.warsztaty.spring.model.User;
import pl.edu.us.warsztaty.warsztaty.spring.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    private List<User> users;// = Arrays.asList(
////            new User(1L,"Karol", "Kaziród", 20),
////            new User(2L,"Aleksander","Kwaśniewski",2137)
////    );

//    public UserService(List<User> users) {
//        this.users = new ArrayList<>();
//        this.users.add(new User(1L,"Karol", "Kaziród", 20));
//        this.users.add(new User(2L,"Aleksander","Kwaśniewski",60));
//    }

    public Optional<User> getUserByName(String name){
        return userRepository.findUserByName(name);
//        return users.stream()
//                .filter(u -> u.getName().equals(name))
//                .findAny();
        //return Optional.empty();
    }

    public User createUser (User user){
        Optional<User> optionalUser = getUserByName(user.getName());
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistException("User [" + user.getName() + "] already exist!");
        }
        return userRepository.save(user);
//        user.setId((long) users.size()+1);
//        users.add(user);
//        return user;
    }

    public User updateUser(long id, User user) {
        User userFromdb = getUserOrThrowException(id);

//        Optional<User> optionalUser = users.stream()
//                .filter(u -> u.getId().equals(id))
//                .findAny();
//        if(optionalUser.isEmpty()){
//            throw new UserNotFoundException("User [" + id + "] not found!");
//        }
//        user.setId(optionalUser.get().getId());
//        users.remove(optionalUser.get());
//        users.add(user);

        userFromdb.setAge(user.getAge());
        userFromdb.setName(user.getName());
        userFromdb.setSurname(user.getSurname());
        return userRepository.save(userFromdb);
    }

    public void deleteUser(Long id) {

        User userFromdb = getUserOrThrowException(id);
        userRepository.delete(userFromdb);
//
//        Optional<User> optionalUser = users.stream()
//                .filter(u -> u.getId().equals(id))
//                .findAny();
//        if(optionalUser.isEmpty()){
//            throw new UserNotFoundException("User [" + id + "] not found!");
//        }
//        users.remove(optionalUser.get());

    }


    public User getUserOrThrowException(Long id){
        User userFromdb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return userFromdb;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
        //return users;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User not found!"));
    }
}
