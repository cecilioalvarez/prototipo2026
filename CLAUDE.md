# Prototipo

Spring Boot + Thymeleaf + JPA (H2).

## Vistas

- Todas las vistas usan Bootstrap 5 y sus componentes estándar (table, card, alert, badge, form-control, btn...). Nada de estilos HTML sueltos como `border="1"`.
- Cada vista reutiliza los fragmentos de `src/main/resources/templates/fragments/layout.html`:
  - `<head th:replace="~{fragments/layout :: head('Título')}"></head>`
  - `<nav th:replace="~{fragments/layout :: navbar}"></nav>`
  - `<th:block th:replace="~{fragments/layout :: scripts}"></th:block>` al final del `body`
- El contenido va dentro de `<main class="container">`.
- Listados: `table table-striped table-hover` dentro de `table-responsive`; lista vacía con `alert alert-info`.
