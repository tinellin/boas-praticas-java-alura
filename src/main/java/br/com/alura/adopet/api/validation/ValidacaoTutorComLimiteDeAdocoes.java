package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoDto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepo;

    @Autowired
    private TutorRepository tutorRepo;

    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepo.findAll();
        Tutor tutor = tutorRepo.getReferenceById(dto.idTutor());

        if (adocoes.stream().filter(a -> a.getTutor().equals(tutor) && a.getStatus() == StatusAdocao.APROVADO).count() == 5)
            throw  new AdocaoException("Tutor chegou ao limite máximo de 5 adoções!");
    }
}
