# API FizzBuzz

Esta es una API REST para generar secuencias FizzBuzz.

## Tabla de Contenidos
- [Introducción](#introducción)
- [Instalación](#instalación)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Base de datos](#Database)
- [Autor](#Autor)

## Introducción

La API FizzBuzz te permite generar secuencias FizzBuzz basadas en el rango de entrada proporcionado.

## Instalación

Para ejecutar la API FizzBuzz, sigue estos pasos:

1. Clona el repositorio: `git clone https://github.com/CristianEPT/fizzbuzz-api.git`
2. Navega al directorio del proyecto: `cd fizzbuzz-api`
3. Ejecuta el docker-compose `docker-compose up -d`
4. Construye el proyecto: `gradle clean build`
5. Ejecuta la aplicación: `java -jar target/fizzbuzz-api.jar`

## Uso

Una vez que la API esté en funcionamiento, puedes enviar solicitudes HTTP a los endpoints disponibles para generar secuencias FizzBuzz.

## Endpoints

La API FizzBuzz proporciona los siguientes endpoints:

- `GET /intraway/api/fizzbuzz/{min}/{max}`: Genera la secuencia FizzBuzz para el rango dado.

Consulta la documentación de la API para obtener información detallada sobre cómo utilizar estos endpoints.

## Database

Las configuraciones de la base de datos se encuentran en el archivo `properties.yml`. Asegúrate de proporcionar las configuraciones correctas antes de ejecutar la aplicación.

## Autor

Este archivo README.md fue creado por [Cristin Piña](https://github.com/CristianEPT).
