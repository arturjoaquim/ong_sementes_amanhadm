package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.domain.types.LookupType;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.LookupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LookupService {

    private final LookupRepository repository;
    private final Map<LookupType, List<LookupDTO>> cache = new ConcurrentHashMap<>();

    public LookupService(LookupRepository repository) {
        this.repository = repository;
    }

    public List<LookupDTO> getLookup(LookupType type) {
        return cache.computeIfAbsent(type, repository::findAll);
    }
    
    public void clearCache() {
        cache.clear();
    }
}
