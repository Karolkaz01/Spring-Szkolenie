package pl.edu.us.warsztaty.warsztaty.spring.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mvc;


    @Test
    void shouldReturnUserByNameIfUserExist() throws Exception {
        mvc.perform(get("/users/Andrzej"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.id").value(1),
                        jsonPath("$.name").value("Andrzej"),
                        jsonPath("$.surname").value("Duda"),
                        jsonPath("$.age").value(45)
                );
    }


    @Test
    void shouldFailToGetUserByNameIfUserDoesNotExist() throws Exception {
        mvc.perform(get("/users/Karol"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.timestamp").value("2022-05-19T10:54:58.000Z"),
                        jsonPath("$.message").value("User [Mateusz] not found!"),
                        jsonPath("$.details").value("uri=/users/Mateusz")
                );
    }

    @Test
    void shouldAddNewUserIfUserDoesNotExist() throws Exception{
        mvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("" +
                            "{" +
                            "   \"name\": \"Mateusz\"," +
                            "   \"surname\": \"Nowak\"," +
                            "   \"age\": 27" +
                            "}"
            ))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.id").isNotEmpty(),
                        jsonPath("$.name").value("Mateusz"),
                        jsonPath("$.surname").value("Nowak"),
                        jsonPath("$.age").value(27)
                );

        mvc.perform(get("/users/Mateusz"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @TestConfiguration
    static class TestConfig{


        @Primary
        @Bean
        Clock fixedClock(){
            return Clock.fixed(
                    Instant.parse("2022-05-19T10:54:58.000Z"),
                    ZoneId.from(ZoneOffset.UTC)
            );
        }


    }



}