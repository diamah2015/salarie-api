package com.craftteam.salarie.config;

import com.craftteam.salarie.model.Salarie;
import com.craftteam.salarie.service.SalarieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {

    private final SalarieService salarieService;

    public DataLoader(SalarieService salarieService) {
        this.salarieService = salarieService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadSalaries() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // <<<<<< IMPORTANT
        
        InputStream inputStream = getClass().getResourceAsStream("/salarie.json");
        List<Salarie> salaries = mapper.readValue(inputStream, new TypeReference<List<Salarie>>() {});
        salarieService.setSalaries(salaries);
    }
}
