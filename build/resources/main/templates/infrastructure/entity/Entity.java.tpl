package ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "${MODULE}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ${MODULE_CAP}Entity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean active;
}