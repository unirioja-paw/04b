# AJAX

Para evitar errores CORS las páginas deben ser servidas, no por protocolo file://

Para tener un servidor simple hay varias alternativas

## Python (http.server)

```
python3 -m http.server 3000
```

## Tomcat

Copiar las páginas html dentro de un carpeta accesible dentro del proyecto maven para que sean servidas por Tomcat

