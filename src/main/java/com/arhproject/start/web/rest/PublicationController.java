package com.arhproject.start.web.rest;

import com.arhproject.start.jpa.domain.Publication;
import com.arhproject.start.service.PublicationService;
import com.arhproject.start.web.dto.failure.FailureBodyModel;
import com.arhproject.start.web.dto.publication.CreatePublicationDto;
import com.arhproject.start.web.dto.publication.PublicationModel;
import com.arhproject.start.web.dto.publication.PublicationShortModel;
import com.arhproject.start.web.dto.publication.UpdatePublicationDto;
import com.arhproject.start.web.mapper.PublicationMapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/question")
@AllArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;

    private final PublicationMapper publicationMapper;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
            @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<PublicationModel> create(@RequestBody @Valid CreatePublicationDto createDto) {
        Publication publication = publicationService.create(createDto);
        PublicationModel publicationModel = publicationMapper.toModel(publication);
        return ResponseEntity.ok(publicationModel);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
    })
    public ResponseEntity<PublicationModel> get(@PathVariable Long id) {
        Publication publication = publicationService.get(id);
        PublicationModel publicationModel = publicationMapper.toModel(publication);
        return ResponseEntity.ok(publicationModel);
    }

    @GetMapping
    public ResponseEntity<List<PublicationShortModel>> getAll() {
        List<PublicationShortModel> publicationShortModels = publicationService.getAll().stream()
                .map(publicationMapper::toShortModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(publicationShortModels);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, response = FailureBodyModel.class, message = "FORBIDDEN"),
            @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
            @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<PublicationModel> update(@PathVariable Long id,
                                                   @RequestBody @Valid UpdatePublicationDto updateDto) {
        Publication publication = publicationService.update(id, updateDto);
        PublicationModel questionModel = publicationMapper.toModel(publication);
        return ResponseEntity.ok(questionModel);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 401, response = FailureBodyModel.class, message = "UNAUTHORIZED"),
            @ApiResponse(code = 403, response = FailureBodyModel.class, message = "FORBIDDEN"),
            @ApiResponse(code = 404, response = FailureBodyModel.class, message = "NOT FOUND"),
            @ApiResponse(code = 422, response = FailureBodyModel.class, message = "UNPROCESSABLE ENTITY")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publicationService.delete(id);
        return ResponseEntity.ok().build();
    }

}
