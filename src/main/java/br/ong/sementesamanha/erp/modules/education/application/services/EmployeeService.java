package br.ong.sementesamanha.erp.modules.education.application.services;

import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.CreateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.EmployeePreviewResponseDTO;
import br.ong.sementesamanha.erp.modules.education.application.dtos.employee.UpdateEmployeeDTO;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Employee;
import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.User;
import br.ong.sementesamanha.erp.modules.education.infraestructure.enums.LookupTypeEnum;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.EmployeeMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.mappers.IndividualPersonMapper;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.EmployeeRepository;
import br.ong.sementesamanha.erp.modules.education.infraestructure.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final IndividualPersonService personService;
    private final IndividualPersonMapper personMapper;
    private final UserRepository userRepository;
    private final LookupService lookupService;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           IndividualPersonService personService,
                           IndividualPersonMapper personMapper,
                           UserRepository userRepository,
                           LookupService lookupService,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.personService = personService;
        this.personMapper = personMapper;
        this.userRepository = userRepository;
        this.lookupService = lookupService;
        this.employeeMapper = employeeMapper;
    }

    @Transactional(readOnly = true)
    public Employee findByUserId(Long userId) {
        return employeeRepository.findBySystemUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found for user id: " + userId));
    }

    @Transactional(readOnly = true)
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<EmployeePreviewResponseDTO> getAllPreviews() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toPreview)
                .collect(Collectors.toList());
    }

    @Transactional
    public Employee create(CreateEmployeeDTO dto) {
        if (lookupService.getLookupAsMap(LookupTypeEnum.POSITION).get(dto.positionId()) == null) {
            throw new IllegalArgumentException("Position not found with id: " + dto.positionId());
        }
        
        User systemUser = null;
        if (dto.systemUserId() != null) {
            systemUser = userRepository.findById(dto.systemUserId())
                    .orElseThrow(() -> new IllegalArgumentException("System User not found with id: " + dto.systemUserId()));
        }

        IndividualPerson person = personMapper.toDomain(dto.person());
        IndividualPerson savedPerson = personService.create(person);

        Employee employee = new Employee();
        employee.setPerson(savedPerson);
        employee.setPositionId(dto.positionId());
        employee.setSystemUser(systemUser);

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee update(Long id, UpdateEmployeeDTO dto) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with id: " + id));

        if (dto.positionId() != null) {
            if (lookupService.getLookupAsMap(LookupTypeEnum.POSITION).get(dto.positionId()) == null) {
                throw new IllegalArgumentException("Position not found with id: " + dto.positionId());
            }
            existingEmployee.setPositionId(dto.positionId());
        }

        if (dto.systemUserId() != null) {
            User user = userRepository.findById(dto.systemUserId())
                    .orElseThrow(() -> new IllegalArgumentException("System User not found with id: " + dto.systemUserId()));
            existingEmployee.setSystemUser(user);
        }

        if (dto.person() != null && existingEmployee.getPerson() != null) {
            personService.update(existingEmployee.getPerson().getId(), dto.person());
        }

        return employeeRepository.save(existingEmployee);
    }

    @Transactional
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
