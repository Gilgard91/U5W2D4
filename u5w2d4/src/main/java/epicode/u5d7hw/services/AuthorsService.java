package epicode.u5d7hw.services;

import epicode.u5d7hw.entities.Author;
import epicode.u5d7hw.exceptions.NotFoundException;
import epicode.u5d7hw.repositories.AuthorsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorsService {

    @Autowired
    AuthorsDAO authorsDAO;

    public Author save(Author body) {
        return authorsDAO.save(body);
    }

    public List<Author> getAuthors() {
        return authorsDAO.findAll();
    }

    public Author findById(int id) {
        return authorsDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Author found = this.findById(id);
        authorsDAO.delete(found);
    }

    public Author findByIdAndUpdate(int id, Author body) {
        Author found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setDateOfBirth(body.getDateOfBirth());
        found.setAvatar(body.getAvatar());
        return authorsDAO.save(found);
    }
}
