package com.example.microservicio_reportes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioClientService {

    private final RestTemplate restTemplate;
    private final String usuariosBaseUrl;

    public UsuarioClientService(RestTemplate restTemplate,
                                @Value("${usuarios.service.base-url}") String usuariosBaseUrl) {
        this.restTemplate = restTemplate;
        this.usuariosBaseUrl = usuariosBaseUrl;
    }

    /**
     * Llama al microservicio de usuarios y devuelve la lista cruda
     * como List<Map<String, Object>> para evitar problemas de mapeo.
     */
    public List<Map<String, Object>> obtenerTodosLosUsuarios() {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                usuariosBaseUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        if (response.getBody() == null) {
            return Collections.emptyList();
        }

        return response.getBody();
    }
}
