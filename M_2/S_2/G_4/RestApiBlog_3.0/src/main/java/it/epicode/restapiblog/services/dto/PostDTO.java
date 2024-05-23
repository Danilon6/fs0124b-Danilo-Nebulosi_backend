package it.epicode.restapiblog.services.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class PostDTO {

    private Long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private double readingTime;
    private Long AuthorId;

}
