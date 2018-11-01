package io.github.fernandasj.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author fernanda
 */
public class Album {
    private int idAlbum;
    private Estilo estilo;
    private int banda;
    private LocalDate anoDeLancamento;
    
    public Album(int idAlbum, Estilo estilo, int banda, LocalDate anoDeLancamento) {
        this.idAlbum = idAlbum;
        this.estilo = estilo;
        this.banda = banda;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Album(Estilo estilo, int banda, LocalDate anoDeLancamento) {
        this.estilo = estilo;
        this.banda = banda;
        this.anoDeLancamento = anoDeLancamento;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public int getBanda() {
        return banda;
    }

    public void setBanda(int banda) {
        this.banda = banda;
    }

    public LocalDate getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(LocalDate anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.idAlbum;
        hash = 97 * hash + Objects.hashCode(this.estilo);
        hash = 97 * hash + Objects.hashCode(this.banda);
        hash = 97 * hash + Objects.hashCode(this.anoDeLancamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (this.idAlbum != other.idAlbum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Album{" + "idAlbum=" + idAlbum + ", estilo=" + estilo + ", "
                + "banda=" + banda + ", anoDeLancamento=" + anoDeLancamento + '}';
    }   
}