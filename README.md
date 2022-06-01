# PruebaSoftware
Consultar documentación del api
http://localhost:8085/v1/prueba-software-api/swagger-ui.html

1- El primer valor del estatus de operación se ha definido en base de datos
como ALTA

2.- Si el tipo de mercancia es contenerizada se usa el siguiente link para
registrar el contenedor y la operación si ese es el caso:

http://localhost:8085/v1/prueba-software-api/contenedor/create

3.-La operación puede tener muchos contenedores, pero la operación no puede ser creada 
sin contenedores ya que en base de datos se hace una relación uno a muchos
y en el api no se expone la lógica para registrar operaciones a menos de que 
se registre un contenedor.

4.- Si el tipo de mercancía es carga suelta se permite registrar la operación 
junto con los datos que corresponden a una carga suelta.

http://localhost:8085/v1/prueba-software-api/c_suelta/create

5.- Creación de un metodo que capture la fecha de arribo/zarpe y cambie 
el estatus a ETA/ETD

consultar en el proyecto:
de la linea 26 - 50
package com.welldex.PruebaSoftware.service.impl.ContenedorServiceImpl;

de la linea 23 - 45
package com.welldex.PruebaSoftware.service.impl.CSueltaServiceImpl;


6.- crear un etodo que registre el descargo de carga

consultar en el proyecto:
de la linea 100 - 145
package com.welldex.PruebaSoftware.service.impl.ContenedorServiceImpl;
http://localhost:8085/v1/prueba-software-api/contenedor/descargar/{folioContenedor}

de la linea 82 - 119
package com.welldex.PruebaSoftware.service.impl.CSueltaServiceImpl;
http://localhost:8085/v1/prueba-software-api/c_suelta/descarga/{idCargaSuelta}/{cantidadDescargada}
