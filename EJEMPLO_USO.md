# 🚀 Ejemplo de Uso Completo - Spring Modulith Scaffolding

Este ejemplo muestra cómo crear un sistema de e-commerce modular usando el plugin de scaffolding.

## 1. Crear el proyecto base

```bash
./gradlew createProject --name=ecommerce --package=com.empresa.ecommerce
```

**Resultado**: Se crea la estructura base con Spring Modulith configurado.

## 2. Crear módulos de negocio

```bash
# Módulo de productos
./gradlew createModule --name=products

# Módulo de órdenes  
./gradlew createModule --name=orders

# Módulo de clientes
./gradlew createModule --name=customers

# Módulo de inventario
./gradlew createModule --name=inventory
```

**Resultado**: Cada módulo tiene su estructura hexagonal completa.

## 3. Agregar componentes al módulo de productos

```bash
# Modelo de dominio
./gradlew addComponent --module=products --type=model --name=Product

# Caso de uso para crear producto
./gradlew addComponent --module=products --type=usecase --name=CreateProduct

# Caso de uso para buscar productos
./gradlew addComponent --module=products --type=usecase --name=FindProduct

# Controlador REST
./gradlew addComponent --module=products --type=controller --name=Product

# Repositorio
./gradlew addComponent --module=products --type=repository --name=Product
```

## 4. Agregar componentes al módulo de órdenes

```bash
# Modelo de dominio
./gradlew addComponent --module=orders --type=model --name=Order

# Casos de uso
./gradlew addComponent --module=orders --type=usecase --name=CreateOrder
./gradlew addComponent --module=orders --type=usecase --name=CancelOrder
./gradlew addComponent --module=orders --type=usecase --name=ProcessOrder

# Controlador
./gradlew addComponent --module=orders --type=controller --name=Order

# Repositorio
./gradlew addComponent --module=orders --type=repository --name=Order
```

## 5. Verificar la arquitectura

```bash
# Ejecutar tests de Modulith
./gradlew test --tests="*ModulithTest"

# Compilar y verificar todo
./gradlew build
```

## 6. Estructura final generada

```
src/main/java/com/empresa/ecommerce/
├── Application.java                    # @Modulith
├── config/
│   └── GlobalConfig.java
├── products/                           # Módulo de productos
│   ├── package-info.java
│   ├── domain/
│   │   ├── model/
│   │   │   └── Product.java
│   │   ├── port/
│   │   │   ├── in/
│   │   │   │   ├── CreateProductUseCase.java
│   │   │   │   └── FindProductUseCase.java
│   │   │   └── out/
│   │   │       └── ProductRepositoryPort.java
│   │   └── service/
│   │       ├── CreateProductService.java
│   │       └── FindProductService.java
│   └── adapter/
│       ├── in/web/
│       │   ├── ProductController.java
│       │   └── dto/
│       │       ├── ProductRequest.java
│       │       └── ProductResponse.java
│       └── out/persistence/
│           ├── ProductPersistenceAdapter.java
│           ├── entity/
│           │   └── ProductEntity.java
│           └── repository/
│               └── ProductJpaRepository.java
├── orders/                             # Módulo de órdenes
│   └── ... (estructura similar)
├── customers/                          # Módulo de clientes
│   └── ... (estructura similar)
└── inventory/                          # Módulo de inventario
    └── ... (estructura similar)
```

## 7. Beneficios obtenidos

✅ **Arquitectura verificada**: Spring Modulith valida las dependencias entre módulos
✅ **Separación clara**: Cada módulo tiene su responsabilidad bien definida
✅ **Testeable**: Estructura preparada para testing unitario e integración
✅ **Documentación automática**: Diagramas PlantUML generados automáticamente
✅ **Escalable**: Fácil agregar nuevos módulos y componentes
✅ **Mantenible**: Código organizado siguiendo principios SOLID

## 8. Comandos útiles adicionales

```bash
# Ver ayuda completa
./gradlew scaffoldHelp

# Ver ayuda específica de componentes
./gradlew componentHelp

# Ver ayuda de módulos
./gradlew moduleHelp

# Ver ayuda de proyectos
./gradlew projectHelp
```

## 9. Próximos pasos

1. Implementar la lógica de negocio en los casos de uso
2. Configurar la base de datos en `application.yml`
3. Agregar validaciones en los DTOs
4. Implementar tests unitarios
5. Configurar CI/CD para el proyecto

¡Tu proyecto modular está listo para desarrollar! 🎉