FROM postgres
ENV POSTGRES_DB albuns
ENV POSTGRES_USER fernanda
ENV POSTGRES_PASSWORD 123

COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/