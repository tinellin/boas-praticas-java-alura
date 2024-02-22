package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto.CadastrarAbrigoDto;
import br.com.alura.adopet.api.exception.AbrigoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepo;

    @Transactional
    public Abrigo cadastrar(CadastrarAbrigoDto dto) {
        boolean abrigoExiste = abrigoRepo.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email());

        if (abrigoExiste) throw new AbrigoException("Dados já cadastrados para outro abrigo!");

        return abrigoRepo.save(new Abrigo(dto.nome(), dto.telefone(), dto.email()));
    }

    public List<Pet> listarPets(Long id) {
        Abrigo abrigo = abrigoRepo.findById(id).orElseThrow(() -> new AbrigoException("Abrigo com id inexistente."));
        List<Pet> pets = abrigo.getPets();
        return pets;
    }

    public List<Pet> listarPets(String nome) {
        Abrigo abrigo = abrigoRepo.findByNome(nome).orElseThrow(() -> new AbrigoException("Abrigo com nome inexistente."));
        return abrigo.getPets();
    }

    public List<Abrigo> listarTodosAbrigos() {
        return abrigoRepo.findAll();
    }
}
