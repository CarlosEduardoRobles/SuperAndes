create sequence SuperAndes_sequence;

-- Crear tablas
CREATE TABLE Producto
(
    codigoDeBarras VARCHAR(20) NOT NULL,
    nombre VARCHAR(20),
    presentacion VARCHAR(20),
    marca VARCHAR(20),
    cantidad INTEGER,
    unidadDeMedida VARCHAR(20),
    especificacionEmpacado VARCHAR(20),
    idCategoria VARCHAR(20),
    CONSTRAINT producto_pk PRIMARY KEY(codigoDeBarras)
);

CREATE TABLE Sucursal
(
    idSucursal INTEGER NOT NULL,
    nombre VARCHAR(20),
    segmentacion VARCHAR(20),
    tamano VARCHAR(20),
    ciudad VARCHAR(20),
    direccion VARCHAR(20),
    CONSTRAINT sucursal_pk PRIMARY KEY(idSucursal)
);

CREATE TABLE ProductoOfrecidoSucursal
(
    idSucursal INTEGER NOT NULL,
    codigoBarras VARCHAR(20) NOT NULL,
    precioUnitario INTEGER,
    precioUnidadMedida INTEGER,
    nivelDeReorden INTEGER,
    cantidadRecompra INTEGER,
    CONSTRAINT productoofrecidosucursal_pk PRIMARY KEY(idSucursal, codigoBarras)
);

CREATE TABLE Estante
(
    idSucursal INTEGER NOT NULL,
    idEstante INTEGER NOT NULL,
    tipoproductoID INTEGER,
    capacidadVolumen INTEGER,
    capacidadTotalVolumen INTEGER,
    capacidadPeso INTEGER,
    capacidadTotalPeso INTEGER,
    nivelDeAbastecimiento INTEGER,
    idProducto VARCHAR(20),
    CONSTRAINT estante_pk PRIMARY KEY(idSucursal, idEstante)
);

CREATE TABLE Bodega
(
    idSucursal INTEGER NOT NULL,
    idBodega INTEGER NOT NULL,
    tipoproductoID INTEGER,
    capacidadVolumen INTEGER,
    capacidadTotalVolumen INTEGER,
    capacidadPeso INTEGER,
    capacidadTotalPeso INTEGER,
    idProducto VARCHAR(20),
    CONSTRAINT bodega_pk PRIMARY KEY(idSucursal, idBodega)
);

CREATE TABLE Categoria
(
    idCategoria INTEGER NOT NULL,
    tipoCat VARCHAR(20),
    CONSTRAINT categoria_pk PRIMARY KEY(idCategoria)
);

CREATE TABLE RestriccionEstante
(
    idSucursal INTEGER NOT NULL,
    id INTEGER NOT NULL,
    idCategoria INTEGER NOT NULL,
    CONSTRAINT restriccionestante_pk PRIMARY KEY(idSucursal, id, idCategoria)
);

CREATE TABLE RestriccionBodega
(
    idSucursal INTEGER NOT NULL,
    id INTEGER NOT NULL,
    idCategoria INTEGER NOT NULL,
    CONSTRAINT restriccionbodega_pk PRIMARY KEY(idSucursal, id, idCategoria)
);

CREATE TABLE Pedido
(
    codigoPedido INTEGER NOT NULL,
    fechaEntrega DATE NOT NULL,
    precioTotal INTEGER,
    estadoOrden VARCHAR(20),
    NitProveedor VARCHAR(20),
    idSucursal NUMBER,
    CONSTRAINT pedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE ProductoPedido
(
    codigoProducto VARCHAR(20) NOT NULL,
    codigoPedido INTEGER NOT NULL,
    volumen INTEGER,
    precio INTEGER,
    CONSTRAINT productopedido_pk PRIMARY KEY(codigoProducto, codigoPedido)
);

CREATE TABLE Proveedor
(
    NIT VARCHAR(20) NOT NULL,
    nombre VARCHAR(20),
    CONSTRAINT proveedor_pk PRIMARY KEY(NIT)
);

CREATE TABLE ProveedorProducto
(
    NitProveedor VARCHAR(20) NOT NULL,
    codigoProducto VARCHAR(20) NOT NULL,
    precio INTEGER,
    calificacionCalidad VARCHAR(20),
    CONSTRAINT proveedorproducto_pk PRIMARY KEY(NitProveedor, codigoProducto)
);

CREATE TABLE LlegadaPedido
(
    codigoPedido INTEGER NOT NULL,
    idsucursal INTEGER,
    fechaEntrega DATE,
    cantidadProductos INTEGER,
    calidadProductos VARCHAR(20),
    calificacion VARCHAR(20),
    CONSTRAINT llegadapedido_pk PRIMARY KEY(codigoPedido)
);

CREATE TABLE Cliente
(
    tipoDocumento VARCHAR(20) NOT NULL,
    numDocumento VARCHAR(20) NOT NULL,
    nombre VARCHAR(20),
    correo VARCHAR(20),
    CONSTRAINT cliente_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE PersonaJuridica
(
    tipoDocumento VARCHAR(20) NOT NULL,
    numDocumento VARCHAR(20) NOT NULL,
    direccion VARCHAR(20),
    CONSTRAINT personajuridica_pk PRIMARY KEY(tipoDocumento, numDocumento)
);

CREATE TABLE Venta
(
    numeroVenta INTEGER NOT NULL,
    tipoDocCliente VARCHAR(20) NOT NULL,
    numDocCliente VARCHAR(20) NOT NULL,
    idSucursal INTEGER NOT NULL,
    fechaVenta DATE,
    totalVenta INTEGER,    
    CONSTRAINT venta_pk PRIMARY KEY(numeroVenta)
);

CREATE TABLE VentaProducto
(
    numeroVenta INTEGER NOT NULL,
    codigoProducto VARCHAR(20) NOT NULL,
    unidades INTEGER,
    CONSTRAINT ventaproducto_pk PRIMARY KEY(numeroVenta, codigoProducto)
);

CREATE TABLE Promocion
(
    codigoPromocion VARCHAR(20) NOT NULL,
    tipoPromocion INTEGER,
    fechaTerminacion DATE,
    CONSTRAINT promocion_pk PRIMARY KEY(codigoPromocion)
);

CREATE TABLE VentaPromocion
(
    numeroVenta INTEGER NOT NULL,
    codigoPromo VARCHAR(20) NOT NULL,
    unidades INTEGER,
    CONSTRAINT ventapromocion_pk PRIMARY KEY(numeroVenta, codigoPromo)
);

CREATE TABLE ProductoPromocion
(
    codigoPromocion VARCHAR(20) NOT NULL,
    codigoProducto VARCHAR(20) NOT NULL,
    CONSTRAINT productopromocion_pk PRIMARY KEY(codigoPromocion, codigoProducto)
);

CREATE TABLE PagueNUnidadesLleveMPromo
(
    codigoPromo VARCHAR(20) NOT NULL,
    compraUnidades INTEGER NOT NULL,
    llevaUnidades INTEGER,
    CONSTRAINT paguenunidadesllevempromo_pk PRIMARY KEY(codigoPromo)
);


CREATE TABLE PaqueteDeProductosPromo
(
	codigoPromo VARCHAR(20) NOT NULL,
	precioPromo INTEGER NOT NULL,
	CONSTRAINT paquetedeproductospromo_pk PRIMARY KEY (codigoPromo)
);

CREATE TABLE PagueXCantidadLleveYPromo
(
    codigoPromo VARCHAR(20) NOT NULL,
    cantidadPaga INTEGER NOT NULL,
    cantidadLleva INTEGER,
    CONSTRAINT paguexcantidadlleveypromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE DescPorcentajePromo
(
    codigoPromo VARCHAR(20) NOT NULL,
    porcentajeDesc INTEGER,
    CONSTRAINT descporcentajepromo_pk PRIMARY KEY(codigoPromo)
);

CREATE TABLE Pague1Lleve2ConDescPromo
(
    codigoPromo VARCHAR(20) NOT NULL,
    porcentajeDesc INTEGER,
    CONSTRAINT pague1lleve2condescpromo_pk PRIMARY KEY(codigoPromo)
);


-- Crear llaves foraneas

ALTER TABLE PaqueteDeProductosPromo
	ADD FOREIGN KEY (codigoPromo)
	REFERENCES Producto(codigoDeBarras)
;

ALTER TABLE Bodega
    ADD FOREIGN KEY (tipoproductoID)
    REFERENCES Categoria(idCategoria)
;    

ALTER TABLE Bodega
    ADD FOREIGN KEY (idProducto)
    REFERENCES Producto(codigoDeBarras)
;

ALTER TABLE Estante
    ADD FOREIGN KEY (tipoproductoID)
    REFERENCES Categoria(idCategoria)
;    
ALTER TABLE Pedido 
    ADD FOREIGN KEY  (idSucursal)
    REFERENCES Sucursal(idSucursal)
;
ALTER TABLE Estante
    ADD FOREIGN KEY (idProducto)
    REFERENCES Producto(codigoDeBarras)
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

    
ALTER TABLE RestriccionEstante
    ADD    FOREIGN KEY (idSucursal, id)
    REFERENCES Estante(idSucursal, idEstante)
;

ALTER TABLE RestriccionEstante
    ADD    FOREIGN KEY (idCategoria)
    REFERENCES Categoria(idCategoria)
;
    
ALTER TABLE RestriccionBodega
    ADD    FOREIGN KEY (idSucursal, id)
    REFERENCES Bodega(idSucursal, idBodega)
;
    
ALTER TABLE RestriccionBodega
    ADD    FOREIGN KEY (idCategoria)
    REFERENCES Categoria(idCategoria)
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


-- Restricciones 

ALTER TABLE PersonaJuridica
    ADD CONSTRAINT SOLO_NIT_PERSONAS_JURIDICAS
    CHECK (tipoDocumento IN ('NIT'))
;
