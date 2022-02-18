package com.mywork.discoman.domain.label.api;

import com.mywork.discoman.domain.label.domain.Label;
import com.mywork.discoman.domain.label.dto.request.RequestLabelDto;
import com.mywork.discoman.global.common.response.BasicResponse;
import com.mywork.discoman.global.common.response.CommonResponse;
import com.mywork.discoman.global.common.response.ErrorResponse;
import com.mywork.discoman.domain.label.service.LabelServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelServiceImpl labelService;

    @GetMapping("")
    public ResponseEntity<? extends BasicResponse> getAllLabels(){
        List<Label> labels = labelService.getAllLabels();
        return ResponseEntity.ok(new CommonResponse<List<Label>>(labels));
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> getLabel(@PathVariable Long id){
        Optional<Label> label = labelService.getLabel(id);
        if(label.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("NotFoundId"));
        }
        return ResponseEntity.ok(new CommonResponse<Label>(label.get()));
    }

    @PostMapping("")
    public ResponseEntity<? extends BasicResponse> createLabel(@RequestBody RequestLabelDto labelDto){
        Label label = labelService.saveLabel(labelDto);
        return ResponseEntity.ok(new CommonResponse<Label>(label));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> modifyLabel(
            @PathVariable("id") Long id,
            @RequestBody RequestLabelDto requestLabelDto){
        Label label = labelService.modifyArtist(id, requestLabelDto);
        if(label == null)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Not found id"));
        return ResponseEntity.ok().body(new CommonResponse<Label>(label));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends BasicResponse> deleteLabel(@PathVariable Long id){
        labelService.deleteLabel(id);
        return ResponseEntity.noContent().build();
    }
}
