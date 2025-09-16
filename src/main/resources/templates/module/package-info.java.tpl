/**
 * Módulo ${MODULE_CAP} - Arquitectura Hexagonal
 * 
 * Este módulo sigue los principios de arquitectura hexagonal y Spring Modulith:
 * - domain/model/: Modelos de dominio ricos
 * - domain/port/in/: Puertos de entrada (casos de uso)
 * - domain/port/out/: Puertos de salida (repositorios, servicios externos)
 * - domain/service/: Implementaciones de casos de uso
 * - adapter/in/: Adaptadores de entrada (controladores, listeners)
 * - adapter/out/: Adaptadores de salida (persistencia, servicios externos)
 * 
 * @author Scaffolding Plugin
 */
package ${BASE_PACKAGE}.${MODULE};