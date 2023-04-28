package com.TreeD.application.bootstrap;

import com.TreeD.application.model.entity.*;
import com.TreeD.application.model.roles.Roles;
import com.TreeD.application.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class BootstrapData implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private final CommentRepository commentRepository;
    private final VehicleRepository vehicleRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Authority admin = authorityRepository.save(Authority.builder().role(Roles.ROLE_ADMIN.name()).build());
        Authority userRole = authorityRepository.save(Authority.builder().role(Roles.ROLE_USER.name()).build());
        Authority vendor = authorityRepository.save(Authority.builder().role(Roles.ROLE_VENDOR.name()).build());
        Authority $3d_specialist = authorityRepository.save(Authority.builder().role(Roles.ROLE_3D_SPECIALIST.name()).build());

       User user = User.builder()
                .username("John")
                .firstName("John")
                .lastName("Deer")
                .password(passwordEncoder.encode("password"))
                .authorities(Collections.singleton(userRole))
                .email("john@TreeD.com")
                .phoneNumber("555-1234")
                .address("123 Main St")
                .build();

        user.setModifiedAt(ZonedDateTime.now());
        user.setCreatedAt(ZonedDateTime.now());

        User savedUser = userRepository.save(user);

        Price price = new Price();
        price.setAmount(BigDecimal.TEN);
        price.setCurrency("EUR");

        Vehicle vehicle = new Vehicle();
        vehicle.setCreatedAt(ZonedDateTime.now());
        vehicle.setModifiedAt(ZonedDateTime.now());
        vehicle.setModel("525D");
        vehicle.setMake("BMW");
        vehicle.setStartYear("1996");
        vehicle.setEndYear("2003");
        vehicle.setInternalBodyCode("E39");

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        Byte[] data = dummyBytes(user);

        Model model = new Model();
        model.setModelName("BMW 525D model");
        model.setUser(savedUser);
        model.setModifiedAt(ZonedDateTime.now());
        model.setCreatedAt(ZonedDateTime.now());
        model.setModel(data);
        model.setPrice(price);
        model.setDetails("Details");
        model.setImage(data);
        model.setCarModel(savedVehicle);


        Model savedModel = modelRepository.save(model);

        Comment comment = new Comment();
        comment.setModifiedAt(ZonedDateTime.now());
        comment.setCreatedAt(ZonedDateTime.now());
        comment.setModel(savedModel);
        comment.setComment("Demo comment for model");
        comment.setUser(savedUser);
        comment.setRating(4.7);

        commentRepository.save(comment);
    }

    private Byte[] dummyBytes(User user) {
        Byte[] data = new Byte[user.getAddress().length()];
        int i = 0;
        for (byte b : user.getAddress().getBytes()) {
            data[i++] = b;
        }
        return data;
    }
}
