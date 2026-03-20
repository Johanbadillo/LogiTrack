# 🚚 LogiTrack S.A - Sistema de Gestión Logística

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/status-terminado-brightgreen?style=for-the-badge" alt="Estado">
</p>

<p align="center">
  <strong>

LogiTrack S.A. administraba el inventario de sus bodegas en hojas de cálculo: sin trazabilidad, sin control de accesos y con movimientos entre ciudades registrados manualmente. Cualquier modificación era invisible e irreversible.
LogiTrack es el sistema backend desarrollado en Java con Spring Boot para resolver eso. Expone una API REST con autenticación JWT, arquitectura limpia por capas y registro completo de cada movimiento de inventario: quién lo hizo, desde qué bodega, hacia dónde y cuándo.</strong>
</p>

---

## 🚀 Características del Sistema

- Autenticación y control de accesos con JWT
- Gestión de bodegas y productos por ciudad
- Registro de entradas, salidas y transferencias entre bodegas
- Trazabilidad completa: historial inmutable de cada movimiento
- Validación de stock antes de ejecutar cualquier transferencia
- Arquitectura por capas: Controller → Service → Repository → Model

## 📁 Estructura del Proyecto
```
📁 LogiTrack/
├── auth/               # Autenticación y autorización de usuarios
├── config/             # Configuración general de la aplicación
├── controller/         # Controladores REST - reciben y responden peticiones HTTP
├── dto/                # Objetos de transferencia de datos (Data Transfer Objects)
├── enums/              # Enumeraciones y constantes del sistema
├── exception/          # Manejo centralizado de excepciones
├── mapper/             # Conversión entre entidades y DTOs
├── model/              # Entidades del dominio (clases principales del negocio)
├── repository/         # Acceso y consulta a la base de datos
├── service/            # Lógica de negocio del sistema
└── LogiTrackApplication.java   # Punto de entrada de la aplicación
```

## 🛠️ Requisitos Previos

- Java JDK 17 o superior
- Maven 3.8+
- IntelliJ IDEA (recomendado) o cualquier IDE compatible con Java

## 🚀 Cómo Usar el Proyecto

1. Clona o descarga este repositorio:
```bash
git clone https://github.com/Johanbadillo/LogiTrack.git
```

2. Abre el proyecto en IntelliJ IDEA:
```
File > Open > Selecciona la carpeta LogiTrack/
```

3. Ejecuta la aplicación desde la clase principal:
```bash
O directamente desde IntelliJ haciendo clic en ▶️ sobre `LogiTrackApplication.java`
```
### La visualizacion de pruebas en una vista de usuario

[LogiTrack](https://github.com/Johanbadillo/Front_LogiTrack)

### NOTA

Asegúrate de tener el JDK correctamente instalado y configurado en las variables de entorno de tu sistema antes de ejecutar el proyecto.

Si utilizas IntelliJ IDEA, el proyecto puede abrirse directamente desde la carpeta raíz sin necesidad de configuración adicional,
ya que los archivos `.idea` están incluidos en el repositorio.

## 👨‍💻 Autor

<div align="center">

**Hecho con 🚛 y ❤️ por Johan Monsalve**

[![GitHub](https://img.shields.io/badge/GitHub-Johanbadillo-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Johanbadillo)

</div>
