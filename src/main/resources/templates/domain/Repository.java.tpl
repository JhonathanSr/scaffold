package ${BASE_PACKAGE}.${MODULE}.domain.spi;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import java.util.List;

public interface ${MODULE_CAP}Repository {
    ${MODULE_CAP}Model save(${MODULE_CAP}Model model);
    ${MODULE_CAP}Model findById(Long id);
    List<${MODULE_CAP}Model> findAll();
    ${MODULE_CAP}Model update(${MODULE_CAP}Model model);
    void delete(Long id);
}