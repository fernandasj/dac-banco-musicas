package io.github.fernandasj.domain;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author fernanda
 */
public class Banda {
    private int idBanda;
    private String nome;
    private String localDeOrigem;
    private List<String> integrantes;

    public Banda(int idBanda, String nome, String localDeOrigem, List<String> integrantes) {
        this.idBanda = idBanda;
        this.nome = nome;
        this.localDeOrigem = localDeOrigem;
        this.integrantes = integrantes;
    }

    Banda(String nome, String localDeOrigem, String integrantes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdBanda() {
        return idBanda;
    }

    public void setIdBanda(int idBanda) {
        this.idBanda = idBanda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalDeOrigem() {
        return localDeOrigem;
    }

    public void setLocalDeOrigem(String localDeOrigem) {
        this.localDeOrigem = localDeOrigem;
    }

    public List<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<String> integrantes) {
        this.integrantes = integrantes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.idBanda;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.localDeOrigem);
        hash = 83 * hash + Objects.hashCode(this.integrantes);
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
        final Banda other = (Banda) obj;
        if (this.idBanda != other.idBanda) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Banda{" + "idBanda=" + idBanda + ", nome=" + nome + ", "
                + "localDeOrigem=" + localDeOrigem + ", integrantes=" + integrantes + '}';
    }
}