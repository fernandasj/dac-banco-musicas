CREATE TABLE banda(
    idBanda serial,
    nome varchar(100),
    localDeOrigem varchar(100),
    integrantes varchar(255),
    PRIMARY KEY(idBanda)
);

CREATE TABLE album(
    idAlbum serial,
    estilo varchar (25), 
    banda integer,
    anoDeLancamento DATE,
    PRIMARY KEY (idAlbum),
    FOREIGN KEY(banda) REFERENCES Banda(idBanda) 
);