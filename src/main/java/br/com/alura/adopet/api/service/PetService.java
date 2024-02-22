package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PetDto.CadastrarPetDto;
import br.com.alura.adopet.api.exception.AbrigoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepo;

    @Autowired
    private AbrigoRepository abrigoRepo;


    public List<Pet> listarTodosDisponiveis() {
        return petRepo.findAllByAdotado(false);
    }

    public Pet salvar(CadastrarPetDto dto) {
        Abrigo abrigo = abrigoRepo.findByNome(dto.nomeAbrigo()).orElseThrow(() -> new AbrigoException(HttpStatus.FORBIDDEN, "Abrigo inexistente"));

        Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso(), abrigo);
        return petRepo.save(pet);
    }
}
