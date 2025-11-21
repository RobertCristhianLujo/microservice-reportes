package com.example.microservicio_reportes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of(
                "status", "OK",
                "service", "microservicio-reportes",
                "message", "El servicio de reportes está funcionando correctamente."
        );
    }
}
