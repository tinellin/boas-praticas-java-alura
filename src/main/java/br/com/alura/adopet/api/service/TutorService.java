package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.TutorDto.CadastrarTutorDto;
import br.com.alura.adopet.api.exception.TutorException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepo;

    @Transactional
    public Tutor salvar(CadastrarTutorDto dto) {
        boolean tutorExiste = tutorRepo.existsByEmailOrTelefone(dto.email(), dto.telefone());

        if (tutorExiste) throw new TutorException("Dados já cadastrados para outro tutor!");

        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        return tutorRepo.save(tutor);
    }

    @Transactional
    public Tutor atualizar(AtualizarTutorDto dto) {
        Tutor tutor = tutorRepo.findById(dto.id())
                .orElseThrow(() -> new TutorException(String.format("Tutor com id = %s não existe na base de dados.", dto.id())));

        tutor.setNome(dto.nome());
        tutor.setTelefone(dto.telefone());
        tutor.setEmail(dto.email());

        return tutor;
    }
}
