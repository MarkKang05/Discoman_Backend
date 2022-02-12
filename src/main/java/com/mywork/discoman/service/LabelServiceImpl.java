package com.mywork.discoman.service;

import com.mywork.discoman.domain.Label;
import com.mywork.discoman.dto.CreateLabelDto;
import com.mywork.discoman.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabelServiceImpl {
    private final LabelRepository labelRepository;

    public List<Label> getAllLabels(){
        return labelRepository.findAll();
    }

    public Optional<Label> getLabel(Long id) {
        return labelRepository.findById(id);
    }

    public Label saveLabel(CreateLabelDto labelDto){
        log.debug(labelDto.getContactInfo());
        return labelRepository.save(labelDto.toEntity());
    }

    public boolean existLabelName(String name){
        return labelRepository.existsByName(name);
    }

    public void deleteLabel(Long id){
        labelRepository.deleteById(id);
    }
}
