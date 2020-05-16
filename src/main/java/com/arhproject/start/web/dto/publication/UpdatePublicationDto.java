package com.arhproject.start.web.dto.publication;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePublicationDto {
    @NotBlank
    @Length(max = 256)
    private String title;

    @NotBlank
    @Length(max = 4096)
    private String text;
}
