
# Desarrollo ágil mediante CRUDs genericos

La finalidad de este servicio es ilustrar el uso de la libreria
'crudifier' con la que será posible levantar una capa de CRUDs sin
a penas necesidad de picar codigo, lo que nos permitirá centrarnos
en el diseño del modelo y manipular directamente los datos en fases
tempranas del desarrollo, sin necesidad de una interfaz grafica o
consultas directas contra la base de datos.

## API

### Create test entity

Request:

POST /testentity
```
{
	"name": "nueva entidad de prueba"
}
```

Response:

200:
```
{
    "id": <UUID>
    , "name": "nueva entidad de prueba"
}
```

### Update test entity

Request:

PUT /testentity/<UUID>
```
{
    "name": "nombre editado de entidad de prueba existente"
}
```

Response:

200:
```
{
    "id": <UUID>
    , "name": "nombre editado de entidad de prueba existente"
}
```

### Delete test entity

Request:

DELETE /testentity/<UUID>

Response:

200

### Find all test entity

Request:

GET /testentity[?\<filter>][&\<filter>...]

filter=
- \<entity attribute>=\<value>
- page=<0..N>
- size=<1..N>
- order=[-]\<entity attribute>

**Note**
- Filtering entity attributes by String values aplies a 'contains'
match, whilst numeric values apply an exact match.
- Filters 'page' and 'size' must be both present if one is.

Response:

200:
```
[
    {
        "id": <UUID>
        , "name": "primera entidad de prueba existente"
    }
    , {
        "id": <UUID>
        , "name": "segunda entidad de prueba existente"
    }
]
```

### Find one test entity

Request:

GET /testentity/<UUID>

Response:

200:
```
{
    "id": <UUID>
    , "name": "entidad de prueba existente"
}
```

## Compatibilidad

Este servicio tambien pretende demostrar la compatibilidad de la
libreria mencionada con otras estrategias, como el uso de DTOs/VOs
definidos explicitamente, o realizando el mapeo entre entidades y
éstos mediante otra herramienta distinta de Jackson (usado por
defecto en Spring). En este caso, la prueba de compatibilidad se
hará con Dozer, pero otros serían igualmente válidos.

### Adicional

En este caso concreto, el uso de Dozer respecto a delegar el mapeo
de entidades en las herramientas por defecto de Spring, implica la
necesidad de incorporar al proyecto lo siguiente:

- JavaConfig para Dozer
- JavaConfig para Spring (para indicar el paquete de Dozer, el resto es
por defecto)
- Estructura de datos (DTO/VO) especifica
- Metodo explicito y especifico (no generico) en el controlador
- Metodo explicito y especifico (no generico) en el servicio
- Fichero de configuracion de mapeos

## Conclusion

Teniendo en cuenta que la logica especifica escala de forma exponencial
(para cada CRUD seria necesario definir todos sus metodos del CRUD en el
controlador, el servicio, declarar DTOs/VOs y definir mapeos), cuantas
más entidades compongan el modelo, mayor será el coste de implementar y
mantener esta estrategia, por lo que valdria la pena considerar si es
recomendable aplicarla de forma sistematica, o por el contrario puntual,
cuando sea realmente necesario.