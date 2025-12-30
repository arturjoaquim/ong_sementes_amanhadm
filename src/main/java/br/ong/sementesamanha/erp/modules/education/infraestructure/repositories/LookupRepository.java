package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.domain.types.LookupType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LookupRepository {

    private final JdbcTemplate jdbcTemplate;

    public LookupRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LookupDTO> findAll(LookupType type) {
        String sql = "SELECT * FROM lookup." + type.getTableName();
        return jdbcTemplate.query(sql, type.getRowMapper());
    }
}
