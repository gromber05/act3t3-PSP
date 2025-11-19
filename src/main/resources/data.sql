INSERT INTO categorias (nombre, descripcion)
VALUES ('Electrónica', 'Dispositivos electrónicos y gadgets')
    ON CONFLICT DO NOTHING;

INSERT INTO categorias (nombre, descripcion)
VALUES ('Libros', 'Libros físicos y electrónicos')
    ON CONFLICT DO NOTHING;

INSERT INTO productos (nombre, precio, stock, categoria_id)
VALUES ('Auriculares Bluetooth', 39.99, 50,
        (SELECT id FROM categorias WHERE nombre = 'Electrónica'));

INSERT INTO productos (nombre, precio, stock, categoria_id)
VALUES ('Teclado mecánico', 79.90, 30,
        (SELECT id FROM categorias WHERE nombre = 'Electrónica'));

INSERT INTO productos (nombre, precio, stock, categoria_id)
VALUES ('Libro Kotlin', 29.95, 20,
        (SELECT id FROM categorias WHERE nombre = 'Libros'));
