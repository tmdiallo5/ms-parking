package tech.mavi.ms_parking.security.activations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.mavi.ms_parking.profiles.Profile;

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
                .active(true)
                .userCode(userCode)
                .code(passwordEncoder.encode("" + userCode))
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(10))
                .profile(profile)
                .build();

        return this.activationsRepository.save(activation);

    }

    public Profile validateAndReturnProfile(Map<String, String> parameters) {
        String email = parameters.get("email");
        String code = parameters.get("code");
        List<Activation> activations = this.activationsRepository.findByProfileEmailAndActiveAndDesactivationAfter(
                    email,
                true,
                LocalDateTime.now()
        );
        activations = activations.stream().filter(
                activation -> passwordEncoder.matches(
                        code,
                        activation.getCode()
        )).toList();
        if (activations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid activation code");
        }
        Activation activation = activations.getFirst();
        activation.setActive(false);
        this.activationsRepository.save(activation);
        return activation.getProfile();
    }
}
