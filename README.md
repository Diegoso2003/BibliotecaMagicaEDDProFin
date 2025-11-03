# BibliotecaMagicaEDDProFin
Proyecto final del curso de Estructura de datos segundo semestre del año 2025

# BibliotecaMagica

Aplicación de gestión de biblioteca desarrollada en JavaFX con Maven.

## Requisitos Previos

### Instalación de Maven

#### Para Linux (Debian/Ubuntu)
```bash
sudo apt update
sudo apt install maven
```

#### Para Windows
Descargar desde: https://maven.apache.org/download.cgi
Agregar Maven al PATH del sistema.

#### Para macOS
```bash
brew install maven
```

### Instalación de Graphviz
Necesario para visualizar los diagramas de los árboles.

#### Para Linux (Debian/Ubuntu)
```bash
sudo apt update && sudo apt install graphviz
```

#### Para Windows
Descargar desde: https://graphviz.org/download/
Agregar Graphviz al PATH del sistema.

#### Para macOS
```bash
brew install graphviz
```

## Compilar y Generar la Aplicación

### Generar image ejecutable con jlink
Desde la raíz del proyecto, ejecutar:
```bash
mvn clean javafx:jlink
```

## Ejecutar la Aplicación

### Después de compilar con jlink:

#### Linux/macOS
```bash
cd target/BibliotecaMagica/bin/
chmod +x BibliotecaMagicaProFin
./BibliotecaMagicaProFin
```

#### Windows
```cmd
cd target\BibliotecaMagica\bin\
BibliotecaMagicaProFin.bat
```

O simplemente hacer doble clic en `BibliotecaMagicaProFin.bat`

## Estructura del Proyecto
```
BibliotecaMagica/
├── src/
│   └── main/
│       ├── java/
│       │   ├── module-info.java
│       │   └── com/mycompany/bibliotecamagica/
│       └── resources/
├── target/
│   └── BibliotecaMagica/          (generado por jlink)
│       ├── bin/
│       │   └── BibliotecaMagicaProFin
│       ├── conf/
│       ├── legal/
│       └── lib/
└── pom.xml
```

## Tecnologías Utilizadas

- Java 22
- JavaFX 21.0.8
- Maven
- Graphviz
