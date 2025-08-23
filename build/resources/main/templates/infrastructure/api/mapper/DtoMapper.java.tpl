package ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.mapper;

import ${BASE_PACKAGE}.${MODULE}.domain.model.${MODULE_CAP}Model;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${MODULE_CAP}DtoMapper {
    ${MODULE_CAP}Model toModel(Create${MODULE_CAP}Request request);
    ${MODULE_CAP}Response toResponse(${MODULE_CAP}Model model);
}