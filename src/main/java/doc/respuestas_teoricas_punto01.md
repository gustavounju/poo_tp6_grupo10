# Trabajo Práctico Nº 6 — Punto 1: Justificaciones Teóricas

### a. ¿Cuántos atributos y operaciones tiene la clase PorHora y Mensual?
* **Clase `PorHora`:**
  * **Atributos propios:** 2 (`cupon: Cupon` y `valorHora: double`).
  * **Atributos heredados de `RegistroIngresoSalida`:** 5 (`id`, `fecha`, `hora`, `vehiculo`, `estado`).
  * **Total de atributos:** 7.
  * **Operaciones:** Implementa/sobrescribe `obtenerImporte(): Double`, hereda `cambiarEstado(estado: String)` de la clase base, más sus constructores, getters y setters correspondientes.

* **Clase `Mensual`:**
  * **Atributos propios:** 1 (`cliente: Cliente`).
  * **Atributos heredados de `RegistroIngresoSalida`:** 5 (`id`, `fecha`, `hora`, `vehiculo`, `estado`).
  * **Total de atributos:** 6.
  * **Operaciones:** Implementa/sobrescribe `obtenerImporte(): Double` (retornando 0), hereda `cambiarEstado(estado: String)` de la clase base, más sus constructores, getters y setters correspondientes.

---

### b. ¿Un ingreso por hora es un tipo de RegistroIngresoSalida?
**Sí.** La relación modelada en el diagrama es de **herencia / generalización** (representada con la flecha de triángulo cerrado hacia `RegistroIngresoSalida`). Esto cumple con el principio de *"es un"* (*is-a*): una instancia de `PorHora` es una especialización de `RegistroIngresoSalida`.

---

### c. ¿Un cliente es un tipo de RegistroIngresoSalida?
**No.** `Cliente` no hereda de `RegistroIngresoSalida`. Entre la subclase `Mensual` y `Cliente` existe una relación de **agregación / asociación** (*tiene un* / *has-a*), lo que significa que un registro mensual contiene una referencia a un cliente, pero no comparte su jerarquía ni su tipo.

---

### d. ¿Cuántas clases abstractas y métodos abstractos hay?
* **Clases abstractas:** Hay **1** (`RegistroIngresoSalida`, denotada en cursiva en el diagrama).
* **Métodos abstractos:** Hay **1** (`obtenerImporte(): Double`), el cual no posee implementación en la clase base y delega obligatoriamente su lógica a las subclases derivadas (`PorHora` y `Mensual`).

---

### e. ¿El método obtenerImporte() es necesario implementarlo en las subclases de RegistroIngresoSalida?
**Sí.** Al ser un método abstracto definido en una clase abstracta, toda subclase concreta no abstracta que herede de ella (`PorHora` y `Mensual`) está obligada por las reglas del lenguaje Java a proporcionar una implementación concreta de dicho método (a menos que la subclase fuese también declarada abstracta).

---

### f. ¿El método registrarIngreso(registro: RegistroIngresoSalida) es necesario implementarlo en las subclases de Manager?
**No.** En primer lugar, según el diagrama de clases, la clase `Manager` es una clase concreta y no posee subclases. En segundo lugar, `registrarIngreso` es un método concreto implementado directamente en `Manager` que opera de forma polimórfica recibiendo cualquier objeto que sea subtipo de `RegistroIngresoSalida`.

---

### g. ¿Vehiculo es una subclase de RegistroIngresoSalida?
**No.** `Vehiculo` es una clase independiente asociada mediante agregación/asociación con `RegistroIngresoSalida` (cardinalidad `1..*`). `RegistroIngresoSalida` *posee una referencia* a un vehículo (`vehiculo: Vehiculo`), pero no existe herencia entre ellos.