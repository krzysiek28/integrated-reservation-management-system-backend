package com.uliasz.irms.backend.users;

import com.uliasz.irms.internal.database.entities.TestEntity;
import com.uliasz.irms.internal.database.repositories.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testApi")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    private final TestRepository testRepository;

    @GetMapping(value = "/text")
    public TextModel getText() {
        testRepository.save(new TestEntity("Anna", "Kowalska"));
        return new TextModel("text");
    }

    @GetMapping(value = "/tests")
    public List<TestEntity> getTests() {
        return testRepository.findAll();
    }

    @PostMapping(value = "/tests")
    public void saveUser(@RequestBody TestEntity entity) {
        testRepository.save(entity);
    }
}
