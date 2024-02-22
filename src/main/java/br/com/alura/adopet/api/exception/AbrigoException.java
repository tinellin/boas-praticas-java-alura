package br.com.alura.adopet.api.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AbrigoException extends ResponseStatusException {
    public AbrigoException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
