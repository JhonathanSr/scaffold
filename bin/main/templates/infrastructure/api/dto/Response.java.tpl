package ${BASE_PACKAGE}.${MODULE}.infrastructure.entrypoint.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${MODULE_CAP}Response {
    private Long id;
    private String name;
    private String description;
    private boolean active;
}