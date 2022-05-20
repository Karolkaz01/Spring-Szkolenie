package pl.edu.us.warsztaty.warsztaty.spring.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.us.warsztaty.warsztaty.spring.exceptions.UserAlreadyExistException;
import pl.edu.us.warsztaty.warsztaty.spring.model.User;
import pl.edu.us.warsztaty.warsztaty.spring.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;



    @Test
    void shouldCreateUserIfUserDoesNotExist(){
        //given
        //przygotowanie stanu przed wywołaniem metody
        given(userRepository.findUserByName("Mateusz")).willReturn(Optional.empty());
        User user = new User(
                null,
                "Mateusz",
                "Nowak",
                27
        );



        //when
        //wywołanie metody, którą chcemy wykonać

        userService.createUser(user);


        //then
        //sprawdzenie stanu po
        verify(userRepository).save(user);



    }


    @Test
    void shouldFailIfUserAlreadyExist(){

        //given
        given(userRepository.findUserByName("Mateusz")).willReturn(Optional.of(new User(
                null,
                "Mateusz",
                "Mnich",
                30
                )
        ));
        User user = new User(
                null,
                "Mateusz",
                "Nowak",
                27
        );

        //when
        UserAlreadyExistException exception = catchThrowableOfType(
                () -> userService.createUser(user),
                UserAlreadyExistException.class
        );


        //then
        then(exception).isNotNull().hasMessage("User [Mateusz] already exist!");
        verify(userRepository, never()).save(user);


    }

}