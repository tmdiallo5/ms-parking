package com.parking.ms_parking.security.activations;

import com.parking.ms_parking.profiles.Profile;
import com.parking.ms_parking.security.ApplicationPasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ActivationsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final ActivationsRepository activationsRepository;


    public Activation create(Profile profile){
        Random random = new Random();
        int userCode = 100000 + random.nextInt(900000);
        Activation activation = Activation.builder()
                .active(Boolean.TRUE)
                .userCode(userCode)
                .code(passwordEncoder.encode("" + userCode))
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(5))
                .profiles(profile)
                .build();
        return this.activationsRepository.save(activation);
    }

    public Profile validateAndReturnProfile(Map<String, String> parameters) {
       List<Activation> activations = this.activationsRepository.findAllByActiveAndDesactivationAfter(true, LocalDateTime.now());
      activations = activations.stream().filter(activation ->  passwordEncoder.matches(
               parameters.get("code"),
               activation.getCode()
               )).toList();

      if (activations.isEmpty()) {
          throw new RuntimeException("Activation code not found");
      }
      Activation activation = activations.getFirst();
      activation.setActive(Boolean.FALSE);
      this.activationsRepository.save(activation);
      return activation.getProfiles();
    }
}
