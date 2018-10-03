-- Primero crearemos las 3 categor�as
INSERT INTO CATEGORIA (IDCATEGORIA, TIPOCAT) VALUES (1, 'ASEO PERSONAL');

INSERT INTO CATEGORIA (IDCATEGORIA, TIPOCAT) VALUES (2, 'COMPUTADORES');

INSERT INTO CATEGORIA (IDCATEGORIA, TIPOCAT) VALUES (3, 'FRUTAS Y VERDURAS');


-- Vamos creando cada producto y asociandolo con su categoria

-- Productos de aseo personal

INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('57d9b18e', 'Jabon', '1 unidad en caja','Dove', '125','gr', '125 gr');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (1, '57d9b18e');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('fc9847d6', 'Shampoo','Botella','Pantene','500','cm3','500 cm3');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (1, 'fc9847d6');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('10893f3ea', 'Crema Dental', 'Tubo','Colgate', '100','cm^3', '100 cm^3');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (1, '10893f3ea');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('239109cc6', 'Crema de afeitar', 'Spray', 'Prestobarba', '300','cm^3', '300 cm^3');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (1, '239109cc6');


-- Computadores

INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('cf88a7b7', 'Portatil', 'Caja', 'Asus', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (2, 'cf88a7b7');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('1a1b593ae', 'Notebook', 'Caja', 'Acer', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (2, '1a1b593ae');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('154422331', 'PC Escritorio', 'Caja', 'Dell', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (2, '154422331');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('cfd36e72', 'Portatil', 'Caja', 'Janus', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (2, 'cfd36e72');



-- Frutas y verduras

INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('113a25be9', 'Tomate', '1 unidad','Tomatico', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (3, '113a25be9');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('1fb6498b1', 'Cebolla', '1 unidad','Cebollita', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (3, '1fb6498b1');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('fd7a5f75', 'Mazorca', '1 unidad','Mazorquita', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (3, 'fd7a5f75');


INSERT INTO PRODUCTO (CODIGODEBARRAS, NOMBRE, PRESENTACION, MARCA, CANTIDAD, UNIDADDEMEDIDA, ESPECIFICACIONEMPACADO) VALUES ('4f918bc6', 'Ajo', '1 unidad', 'Ajitos', '1','unidad', '1 unidad');

INSERT INTO CATEGORIAPRODUCTO(IDCATEGORIA, IDPRODUCTO) VALUES (3, '4f918bc6');


