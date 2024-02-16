package epicode.u5d7hw.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import epicode.u5d7hw.entities.Author;
import epicode.u5d7hw.entities.Blogpost;
import epicode.u5d7hw.exceptions.NotFoundException;
import epicode.u5d7hw.payloads.NewBlogPostPayload;
import epicode.u5d7hw.repositories.BlogPostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogsService {

    @Autowired
    BlogPostsDAO blogPostsDAO;
    @Autowired
    AuthorsService authorsService;

    @Autowired
    Cloudinary cloudinaryUploader;

//    private final List<Blogpost> blogs = new ArrayList<>();

    public Blogpost save(NewBlogPostPayload body) {
        Author author = authorsService.findById(body.getAuthorId());
        Blogpost newBlogPost = new Blogpost();
        newBlogPost.setReadingTime(body.getReadingTime());
        newBlogPost.setContent(body.getContent());
        newBlogPost.setTitle(body.getTitle());
        newBlogPost.setAuthor(author);
        newBlogPost.setCategory(body.getCategory());
//        newBlogPost.setCover("http://picsum.photos/200/300");
        newBlogPost.setCover(body.getCoverURL());
        return blogPostsDAO.save(newBlogPost);

    }

    public Page<Blogpost> getBlogPosts(int pageNumber, int size, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return blogPostsDAO.findAll(pageable);
    }

    public Blogpost findById(int id) {
        return blogPostsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Blogpost found = this.findById(id);
        blogPostsDAO.delete(found);
    }


    public Blogpost findByIdAndUpdate(int id, NewBlogPostPayload body) {
        Blogpost found = this.findById(id);
        found.setCategory(body.getCategory());
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());

        if (found.getAuthor().getId() != body.getAuthorId()) {
            Author newAuthor = authorsService.findById(body.getAuthorId());
            found.setAuthor(newAuthor);
        }
        return blogPostsDAO.save(found);
    }

    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(),
                ObjectUtils.emptyMap()).get("url");
        return url;
    }
}
