package ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Create${MODULE_CAP}Request {
    @NotBlank(message = "El nombre es requerido")
    private String name;
    private String description;
    @NotNull(message = "El estado es requerido")
    private Boolean active;
}