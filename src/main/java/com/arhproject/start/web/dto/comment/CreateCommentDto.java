package com.arhproject.start.web.dto.comment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateCommentDto {
    @NotNull
    private Long publicationId;

    @NotBlank
    @Length(max = 4096)
    private String text;
}
