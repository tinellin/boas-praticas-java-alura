package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.AdocaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validation.ValidacaoSolicitacaoAdocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository adocaoRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetRepository petRepo;

    @Autowired
    private TutorRepository tutorRepo;

    @Autowired
    private List<ValidacaoSolicitacaoAdocao> validacoes;

    @Transactional
    public void solicitar(SolicitacaoAdocaoDto dto) {
//        if (pet.getAdotado()) throw new AdocaoException("Pet já foi adotado!");

        validacoes.forEach(v -> v.validar(dto));

//        List<Adocao> adocoes = adocaoRepo.findAll();

//        if (adocoes.stream().anyMatch(a -> a.getTutor().equals(tutor) && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO))
//            throw new AdocaoException("Tutor já possui outra adoção aguardando avaliação!");
//
//        if(adocoes.stream().anyMatch(a -> a.getPet().equals(pet) && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO))
//            throw new AdocaoException("Pet já está aguardando avaliação para ser adotado!");
//
//        if (adocoes.stream().filter(a -> a.getTutor().equals(tutor) && a.getStatus() == StatusAdocao.APROVADO).count() == 5)
//            throw  new AdocaoException("Tutor chegou ao limite máximo de 5 adoções!");

        Pet pet = petRepo.getReferenceById(dto.idPet());
        Tutor tutor = tutorRepo.getReferenceById(dto.idTutor());

        Adocao adocao = new Adocao(tutor, pet, dto.motivo());

        adocaoRepo.save(adocao);

        emailService.enviarEmail(
                 adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }

    @Transactional
    public void aprovar(AprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepo.getReferenceById(dto.idAdocao());

        adocao.definirComoAprovado();

        adocaoRepo.save(adocao); // opcional
        emailService.enviarEmail(
                adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " + adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }

    @Transactional
    public void reprovar(ReprovacaoAdocaoDto dto) {
        Adocao adocao = adocaoRepo.getReferenceById(dto.idAdocao());

        adocao.definirComoReprovado(dto.justificativa());
        adocaoRepo.save(adocao); // opcional

        emailService.enviarEmail(
                adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá \" + adocao.getTutor().getNome() +\"!\\n\\nInfelizmente sua adoção do pet \" +adocao.getPet().getNome() +\", solicitada em \" +adocao.getData().format(DateTimeFormatter.ofPattern(\"dd/MM/yyyy HH:mm:ss\")) +\", foi reprovada pelo abrigo \" + adocao.getPet().getAbrigo().getNome() + \" com a seguinte justificativa: \" + adocao.getJustificativaStatus()");
    }
}
