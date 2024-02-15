package epicode.u5d7hw.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewBlogPostPayload {
    @NotEmpty(message = "L'ID è obbligatorio!")
    private int authorId;
    @NotEmpty(message = "Il titolo è obbligatorio!")
    private String title;
    @NotEmpty(message = "La categoria è obbligatoria!")
    private String category;
    @NotEmpty(message = "Il contenuto è obbligatorio!")
    private String content;
    @NotEmpty(message = "Il reading time è obbligatorio!")
    private double readingTime;
    private String coverURL;

}