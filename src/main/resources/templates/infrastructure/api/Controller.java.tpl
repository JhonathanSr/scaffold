package ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api;

import ${BASE_PACKAGE}.${MODULE}.domain.usecase.${MODULE_CAP}UseCase;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.dto.*;
import ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.mapper.${MODULE_CAP}DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/${MODULE}")
@RequiredArgsConstructor
public class ${MODULE_CAP}Controller {
    private final ${MODULE_CAP}UseCase useCase;
    private final ${MODULE_CAP}DtoMapper mapper;

    @PostMapping
    public ResponseEntity<${MODULE_CAP}Response> create(@Valid @RequestBody Create${MODULE_CAP}Request request) {
        return ResponseEntity.ok(mapper.toResponse(useCase.create(mapper.toModel(request))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<${MODULE_CAP}Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toResponse(useCase.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<${MODULE_CAP}Response>> getAll() {
        return ResponseEntity.ok(useCase.findAll().stream().map(mapper::toResponse).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<${MODULE_CAP}Response> update(@PathVariable Long id, @Valid @RequestBody Create${MODULE_CAP}Request request) {
        var model = mapper.toModel(request);
        model.setId(id);
        return ResponseEntity.ok(mapper.toResponse(useCase.update(model)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}