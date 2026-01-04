package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jdbc.LookupJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class LookupService {

    private final LookupJdbcRepository repository;
    private final Map<LookupTypeEnum, Map<Long, LookupDTO>> mapCache = new ConcurrentHashMap<>();
    private final Map<LookupTypeEnum, List<LookupDTO>> listCache = new ConcurrentHashMap<>();

    public LookupService(LookupJdbcRepository repository) {
        this.repository = repository;
    }

    public Map<Long, LookupDTO> getLookupAsMap(LookupTypeEnum type) {
        return mapCache.computeIfAbsent(type, k -> 
            repository.findAll(k).stream()
                .collect(Collectors.toMap(LookupDTO::getId, dto -> dto))
        );
    }

    public List<LookupDTO> getLookupAsList(LookupTypeEnum type) {
        return listCache.computeIfAbsent(type, repository::findAll);
    }
    
    public void clearCache() {
        mapCache.clear();
        listCache.clear();
    }
}
