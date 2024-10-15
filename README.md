# Truco Game

## Descripción

**Truco** es un juego de cartas popular en varios países de América del Sur, especialmente en Argentina y Uruguay. Este programa implementa una versión digital del juego utilizando Java, permitiendo a los jugadores disfrutar de este clásico desde la comodidad de sus dispositivos.

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Cómo Ejecutar el Proyecto](#cómo-ejecutar-el-proyecto)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- Juego para 2 jugadores. (No implementado aún para 6)
- Reglas del Truco implementadas.
- Interfaz de usuario basada en consola.
- Barajado y reparto automático de cartas.
- Sistema de puntajes para llevar el control de cada partida.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal utilizado para la implementación.

## Estructura del Proyecto

```
TrucoGame/
├── src/
│   │── Clases/ 
│      ├── Carta.java
│      ├── Mazo.java
│      ├── Jugador.java
│      └── Partida.java
└── README.md
```

- **Carta.java**: Clase que representa una carta del mazo.
- **Mazo.java**: Clase que maneja la baraja de cartas.
- **Jugador.java**: Clase que representa a cada jugador y su mano.
- **Partida.java**: Clase que controla el flujo del juego y las rondas.

## Cómo Ejecutar el Proyecto

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/SantiMussi/TrucoGame.git
   cd TrucoGame
   ```

2. **Compila el proyecto**:
   ```bash
   javac src/*.java
   ```

3. **Ejecuta la partida**:
   ```bash
   java src/Partida
   ```

4. **Sigue las instrucciones en consola para jugar.**

## Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar este proyecto, sigue estos pasos:

1. Fork el repositorio.
2. Crea una nueva rama para tus cambios (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz un commit (`git commit -m 'Agrega una nueva característica'`).
4. Sube tus cambios (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Para más detalles, consulta el archivo `LICENSE`.

---

¡Disfruta jugando a Truco! 🎉
