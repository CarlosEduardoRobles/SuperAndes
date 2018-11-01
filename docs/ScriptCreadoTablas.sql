create sequence SuperAndes_sequence;

-- Crear tablas
CREATE TABLE Producto
(
    codigoDeBarras VARCHAR(80) NOT NULL,
    nombre VARCHAR(80),
    presentacion VARCHAR(80),
    marca VARCHAR(80),
    cantidad INTEGER,
    unidadDeMedida VARCHAR(80),
    especificacionEmpacado VARCHAR(80),
    idCategoria INTEGER NOT NULL,
    CONSTRAINT producto_pk PRIMARY KEY(codigoDeBarras)
);

CREATE TABLE Sucursal
(
    idSucursal INTEGER NOT NULL,
    nombre VARCHAR(80),
    segmentacion VARCHAR(80),
    tamano VARCHAR(80),
    ciudad VARCHAR(80),
    direccion VARCHAR(80),
    CONSTRAINT sucursal_pk PRIMARY KEY(idSucursal)
);

CREATE TABLE ProductoOfrecidoSucursal
(
    idSucursal INTEGER NOT NULL, 
    codigoBarras VARCHAR(80) NOT NULL,
    precioUnitario INTEGER,
    precioUnidadMedida INTEGER,
    nivelDeReorden INTEGER,
    cantidadRecompra INTEGER,
    CONSTRAINT productoofrecidosucursal_pk PRIMARY KEY(idSucursal, codigoBarras)
);

CREATE TABLE Estante
(
    id INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,   
    idCategoria INTEGER NOT NULL,
    volumenActual NUMBER NOT NULL,
    volumenMaximo NUMBER NOT NULL,
    pesoActual NUMBER NOT NULL,
    pesoMaximo NUMBER NOT NULL,
    nivelDeAbastecimiento INTEGER NOT NULL,
    CONSTRAINT estante_pk PRIMARY KEY(idSucursal, id)
);

CREATE TABLE ProductosEstante
(
    idEstante INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    codigoBarras VARCHAR(80) NOT NULL,
    cantidadProducto INTEGER NOT NULL,
    CONSTRAINT productosestante_pk PRIMARY KEY (idEstante, idSucursal, codigoBarras)
);

CREATE TABLE Bodega
(    
    id INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    idCategoria INTEGER NOT NULL,
    volumenActual NUMBER NOT NULL,
    volumenMaximo NUMBER NOT NULL,
    pesoActual NUMBER NOT NULL,
    pesoMaximo NUMBER NOT NULL,
    CONSTRAINT bodega_pk PRIMARY KEY(idSucursal, id)
);

CREATE TABLE ProductosBodega
(
    idBodega INTEGER NOT NULL,
    idSucursal INTEGER NOT NULL,
    codigoBarras VARCHAR(80) NOT NULL,
    cantidadProducto INTEGER NOT NULL,
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
    precioTotal INTEGER,
    estadoOrden VARCHAR(80),
    NitProveedor VARCHAR(80),
    idSucursal NUMBER,
    CONSTRAINT pedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE ProductoPedido
(
    codigoProducto VARCHAR(80) NOT NULL,
    codigoPedido INTEGER NOT NULL,
    volumen INTEGER,
    precio INTEGER,
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
    precio INTEGER,
    calificacionCalidad VARCHAR(80),
    CONSTRAINT proveedorproducto_pk PRIMARY KEY(NitProveedor, codigoProducto)
);

CREATE TABLE LlegadaPedido
(
    codigoPedido INTEGER NOT NULL,
    idsucursal INTEGER,
    fechaEntrega DATE,
    cantidadProductos INTEGER,
    calidadProductos VARCHAR(80),
    calificacion VARCHAR(80),
    CONSTRAINT llegadapedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE Cliente
(
    tipoDocumento VARCHAR(80) NOT NULL,
    numDocumento VARCHAR(80) NOT NULL,
    nombre VARCHAR(80),
    correo VARCHAR(80),
    CONSTRAINT cliente_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE PersonaJuridica
(
    tipoDocumento VARCHAR(80) NOT NULL,
    numDocumento VARCHAR(80) NOT NULL,
    direccion VARCHAR(80),
    CONSTRAINT personajuridica_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE Venta
(
    numeroVenta INTEGER NOT NULL,
    tipoDocCliente VARCHAR(80) NOT NULL,
    numDocCliente VARCHAR(80) NOT NULL,
    idSucursal INTEGER NOT NULL,
    fechaVenta DATE,
    totalVenta INTEGER,    
    CONSTRAINT venta_pk PRIMARY KEY(numeroVenta)
);

CREATE TABLE VentaProducto
(
    numeroVenta INTEGER NOT NULL,
    codigoProducto VARCHAR(80) NOT NULL,
    unidades INTEGER,
    CONSTRAINT ventaproducto_pk PRIMARY KEY(numeroVenta, codigoProducto)
);

CREATE TABLE Promocion
(
    codigoPromocion VARCHAR(80) NOT NULL,
    tipoPromocion INTEGER,
    fechaTerminacion DATE,
    CONSTRAINT promocion_pk PRIMARY KEY(codigoPromocion)
);

CREATE TABLE VentaPromocion
(
    numeroVenta INTEGER NOT NULL,
    codigoPromo VARCHAR(80) NOT NULL,
    unidades INTEGER,
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
    llevaUnidades INTEGER,
    CONSTRAINT paguenunidadesllevempromo_pk PRIMARY KEY(codigoPromo)
);


CREATE TABLE PaqueteDeProductosPromo
(
	codigoPromo VARCHAR(80) NOT NULL,
	precioPromo INTEGER NOT NULL,
	CONSTRAINT paquetedeproductospromo_pk PRIMARY KEY (codigoPromo)
);

CREATE TABLE PagueXCantidadLleveYPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    cantidadPaga INTEGER NOT NULL,
    cantidadLleva INTEGER,
    CONSTRAINT paguexcantidadlleveypromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE DescPorcentajePromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    porcentajeDesc INTEGER,
    CONSTRAINT descporcentajepromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE Pague1Lleve2ConDescPromo
(
    codigoPromo VARCHAR(80) NOT NULL,
    porcentajeDesc INTEGER,
    CONSTRAINT pague1lleve2condescpromo_pk PRIMARY KEY(codigoPromo)
);


-- Crear llaves foraneas

ALTER TABLE PaqueteDeProductosPromo
	ADD FOREIGN KEY (codigoPromo)
	REFERENCES Producto(codigoDeBarras)
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
;
    
ALTER TABLE VentaProducto
    ADD    FOREIGN KEY (numeroVenta)
    REFERENCES Venta(numeroVenta)
;
    
ALTER TABLE VentaPromocion
    ADD    FOREIGN KEY (numeroVenta)
    REFERENCES Venta(numeroVenta)
;
    
ALTER TABLE PersonaJuridica
    ADD    FOREIGN KEY (tipoDocumento, numDocumento)
    REFERENCES Cliente(tipoDocumento, numDocumento)
;
    
ALTER TABLE PagueNUnidadesLleveMPromo
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
    ON DELETE CASCADE
;
    
ALTER TABLE VentaPromocion
    ADD    FOREIGN KEY (codigoPromo)
    REFERENCES Promocion(codigoPromocion)
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
;
    
ALTER TABLE ProveedorProducto
    ADD    FOREIGN KEY (NitProveedor)
    REFERENCES Proveedor(NIT)
;
    
ALTER TABLE Pedido
    ADD    FOREIGN KEY (NitProveedor)
    REFERENCES Proveedor(NIT)
;
    
ALTER TABLE ProductoPromocion
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
;
    
    
ALTER TABLE ProductoOfrecidoSucursal
    ADD    FOREIGN KEY (codigoBarras)
    REFERENCES Producto(codigoDeBarras)
;
    
ALTER TABLE ProductoPedido
    ADD    FOREIGN KEY (codigoProducto)
    REFERENCES Producto(codigoDeBarras)
;
    
    
ALTER TABLE ProductoOfrecidoSucursal
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
;
    
ALTER TABLE Estante
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
;
    
ALTER TABLE Bodega
    ADD    FOREIGN KEY (idSucursal)
    REFERENCES Sucursal(idSucursal)
;
    
ALTER TABLE LlegadaPedido
    ADD    FOREIGN KEY (idsucursal)
    REFERENCES Sucursal(idSucursal)
;
    
ALTER TABLE ProductoPedido
    ADD    FOREIGN KEY (codigoPedido)
    REFERENCES Pedido(codigoPedido)
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
;

ALTER TABLE ProductosBodega
    ADD FOREIGN KEY (idSucursal, idBodega)
    REFERENCES Bodega (idSucursal, id)
;

ALTER TABLE ProductosBodega
    ADD FOREIGN KEY (idSucursal , codigoBarras)
    REFERENCES ProductoOfrecidoSucursal (idSucursal , codigoBarras)
;

ALTER TABLE ProductosEstante
    ADD FOREIGN KEY (idSucursal, idEstante)
    REFERENCES Estante (idSucursal, id)   
;

ALTER TABLE ProductosEstante
 ADD FOREIGN KEY (idSucursal , codigoBarras)
    REFERENCES ProductoOfrecidoSucursal (idSucursal , codigoBarras)
;

-- Restricciones 

ALTER TABLE PersonaJuridica
    ADD CONSTRAINT SOLO_NIT_PERSONAS_JURIDICAS
    CHECK (tipoDocumento IN ('NIT'))
;
