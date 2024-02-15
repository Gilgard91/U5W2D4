package epicode.u5d7hw.controllers;

import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.exceptions.BadRequestException;
import epicode.u5d7hw.payloads.NewBlogPostPayload;
import epicode.u5d7hw.services.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
    @Autowired
    BlogsService blogsService;


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Blogpost saveBlog(@RequestBody @Validated NewBlogPostPayload body, BindingResult validation) {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.blogsService.save(body);
    }


    @GetMapping("")
    public Page<Blogpost> getAllBlogPosts(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String orderBy
    ) {
        return this.blogsService.getBlogPosts(page, size, orderBy);
    }


    @GetMapping("/{blogId}")
    public Blogpost findById(@PathVariable int blogId) {
        return blogsService.findById(blogId);
    }


    @PutMapping("/{blogId}")
    public Blogpost findAndUpdate(@PathVariable int blogId, @RequestBody NewBlogPostPayload body) {
        return blogsService.findByIdAndUpdate(blogId, body);
    }


    @DeleteMapping("/{blogId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable int blogId) {
        blogsService.findByIdAndDelete(blogId);
    }

    @PostMapping("/upload")
    public String uploadAvatar(@RequestParam("cover") MultipartFile image) throws IOException {
        return this.blogsService.uploadImage(image);
    }
}
