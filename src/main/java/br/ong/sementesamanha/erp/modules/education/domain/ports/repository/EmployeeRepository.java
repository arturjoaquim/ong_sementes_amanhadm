package br.ong.sementesamanha.erp.modules.education.domain.ports.repository;

import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(Long id);
    Employee save(Employee employee);
}
