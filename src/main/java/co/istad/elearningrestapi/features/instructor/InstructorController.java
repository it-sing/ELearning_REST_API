package co.istad.elearningrestapi.features.instructor;

import co.istad.elearningrestapi.features.instructor.dto.InstructorCreateRequest;
import co.istad.elearningrestapi.features.instructor.dto.InstructorResponse;
import co.istad.elearningrestapi.features.instructor.dto.InstructorUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    //Create a new instructor
    @PostMapping
    void createNew(@Valid @RequestBody InstructorCreateRequest instructorCreateRequest) {
        instructorService.createNew(instructorCreateRequest);
    }

    //Find all instructors by pagination
    @GetMapping
    Page<InstructorResponse> findList(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        return instructorService.findList(page, size);
    }

    //Find an instructor’s profile
    @GetMapping("/{username}")
    InstructorResponse findInstructorProfileByUsername(@PathVariable String username) {
        return instructorService.findInstructorProfileByUsername(username);
    }

    //Update an instructor’s profile
    @PutMapping("/{username}")
    InstructorResponse updateInstructorProfileByUsername(@PathVariable String username,@Valid @RequestBody InstructorUpdateRequest instructorUpdateRequest) {
        return instructorService.updateInstructorProfileByUsername(username, instructorUpdateRequest);
    }

}
