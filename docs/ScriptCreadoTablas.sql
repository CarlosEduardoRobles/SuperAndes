create sequence SuperAndes_sequence;

-- Crear tablas
CREATE TABLE Producto
(
    codigoDeBarras VARCHAR(80) NOT NULL,
    nombre VARCHAR(80),
    presentacion VARCHAR(80),
    marca VARCHAR(80),
    cantidad INTEGER  CHECK (cantidad > 0),
    unidadDeMedida VARCHAR(80),
    especificacionEmpacado VARCHAR(80),
    idCategoria INTEGER NOT NULL,
    CONSTRAINT producto_pk PRIMARY KEY(codigoDeBarras)
);

CREATE TABLE Sucursal
(
    idSucursal INTEGER NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    segmentacion VARCHAR(80),
    tamano VARCHAR(80) NOT NULL,
    ciudad VARCHAR(80) NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    CONSTRAINT sucursal_pk PRIMARY KEY(idSucursal)
);

CREATE TABLE ProductoOfrecidoSucursal
(
    idSucursal INTEGER NOT NULL, 
    codigoBarras VARCHAR(80) NOT NULL,
    precioUnitario INTEGER CHECK (precioUnitario >= 0),
    precioUnidadMedida INTEGER CHECK (precioUnidadMedida >= 0),
    nivelDeReorden INTEGER CHECK (nivelDeReorden > 0) ,
    cantidadRecompra INTEGER CHECK (cantidadRecompra > 0),
    CONSTRAINT productoofrecidosucursal_pk PRIMARY KEY(idSucursal, codigoBarras)
);

CREATE TABLE Estante
(
    id INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,   
    idCategoria INTEGER NOT NULL,
    volumenActual NUMBER NOT NULL CHECK (volumenActual > 0),
    volumenMaximo NUMBER NOT NULL CHECK (volumenMaximo > 0),
    pesoActual NUMBER NOT NULL CHECK (pesoActual > 0),
    pesoMaximo NUMBER NOT NULL CHECK (pesoMaximo > 0),
    nivelDeAbastecimiento INTEGER NOT NULL CHECK (nivelDeAbastecimiento > 0),
    CONSTRAINT estante_pk PRIMARY KEY(idSucursal, id)
);

CREATE TABLE ProductosEstante
(
    idEstante INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    codigoBarras VARCHAR(80) NOT NULL,
    cantidadProducto INTEGER NOT NULL CHECK (cantidadProducto >= 0),
    CONSTRAINT productosestante_pk PRIMARY KEY (idEstante, idSucursal, codigoBarras)
);

CREATE TABLE Bodega
(    
    id INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    idCategoria INTEGER,
    volumenActual NUMBER NOT NULL CHECK (volumenActual > 0),
    volumenMaximo NUMBER NOT NULL CHECK (volumenMaximo > 0),
    pesoActual NUMBER NOT NULL CHECK (pesoActual > 0),
    pesoMaximo NUMBER NOT NULL CHECK (pesoMaximo > 0),
    CONSTRAINT bodega_pk PRIMARY KEY(idSucursal, id)
);

CREATE TABLE ProductosBodega
(
    idBodega INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    codigoBarras VARCHAR(80) NOT NULL,
    cantidadProducto INTEGER NOT NULL CHECK (cantidadProducto >= 0),
    CONSTRAINT productosbodega_pk PRIMARY KEY (idBodega, idSucursal, codigoBarras)
);

CREATE TABLE Categoria
(
    idCategoria INTEGER NOT NULL,
    tipoCat VARCHAR(80),
    CONSTRAINT categoria_pk PRIMARY KEY(idCategoria)
);

CREATE TABLE Pedido
(
    codigoPedido INTEGER NOT NULL,
    fechaEntrega DATE NOT NULL,
    precioTotal INTEGER NOT NULL CHECK (precioTotal >= 0),
    estadoOrden VARCHAR(80) NOT NULL CHECK (estadoOrden LIKE 'En progreso' OR estadoOrden LIKE 'Finalizado'),
    NitProveedor VARCHAR(80) NOT NULL,
    idSucursal INTEGER NOT NULL,
    CONSTRAINT pedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE ProductoPedido
(
    codigoProducto VARCHAR(80) NOT NULL,
    codigoPedido INTEGER NOT NULL,
    volumen INTEGER CHECK (volumen > 0),
    precio INTEGER CHECK (precio > 0),
    CONSTRAINT productopedido_pk PRIMARY KEY(codigoProducto, codigoPedido)
);

CREATE TABLE Proveedor
(
    NIT VARCHAR(80) NOT NULL,
    nombre VARCHAR(80),
    CONSTRAINT proveedor_pk PRIMARY KEY(NIT)
);

CREATE TABLE ProveedorProducto
(
    NitProveedor VARCHAR(80) NOT NULL,
    codigoProducto VARCHAR(80) NOT NULL,
    precio INTEGER CHECK ( precio >= 0 ),
    calificacionCalidad VARCHAR(80) CHECK (calificacionCalidad >= 0 AND calificacionCalidad <= 5),
    CONSTRAINT proveedorproducto_pk PRIMARY KEY(NitProveedor, codigoProducto)
);

CREATE TABLE LlegadaPedido
(
    codigoPedido INTEGER NOT NULL,
    idsucursal INTEGER NOT NULL,
    fechaEntrega DATE NOT NULL,
    cantidadProductos INTEGER CHECK (cantidadProductos > 0),
    calidadProductos VARCHAR(80),
    calificacion VARCHAR(80),
    CONSTRAINT llegadapedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE Cliente
(
    tipoDocumento VARCHAR(80) NOT NULL,
    numDocumento VARCHAR(80) NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    correo VARCHAR(80) NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE PersonaJuridica
(
    tipoDocumento VARCHAR(80) NOT NULL CHECK (tipoDocumento IN ('NIT')),
    numDocumento VARCHAR(80) NOT NULL,
    direccion VARCHAR(80) NOT NULL,
    CONSTRAINT personajuridica_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE Venta
(
    numeroVenta INTEGER NOT NULL,
    tipoDocCliente VARCHAR(80) NOT NULL,
    numDocCliente VARCHAR(80) NOT NULL,
    idSucursal INTEGER NOT NULL,
    fechaVenta DATE NOT NULL,
    totalVenta INTEGER CHECK (totalVenta > 0),    
    CONSTRAINT venta_pk PRIMARY KEY(numeroVenta)
);

CREATE TABLE VentaProducto
(
    numeroVenta INTEGER NOT NULL,
    codigoProducto VARCHAR(80) NOT NULL,
    unidades INTEGER CHECK (unidades > 0),
    CONSTRAINT ventaproducto_pk PRIMARY KEY(numeroVenta, codigoProducto)
);

CREATE TABLE Promocion
(
    codigoPromocion VARCHAR(80) NOT NULL,
    tipoPromocion INTEGER NOT NULL,
    fechaTerminacion DATE NOT NULL,
    CONSTRAINT promocion_pk PRIMARY KEY(codigoPromocion)
);



CREATE TABLE VentaPromocion
(
    numeroVenta INTEGER NOT NULL,
    codigoPromo VARCHAR(80) NOT NULL,
    unidades INTEGER CHECK (unidades > 0),
    CONSTRAINT ventapromocion_pk PRIMARY KEY(numeroVenta, codigoPromo)
);

CREATE TABLE ProductoPromocion
(
    codigoPromocion VARCHAR(80) NOT NULL,
    codigoProducto VARCHAR(80) NOT NULL,
    CONSTRAINT productopromocion_pk PRIMARY KEY(codigoPromocion, codigoProducto)
);

CREATE TABLE PagueNUnidadesLleveMPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    compraUnidades INTEGER NOT NULL,
    llevaUnidades INTEGER NOT NULL CHECK (llevaUnidades > 0),
    CONSTRAINT paguenunidadesllevempromo_pk PRIMARY KEY(codigoPromo)
);


CREATE TABLE PaqueteDeProductosPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    precioPromo INTEGER NOT NULL CHECK (precioPromo >= 0),
    CONSTRAINT paquetedeproductospromo_pk PRIMARY KEY (codigoPromo)
);

CREATE TABLE PagueXCantidadLleveYPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    cantidadPaga INTEGER NOT NULL CHECK (cantidadPaga >= 0),
    cantidadLleva INTEGER NOT NULL,
    CONSTRAINT paguexcantidadlleveypromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE DescPorcentajePromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    porcentajeDesc INTEGER CHECK ( porcentajeDesc > 0),
    CONSTRAINT descporcentajepromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE Pague1Lleve2ConDescPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    porcentajeDesc INTEGER CHECK ( porcentajeDesc > 0),
    CONSTRAINT pague1lleve2condescpromo_pk PRIMARY KEY(codigoPromo)
);


-- Crear llaves foraneas

ALTER TABLE PaqueteDeProductosPromo
    ADD FOREIGN KEY (codigoPromo)
    REFERENCES Producto(codigoDeBarras)
    ON DELETE CASCADE
;

ALTER TABLE Bodega
    ADD FOREIGN KEY (idCategoria)
    REFERENCES Categoria(idCategoria)
;  

ALTER TABLE Estante
    ADD FOREIGN KEY (idCategoria)
    REFERENCES Categoria(idCategoria)
;    
ALTER TABLE Pedido 
    ADD FOREIGN KEY  (idSucursal)
    REFERENCES Sucursal(idSucursal)
    ON DELETE CASCADE
;
    
ALTER TABLE VentaProducto
    ADD    FOREIGN KEY (numeroVenta)
    REFERENCES Venta(numeroVenta)
    ON DELETE CASCADE
;
    
ALTER TABLE VentaPromocion
    ADD    FOREIGN KEY (numeroVenta)
    REFERENCES Venta(numeroVenta)
    ON DELETE CASCADE
;
    
ALTER TABLE PersonaJuridica
    ADD    FOREIGN KEY (tipoDocumento, numDocumento)
    REFERENCES Cliente(tipoDocumento, numDocumento)
    ON DELETE CASCADE
;
    
ALTER TABLE PagueNUnidadesLleveMPromo
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;
    
ALTER TABLE VentaPromocion
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion
    ON DELETE CASCADE
;
    
ALTER TABLE DescPorcentajePromo
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;
    
ALTER TABLE Pague1Lleve2ConDescPromo
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;

ALTER TABLE PagueXCantidadLleveYPromo
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;    

ALTER TABLE PagueXCantidadLleveYPromo
    ADD CHECK (cantidadLleva > cantidadPaga)
;

ALTER TABLE ProductoPromocion
    ADD    FOREIGN KEY (codigoPromocion)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;
    
ALTER TABLE VentaProducto
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
;
    
ALTER TABLE ProveedorProducto
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
    ON DELETE CASCADE
;
    
ALTER TABLE ProveedorProducto
    ADD    FOREIGN KEY (NitProveedor)
    REFERENCES Proveedor(NIT)
    ON DELETE CASCADE
;
    
ALTER TABLE Pedido
    ADD    FOREIGN KEY (NitProveedor)
    REFERENCES Proveedor(NIT)
    ON DELETE CASCADE
;
    
ALTER TABLE ProductoPromocion
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
    ON DELETE CASCADE
;
    
    
ALTER TABLE ProductoOfrecidoSucursal
    ADD    FOREIGN KEY (codigoBarras)
    REFERENCES Producto(codigoDeBarras)
    ON DELETE CASCADE
;
    
ALTER TABLE ProductoPedido
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
    ON DELETE CASCADE
;
    
    
ALTER TABLE ProductoOfrecidoSucursal
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
    ON DELETE CASCADE
;
    
ALTER TABLE Estante
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
    ON DELETE CASCADE
;

ALTER TABLE Estante
    ADD CHECK (pesoActual <= pesoMaximo AND volumenActual <= volumenMaximo)
;
    
ALTER TABLE Bodega
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
    ON DELETE CASCADE
;

ALTER TABLE Bodega
    ADD CHECK (pesoActual <= pesoMaximo AND volumenActual <= volumenMaximo)
;
    
ALTER TABLE LlegadaPedido
    ADD    FOREIGN KEY (idsucursal)
    REFERENCES Sucursal(idSucursal)
;
    
ALTER TABLE ProductoPedido
    ADD    FOREIGN KEY (codigoPedido)
    REFERENCES Pedido(codigoPedido)
    ON DELETE CASCADE
;
    
ALTER TABLE LlegadaPedido
    ADD    FOREIGN KEY (codigoPedido)
    REFERENCES Pedido(codigoPedido)
;
    
ALTER TABLE Venta
    ADD    FOREIGN KEY (tipoDocCliente, numDocCliente)
    REFERENCES Cliente(tipoDocumento, numDocumento)
;        

ALTER TABLE Venta
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
;        

ALTER TABLE Producto
    ADD FOREIGN KEY (idCategoria)
    REFERENCES Categoria(idCategoria)
    ON DELETE CASCADE
;

ALTER TABLE ProductosBodega
    ADD FOREIGN KEY (idSucursal, idBodega)
    REFERENCES Bodega (idSucursal, id)
    ON DELETE CASCADE
;

ALTER TABLE ProductosBodega
    ADD FOREIGN KEY (idSucursal , codigoBarras)
    REFERENCES ProductoOfrecidoSucursal (idSucursal , codigoBarras)
    ON DELETE CASCADE
;

ALTER TABLE ProductosEstante
    ADD FOREIGN KEY (idSucursal, idEstante)
    REFERENCES Estante (idSucursal, id)  
    ON DELETE CASCADE 
;

ALTER TABLE ProductosEstante
 ADD FOREIGN KEY (idSucursal , codigoBarras)
    REFERENCES ProductoOfrecidoSucursal (idSucursal , codigoBarras)
    ON DELETE CASCADE
;

--Gatillos, Revisan que no se creen fechas anteriores a la actual.
CREATE OR REPLACE TRIGGER check_dates_pedido
  BEFORE INSERT OR UPDATE ON Pedido
  FOR EACH ROW
BEGIN
  IF( :new.fechaEntrega <= sysdate - 1)
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Fecha Invalidad: La fecha debe ser mayor o igual a la actual - ingresado = ' || 
          to_char( :new.fechaEntrega, 'dd/mm/yyyy HH24:MI:SS' ) );
  END IF; 
END;
/

CREATE OR REPLACE TRIGGER check_dates_promocion
  BEFORE INSERT OR UPDATE ON Promocion
  FOR EACH ROW
BEGIN
  IF( :new.fechaTerminacion <= sysdate - 1)
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Fecha Invalidad: La fecha debe ser mayor o igual a la actual - ingresado = ' || 
          to_char( :new.fechaTerminacion, 'dd/mm/yyyy HH24:MI:SS' ) );
  END IF; 
END;
/

CREATE OR REPLACE TRIGGER check_dates_venta
  BEFORE INSERT OR UPDATE ON Venta
  FOR EACH ROW
BEGIN
  IF( :new.fechaVenta <= sysdate - 1)
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Fecha Invalidad: La fecha debe ser mayor o igual a la actual - ingresado = ' || 
          to_char( :new.fechaVenta, 'dd/mm/yyyy HH24:MI:SS' ) );
  END IF; 
END;
/

CREATE OR REPLACE TRIGGER check_dates_llegada_pedido
  BEFORE INSERT OR UPDATE ON LlegadaPedido
  FOR EACH ROW
BEGIN
  IF( :new.fechaEntrega <= sysdate - 1)
  THEN
    RAISE_APPLICATION_ERROR( -20001, 
          'Fecha Invalidad: La fecha debe ser mayor o igual a la actual - ingresado = ' || 
          to_char( :new.fechaEntrega, 'dd/mm/yyyy HH24:MI:SS' ) );
  END IF; 
END;
/