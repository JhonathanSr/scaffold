package ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.mapper;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.entity.${MODULE_CAP}Entity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${MODULE_CAP}Mapper {
    ${MODULE_CAP}Entity toEntity(${MODULE_CAP}Model model);
    ${MODULE_CAP}Model toModel(${MODULE_CAP}Entity entity);
}