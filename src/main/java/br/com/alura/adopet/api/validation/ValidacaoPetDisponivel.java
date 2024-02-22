package br.com.alura.adopet.api.validation;

import br.com.alura.adopet.api.dto.AdocaoDto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    @Autowired
    private PetRepository petRepo;

    public void validar(SolicitacaoAdocaoDto dto) {
        Pet pet = petRepo.getReferenceById(dto.idPet());
        if (pet.getAdotado()) throw new AdocaoException("Pet já foi adotado!");
    }
}
