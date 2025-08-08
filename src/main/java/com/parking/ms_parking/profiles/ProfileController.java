package com.parking.ms_parking.profiles;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<ProfileDTO> search() {
        return this.profileService.search();
    }
    @GetMapping(path = "{id}")
    public Profile read(@PathVariable int id) {
            return this.profileService.read(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "{id}")
    public Profile update(@PathVariable int id, @RequestBody Profile profile) {
        return this.profileService.update(id, profile);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable int id) {
        this.profileService.delete(id);
    }

}
