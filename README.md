# Parrilla de programación televisiva.

La aplicación consisite en un servicio de obtención de vídeos ordenados de tal folrma que se asemeje a una televisión. Junto con ello ofrece búsqueda y asignación de vídeos automáticamente a la parrilla televisiva de YouTube.

# Recursos

### Obtención de la parrilla de distintas formas.

<details>
	<summary>
		<code>GET</code>
		<code><b>/emision</b></code>
		<code>(devuelve los vídeos en emisión)</code>
	</summary>

##### Parámetros

> Ninguno  

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | `text/json;charset=UTF-8` | Texto JSON |

###### Respuesta 200

```json
{
	"parrilla": [
		{
			"momento_inicial": "2004-12-05",
			"identificador_video": "https://localhost/video1",
			"miniatura": "https://localhost/miniatura1"
		},
		{
			"momento_inicial": "2004-12-15",
			"identificador_video": "https://localhost/video2",
			"miniatura": "https://localhost/miniatura2"
		}
	]
}
```

</details>


<details>
	<summary>
		<code>GET</code>
		<code><b>/parrilla</b></code>
		<code>(devuelve los vídeos de la parrilla de toda la semana)</code>
	</summary>

##### Parámetros

> Ninguno

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | `text/json;charset=UTF-8` | Texto JSON |

###### Respuesta 200

```json
{
	"parrilla": [
		{
			"momento_inicial": "2004-12-25",
			"identificador_video": "https://localhost/video1",
			"miniatura": "https://localhost/miniatura1"
		},
		{
			"momento_inicial": "2004-12-15",
			"identificador_video": "https://localhost/video2",
			"miniatura": "https://localhost/miniatura2"
		}
	]
}
```  

</details>

### Gestión de la programación.

Los programas son los encargados de asignar vídeos en la parrilla en el día y hora deseados en función de los criterios filtrados. Los vídeos se filtran desde canales o listas de vídeos, pudiendo especificar textos clave o excluir vídeos.

<details>
	<summary>
		<code>PUT</code>
		<code><b>/programa</b></code>
		<code>(añade un programa)</code>
	</summary>

##### Parámetros

> | nombre            |  tipo     | tipo de dato      | descripcion                         |
> |-------------------|-----------|----------------|-------------------------------------|
> | `momento_inicial` |  obligatorio | fecha   | Primer instante de emision del que a partir se repetirá.        |
> | `identificador_video` |  opcional | enlace   | Enlace del vídeo a programar. Activada esta opción, se anulan el resto de opciones de identificadores, búsqueda y orden.        |
> | `identificadores_de_lista` |  opcional | Lista de enlaces   | Lista de enlaces de listas de vídeos a programar.         |
> | `identificadores_de_canal` |  opcional | Lista de enlaces   | Lista de enlaces de listas de canales a programar.         |
> | `orden` |  opcional | texto   | Establece el orden del listado de videos, siendo por defecto DESCENDENTE. Solo admite: ASCENDENTE; DESCENDENTE.         |
> | `repetir` |  opcional | texto   | Establece el tipo de repetición de la programación, siendo por defecto NUNCA. Solo admite: NUNCA; DIARIAMENTE; SEMANALMENTE; MENSUALMENTE; ANUALMENTE.         |
> | `repetir_cada` |  opcional | entero   | Indica cada cuanto tiempo se repite diariamente, semanalmente, mensualmente o anualmente.        |
> | `dias_de_la_semana` |  opcional | Lista de caractéres   | Indica los días de la semana que se programa el vídeo. Se emplea la forma abreviada de los días de la semana en mayúsculas: L (lunes); M (martes); X (miércoles); J (jueves); V (viernes); S (sábado); D (domingo). Solo es válido con repetición SEMANALMENTE.         |
> | `coincidencia` |  opcional | texto   | Establece el tipo de coincidencia del día de repetición de la programación, siendo por defecto NUMERICA. Solo admite: NUMERICA; SEMANAL; ULTIMA_SEMANAL; ULTIMO_MENSUAL. Solo es válido con repetición MENSUALMENTE.          |
> | `duracion` |  opcional | texto   | Establece cuando finaliza la programación, siendo por defecto SIEMPRE. Solo admite: SIEMPRE; REPETICION; CADUCA.         |
> | `numero_de_repeticiones` |  opcional | entero   | Establece el número de repeticiones con los cuales finaliza la programación. Solo es válido con duracion REPETICION.         |
> | `caducidad` |  opcional | fecha   | Establece la fecha de caducidad de la programación. Solo es válido con duracion CADUCA.         |
> | `omitidos` |  opcional | Lista de enlaces   | Lista de vídeos que se omiten a incluir en la parrilla.         |

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `201`    | Vacío | Ninguna |
> |    `400`    |  `text/json;charset=UTF-8`  | `{"código":"400","mensaje":"Mala petición"}` |



</details>





<details>
	<summary>
		<code>POST</code>
		<code><b>/programa/${id}</b></code>
		<code>(edita un programa)</code>
	</summary>

##### Parámetros

> | nombre            |  tipo     | tipo de dato      | descripcion                         |
> |-------------------|-----------|----------------|-------------------------------------|
> | `id` |  obligatorio | entero   | Identificador del programa.        |
> | `momento_inicial` |  obligatorio | fecha   | Primer instante de emision del que a partir se repetirá.        |
> | `identificador_video` |  opcional | enlace   | Enlace del vídeo a programar. Activada esta opción, se anulan el resto de opciones de identificadores, búsqueda y orden.        |
> | `identificadores_de_lista` |  opcional | Lista de enlaces   | Lista de enlaces de listas de vídeos a programar.         |
> | `identificadores_de_canal` |  opcional | Lista de enlaces   | Lista de enlaces de listas de canales a programar.         |
> | `orden` |  opcional | texto   | Establece el orden del listado de videos, siendo por defecto DESCENDENTE. Solo admite: ASCENDENTE; DESCENDENTE.         |
> | `repetir` |  opcional | texto   | Establece el tipo de repetición de la programación, siendo por defecto NUNCA. Solo admite: NUNCA; DIARIAMENTE; SEMANALMENTE; MENSUALMENTE; ANUALMENTE.         |
> | `repetir_cada` |  opcional | entero   | Indica cada cuanto tiempo se repite diariamente, semanalmente, mensualmente o anualmente.        |
> | `dias_de_la_semana` |  opcional | Lista de caractéres   | Indica los días de la semana que se programa el vídeo. Se emplea la forma abreviada de los días de la semana en mayúsculas: L (lunes); M (martes); X (miércoles); J (jueves); V (viernes); S (sábado); D (domingo). Solo es válido con repetición SEMANALMENTE.         |
> | `coincidencia` |  opcional | texto   | Establece el tipo de coincidencia del día de repetición de la programación, siendo por defecto NUMERICA. Solo admite: NUMERICA; SEMANAL; ULTIMA_SEMANAL; ULTIMO_MENSUAL. Solo es válido con repetición MENSUALMENTE.          |
> | `duracion` |  opcional | texto   | Establece cuando finaliza la programación, siendo por defecto SIEMPRE. Solo admite: SIEMPRE; REPETICION; CADUCA.         |
> | `repeticiones` |  opcional | entero   | Establece el número de repeticiones con los cuales finaliza la programación. Solo es válido con duracion REPETICION.         |
> | `caducidad` |  opcional | fecha   | Establece la fecha de caducidad de la programación. Solo es válido con duracion CADUCA.         |
> | `omitidos` |  opcional | Lista de enlaces   | Lista de vídeos que se omiten a incluir en la parrilla.         |

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | Vacío | Texto JSON |
> |    `400`    |  `text/json;charset=UTF-8`  | `{"código":"400","mensaje":"Mala petición."}` |
> |    `404`    |  `text/json;charset=UTF-8`  | `{"código":"404","mensaje":"Programa no encontrado."}` |

###### Respuesta 200

```json
{
	"momento_inicial": "247873429",
	"identificador_video": "https://youtu.be/",
	"identificadores_de_lista": ["https://youtu.be/","https://www.youtube.com/"],
	"identificadores_de_canal": ["https://youtu.be/","https://www.youtube.com/"],
	"orden": "DESCENDENTE",
	"repetir": "NUNCA",
	"repetir_cada": 0,
	"dias_de_la_semana": ["L","X","V","S"],
	"coincidencia": "NUMERICA",
	"duracion": "SIEMPRE",
	"repeticiones": 0,
	"caducidad": "2892", 
	"excepciones": ["https:", "https"] 
}
```

</details>







<details>
	<summary>
		<code>GET</code>
		<code><b>/programa/{id}</b></code>
		<code>(obtiene un programa)</code>
	</summary>

##### Parámetros

> | nombre            |  tipo     | tipo de dato      | descripcion                         |
> |-------------------|-----------|----------------|-------------------------------------|
> | `id` |  obligatorio | entero   | Identificador del programa.        |

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | Vacío | Texto JSON |
> |    `400`    |  `text/json;charset=UTF-8`  | `{"código":"400","mensaje":"Mala petición."}` |
> |    `404`    |  `text/json;charset=UTF-8`  | `{"código":"404","mensaje":"Programa no encontrado."}` |

###### Respuesta 200

```json
{
	"momento_inicial": "247873429",
	"identificador_video": "https://youtu.be/",
	"identificadores_de_lista": ["https://youtu.be/","https://www.youtube.com/"],
	"identificadores_de_canal": ["https://youtu.be/","https://www.youtube.com/"],
	"orden": "DESCENDENTE",
	"repetir": "NUNCA",
	"repetir_cada": 0,
	"dias_de_la_semana": ["L","X","V","S"],
	"coincidencia": "NUMERICA",
	"duracion": "SIEMPRE",
	"repeticiones": 0,
	"caducidad": "2892", 
	"excepciones": ["https:", "https"] 
}
```

</details>









<details>
	<summary>
		<code>DELETE</code>
		<code><b>/programa/{id}</b></code>
		<code>(elimina un programa)</code>
	</summary>

##### Parámetros

> | nombre            |  tipo     | tipo de dato      | descripcion                         |
> |-------------------|-----------|----------------|-------------------------------------|
> | `id` |  obligatorio | entero   | Identificador del programa.        |

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | Vacío | Texto JSON |
> |    `400`    |  `text/json;charset=UTF-8`  | `{"código":"400","mensaje":"Mala petición."}` |
> |    `404`    |  `text/json;charset=UTF-8`  | `{"código":"404","mensaje":"Programa no encontrado."}` |

###### Respuesta 200

```json
{
	"momento_inicial": "247873429",
	"identificador_video": "https://youtu.be/",
	"identificadores_de_lista": ["https://youtu.be/","https://www.youtube.com/"],
	"identificadores_de_canal": ["https://youtu.be/","https://www.youtube.com/"],
	"orden": "DESCENDENTE",
	"repetir": "NUNCA",
	"repetir_cada": 0,
	"dias_de_la_semana": ["L","X","V","S"],
	"coincidencia": "NUMERICA",
	"duracion": "SIEMPRE",
	"repeticiones": 0,
	"caducidad": "2892", 
	"excepciones": ["https:", "https"] 
}
```

</details>


## Obtener calendario

<details>
	<summary>
		<code>GET</code>
		<code><b>/calendario</b></code>
		<code>(obtiene el calendario con todos los programas)</code>
	</summary>

##### Parámetros

> Ninguno

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | Vacío | Texto JSON | 

###### Respuesta 200

```json
calendario: [
	programa: {
		"id": 1
		"momento_inicial": "247873429",
		"identificador_video": "https://youtu.be/",
		"identificadores_de_lista": ["https://youtu.be/","https://www.youtube.com/"],
		"identificadores_de_canal": ["https://youtu.be/","https://www.youtube.com/"],
		"orden": "DESCENDENTE",
		"repetir": "NUNCA",
		"repetir_cada": 0,
		"dias_de_la_semana": ["L","X","V","S"],
		"coincidencia": "NUMERICA",
		"duracion": "SIEMPRE",
		"repeticiones": 0,
		"caducidad": "2892", 
		"excepciones": ["https:", "https"] 
	},
	programa: {
		"id": 2
		"momento_inicial": "247873429",
		"identificador_video": "https://youtu.be/",
		"identificadores_de_lista": ["https://youtu.be/","https://www.youtube.com/"],
		"identificadores_de_canal": ["https://youtu.be/","https://www.youtube.com/"],
		"orden": "DESCENDENTE",
		"repetir": "NUNCA",
		"repetir_cada": 0,
		"dias_de_la_semana": ["L","X","V","S"],
		"coincidencia": "NUMERICA",
		"duracion": "SIEMPRE",
		"repeticiones": 0,
		"caducidad": "2892", 
		"excepciones": ["https:", "https"] 
	}
]
```

</details>





### Obtención de vídeos de un programa.

<details>
	<summary>
		<code>GET</code>
		<code><b>/filtro/${id}</b></code>
		<code>(devuelve los vídeos filtrados de un programa)</code>
	</summary>

##### Parámetros

> | nombre            |  tipo     | tipo de dato      | descripcion                         |
> |-------------------|-----------|----------------|-------------------------------------|
> | `id` |  obligatorio | entero   | Identificador del programa.        |

##### Códigos de respuesta

> | Código HTTP | Tipo de contenido          | Respuesta                                                            |
> |-------------|----------------------------|----------------------------------------------------------------------|
> |    `200`    | Vacío | Texto JSON |
> |    `400`    |  `text/json;charset=UTF-8`  | `{"código":"400","mensaje":"Mala petición."}` |
> |    `404`    |  `text/json;charset=UTF-8`  | `{"código":"404","mensaje":"Programa no encontrado."}` |

###### Respuesta 200

```json
{
	"filtro": [
		video: {
			"momento_inicial": "2004-12-05",
			"identificador_video": "https://localhost/video1",
			"miniatura": "https://localhost/miniatura1",
			"duracion": 1349
		},
		video: {
			"momento_inicial": "2004-12-15",
			"identificador_video": "https://localhost/video2",
			"miniatura": "https://localhost/miniatura2",
			"duracion": 1349
		}
	]
}
```

</details>
