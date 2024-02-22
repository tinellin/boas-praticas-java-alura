package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.PetDto.CadastrarPetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet cadastrar(@RequestBody @Valid CadastrarPetDto dto) {
        return petService.salvar(dto);
    }

    @GetMapping
    public List<Pet> listarTodosDisponiveis() {
        return petService.listarTodosDisponiveis();
    }
}
