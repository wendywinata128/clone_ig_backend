package com.commsult_test.clone_ig.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.commsult_test.clone_ig.dto.PostRequestDTO;
import com.commsult_test.clone_ig.dto.UserRequestDTO;
import com.commsult_test.clone_ig.model.User;
import com.commsult_test.clone_ig.repository.UserRepository;
import com.commsult_test.clone_ig.service.PostService;
import com.commsult_test.clone_ig.service.UserService;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        List<User> users = userRepository.findAll();

        if(users.size() != 0){
            log.info("Seeding is skipped");
            return;
        }

        log.info("Seeding started ");
        // seed 20 Users
        Faker faker = new Faker();
        List<User> listUser = new ArrayList<User>();
        Integer userCreated = 0;
        Integer postCreated = 0;
        Integer followCreated = 0;

        for (int i = 0; i < 20; i++) {
            var userDummy = UserRequestDTO
                    .builder()
                    .username(i == 0 ? "codi.ziemann" : faker.name().username())
                    .name(faker.name().name())
                    .avatar(null)
                    .password("dummy123")
                    .email(faker.internet().emailAddress())
                    .build();
            User user = userService.register(userDummy);
            userCreated++;
            listUser.add(user);

            for (int j = 0; j < 10; j++) {
                PostRequestDTO postRequestDTO = PostRequestDTO
                        .builder()
                        .description(faker.lorem().paragraph())
                        .build();

                postService.createUserPost(postRequestDTO, user, "dummy.jpg");
                postCreated++;
            }
        }

        for (int i = 0; i < 20; i++) {
            User user = listUser.get(i);
            for (int j = 0; j < 20; j++) {
                if (j == i) {
                    continue;
                }

                userService.followUser(user, listUser.get(j).getId());
                followCreated++;
            }
        }

        log.info("User Created: " + userCreated);
        log.info("Post Created: " + postCreated);
        log.info("Follow Created: " + followCreated);
    }

}
