package br.ong.sementesamanha.erp.modules.education.domain.ports;

import br.ong.sementesamanha.erp.modules.education.domain.entities.IndividualPerson;
import br.ong.sementesamanha.erp.modules.education.domain.entities.Student;

public interface StudentServicePort {
    Student create(Student student, IndividualPerson person);
    Student update(Long id, Student student);
    void inactivate(Long id);
    Student findById(Long id);
}
