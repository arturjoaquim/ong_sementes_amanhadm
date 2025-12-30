package br.ong.sementesamanha.erp.modules.education.domain.types;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import org.springframework.jdbc.core.RowMapper;

public enum LookupType {
    POSITION("cargos", Mappers.DESCRIPTION),
    PERIOD("periodos", Mappers.DESCRIPTION),
    EDUCATION_STATUS("status_ensino", Mappers.DESCRIPTION),
    EDUCATION_LEVEL("niveis_ensino", Mappers.DESCRIPTION),
    KINSHIP("graus_parentesco", Mappers.DESCRIPTION),
    REGISTRATION_ORIGIN("origens_inscricao", Mappers.DESCRIPTION),
    GENDER("sexos", Mappers.SIGLA),
    COUNTRY("paises", Mappers.SIMPLE),
    WORKSHOP_TYPE("tipos_oficina", Mappers.DESCRIPTION),
    ECONOMIC_BENEFIT("beneficios_economicos", Mappers.DESCRIPTION),
    DOCUMENT_TYPE("tipos_documento", Mappers.DESCRIPTION),
    RACE("racas", Mappers.SIMPLE),
    MARITAL_STATUS("estados_civis", Mappers.SIMPLE),
    DOMICILE_TYPE("tipos_domicilio", Mappers.DESCRIPTION),
    MEDICAL_LOCATION("locais_acompanhamento_medico", Mappers.DESCRIPTION);

    private final String tableName;
    private final RowMapper<LookupDTO> rowMapper;

    LookupType(String tableName, RowMapper<LookupDTO> rowMapper) {
        this.tableName = tableName;
        this.rowMapper = rowMapper;
    }

    public String getTableName() {
        return tableName;
    }

    public RowMapper<LookupDTO> getRowMapper() {
        return rowMapper;
    }

    private static class Mappers {
        static final RowMapper<LookupDTO> DESCRIPTION = (rs, i) -> 
            new LookupDTO(rs.getLong("id"), rs.getString("nome"), rs.getString("descricao"));
            
        static final RowMapper<LookupDTO> SIGLA = (rs, i) -> 
            new LookupDTO(rs.getLong("id"), rs.getString("nome"), rs.getString("sigla"));
            
        static final RowMapper<LookupDTO> SIMPLE = (rs, i) -> 
            new LookupDTO(rs.getLong("id"), rs.getString("nome"), null);
    }
}
