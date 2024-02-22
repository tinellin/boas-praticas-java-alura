package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.TutorDto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.TutorDto.CadastrarTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    public Tutor cadastrar(@RequestBody @Valid CadastrarTutorDto tutor) {
        return tutorService.salvar(tutor);
    }

    @PutMapping
    public Tutor atualizar(@RequestBody @Valid AtualizarTutorDto dto) {
        return tutorService.atualizar(dto);
    }
}
