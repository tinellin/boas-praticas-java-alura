package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public List<Abrigo> listarTodosAbrigos() {
        return abrigoService.listarTodosAbrigos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Abrigo cadastrar(@RequestBody @Valid CadastrarAbrigoDto dto) {
        return abrigoService.cadastrar(dto);
    }

    @GetMapping("/{id}/pets")
    public List<Pet> listarPets(@PathVariable Long id) {
        return abrigoService.listarPets(id);
    }

    @GetMapping("/pets")
    public List<Pet> listarPets(@RequestParam(value = "nome") String nome) {
        return abrigoService.listarPets(nome);
    }
}
