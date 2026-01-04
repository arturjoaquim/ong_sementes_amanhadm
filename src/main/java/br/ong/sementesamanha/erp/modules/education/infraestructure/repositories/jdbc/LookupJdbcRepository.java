package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jdbc;

import br.ong.sementesamanha.erp.modules.education.application.dtos.LookupDTO;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LookupJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public LookupJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LookupDTO> findAll(LookupTypeEnum type) {
        String sql = "SELECT * FROM lookup." + type.getTableName();
        return jdbcTemplate.query(sql, type.getRowMapper());
    }
}
