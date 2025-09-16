package ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.repository;

import ${BASE_PACKAGE}.${MODULE}.infrastructure.drivenadapter.jpa.entity.${MODULE_CAP}Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ${MODULE_CAP}JpaRepository extends JpaRepository<${MODULE_CAP}Entity, Long> {}