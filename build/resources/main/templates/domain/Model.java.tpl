package ${BASE_PACKAGE}.${MODULE}.domain.model;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ${MODULE_CAP}Model {
    private Long id;
    private String name;
    private String description;
    private boolean active;
}