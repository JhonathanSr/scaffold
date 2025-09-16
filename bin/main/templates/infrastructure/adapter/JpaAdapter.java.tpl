package ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.adapter;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import ${BASE_PACKAGE}.${MODULE}.domain.spi.${MODULE_CAP}Repository;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.mapper.${MODULE_CAP}Mapper;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.repository.${MODULE_CAP}JpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ${MODULE_CAP}JpaAdapter implements ${MODULE_CAP}Repository {
    private final ${MODULE_CAP}JpaRepository repository;
    private final ${MODULE_CAP}Mapper mapper;

    public ${MODULE_CAP}Model save(${MODULE_CAP}Model model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }
    public ${MODULE_CAP}Model findById(Long id) {
        return repository.findById(id).map(mapper::toModel).orElse(null);
    }
    public List<${MODULE_CAP}Model> findAll() {
        return repository.findAll().stream().map(mapper::toModel).toList();
    }
    public ${MODULE_CAP}Model update(${MODULE_CAP}Model model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }
    public void delete(Long id) { repository.deleteById(id); }
}