package org.example.moviservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.moviservice.dto.ActorDto;
import org.example.moviservice.services.ActorService;
import org.example.moviservice.services.RequestCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/actors")
@CrossOrigin(origins = "http://localhost:3000")
public class ActorController {

    private final ActorService actorService;
    private final RequestCounterService requestCounterService;

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAllActors() {
        log.info("Trying GET all actors");
        log.info("{}", requestCounterService.incrementAndGet());
        List<ActorDto> actors = actorService.getAllActors();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable Long id) {
        log.info("Trying GET actor by id");
        log.info("{}", requestCounterService.incrementAndGet());
        ActorDto actorDto = actorService.getActorById(id);
        return new ResponseEntity<>(actorDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorDto actorDto) {
        log.info("Trying POST actor");
        log.info("{}", requestCounterService.incrementAndGet());
        ActorDto createdActor = actorService.createActor(actorDto);
        return new ResponseEntity<>(createdActor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable Long id,
                                                @RequestBody ActorDto actorDto) {
        log.info("Trying PUT actor");
        log.info("{}", requestCounterService.incrementAndGet());
        ActorDto updatedActor = actorService.updateActor(id, actorDto);
        return new ResponseEntity<>(updatedActor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActor(@PathVariable Long id) {
        log.info("Trying DEL actor");
        log.info("{}", requestCounterService.incrementAndGet());
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}