package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoDto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private AdocaoRepository adocaoRepo;

    @Autowired
    private TutorRepository tutorRepo;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean tutorTemAdocaoEmAndamento = adocaoRepo.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO);

        if (tutorTemAdocaoEmAndamento)
            throw new AdocaoException("Tutor já possui outra adoção aguardando avaliação!");
    }
}
