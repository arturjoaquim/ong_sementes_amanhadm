package br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.adapter;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.domain.ports.repository.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.EmployeeMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.models.rh.EmployeeModel;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.jpa.EmployeeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeJpaAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository jpaRepository;
    private final EmployeeMapper mapper;

    public EmployeeJpaAdapter(EmployeeJpaRepository jpaRepository, EmployeeMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeModel model = mapper.toModel(employee);
        EmployeeModel saved = jpaRepository.save(model);
        return mapper.toDomain(saved);
    }
}
