package ${BASE_PACKAGE}.${MODULE}.domain.usecase;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import java.util.List;

public interface ${MODULE_CAP}UseCase {
    ${MODULE_CAP}Model create(${MODULE_CAP}Model model);
    ${MODULE_CAP}Model findById(Long id);
    List<${MODULE_CAP}Model> findAll();
    ${MODULE_CAP}Model update(${MODULE_CAP}Model model);
    void delete(Long id);
}