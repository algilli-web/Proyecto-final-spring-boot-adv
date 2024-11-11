INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('iPhone 14 Pro', 'Smartphone alta gama', 999.99, 25, 'electronica', 'Apple Inc.', '2023-06-15');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('MacBook Air M2', 'Ultrabook ligero', 1299.00, 42, 'electronica', 'Apple Inc.', '2023-07-20');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Nespresso Vertuo', 'Cafetera capsulas premium', 199.99, 15, 'hogar', 'Nestle SA', '2023-08-01');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Samsung Neo QLED', 'Smart TV 8K', 2499.99, 8, 'electronica', 'Samsung Electronics', '2023-09-10');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Monopoly Deluxe', 'Edicion coleccionista', 49.99, 100, 'jugueteria', 'Hasbro Gaming', '2023-10-05');
INSERT INTO articulo (nombre, descripcion, unidad_precio, unidad_stock, tipo, proveedor, fecha) VALUES ('Wacom Intuos Pro', 'Tablet profesional', 399.99, 30, 'electronica', 'Wacom Co.', '2023-11-15');

INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Miguel', 'Ruiz', 'TechSolutions SA', 'Director de Tecnología', 'Calle Silicon 42', 28023, 'Madrid', '611223344', '1988-07-15');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Carmen', 'Vega', 'Carrefour', 'Jefe de Producto', 'Avenida Digital 78', 08028, 'Barcelona', '622334455', '1992-03-28');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Roberto', 'Torres', 'CloudNet SL', 'Ingeniero de Sistemas', 'Plaza Innovacion 15', 46018, 'Valencia', '633445566', '1985-11-03');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Sofia', 'Navarro', 'AILabs Corp', 'Científica de Datos', 'Calle Neural 23', 29016, 'Malaga', '644556677', '1990-09-21');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Daniel', 'Moreno', 'WebDev Plus', 'Desarrollador Web Senior', 'Avenida Code 91', 50015, 'Zaragoza', '655667788', '1993-05-17');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Elena', 'Castro', 'Mercadona', 'Analista Financiero', 'Calle Trading 55', 41012, 'Sevilla', '666778899', '1987-12-09');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Antonio', 'Ramírez', 'El Corte Inglés', 'Jefe de Ventas', 'Avenida Comercial 123', 28002, 'Madrid', '655443322', '1989-05-18');
INSERT INTO cliente (nombre, apellido, empresa, puesto, direccion, codigo_postal, provincia, telefono, fecha_nacimiento) VALUES ('Lucía', 'Martínez', 'Inditex', 'Directora de Marketing', 'Calle Moda 45', 15001, 'A Coruña', '677889900', '1991-08-25');

INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (1, 1, '2024-12-01', 1, 999.99, 21.00, 1209.99);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (2, 3, '2024-12-02', 2, 399.98, 21.00, 483.98);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (3, 2, '2024-12-03', 1, 1299.00, 21.00, 1571.79);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (4, 4, '2024-12-04', 1, 2499.99, 21.00, 3024.99);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (5, 5, '2024-12-05', 3, 149.97, 21.00, 181.46);
INSERT INTO compra (cliente_id, articulo_id, fecha, cantidad, total, iva, total_iva) VALUES (6, 6, '2024-12-06', 2, 799.98, 21.00, 967.98);