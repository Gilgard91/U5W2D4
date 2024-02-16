package epicode.u5d7hw.controllers;

import epicode.u5d7hw.entities.Author;
import epicode.u5d7hw.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Author saveAuthor(@RequestBody Author body) throws Exception {
        return authorsService.save(body);
    }


    @GetMapping("")
    public List<Author> getAuthors() {
        return authorsService.getAuthors();
    }


    @GetMapping("/{authorId}")
    public Author findById(@PathVariable int authorId) throws Exception {
        return authorsService.findById(authorId);
    }


    @PutMapping("/{authorId}")
    public Author findAndUpdate(@PathVariable int authorId, @RequestBody Author body) throws Exception {
        return authorsService.findByIdAndUpdate(authorId, body);
    }


    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int authorId) {
        authorsService.findByIdAndDelete(authorId);
    }
}
