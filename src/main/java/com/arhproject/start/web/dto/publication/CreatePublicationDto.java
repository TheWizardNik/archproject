package com.arhproject.start.web.dto.publication;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CreatePublicationDto {

    @NotBlank
    @Length(max = 256)
    private String title;

    private List<TextOrImageModel> textOrImageModel;

    private List<Long> userIds;

    private List<String> tags;

}
