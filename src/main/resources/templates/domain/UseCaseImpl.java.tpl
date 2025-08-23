package ${BASE_PACKAGE}.${MODULE}.domain.usecase;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import ${BASE_PACKAGE}.${MODULE}.domain.spi.${MODULE_CAP}Repository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class ${MODULE_CAP}UseCaseImpl implements ${MODULE_CAP}UseCase {
    private final ${MODULE_CAP}Repository repository;

    public ${MODULE_CAP}Model create(${MODULE_CAP}Model model) { return repository.save(model); }
    public ${MODULE_CAP}Model findById(Long id) { return repository.findById(id); }
    public List<${MODULE_CAP}Model> findAll() { return repository.findAll(); }
    public ${MODULE_CAP}Model update(${MODULE_CAP}Model model) { return repository.update(model); }
    public void delete(Long id) { repository.delete(id); }
}