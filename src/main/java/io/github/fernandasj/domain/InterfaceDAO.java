package io.github.fernandasj.domain;

import java.util.List;

/**
 *
 * @author fernanda
 */
public interface InterfaceDAO <T>{
    void salvar(T banda);
    
    void excluir(T banda);
    
    T buscar(int id);
    
    void atualizar(T banda);

    List<T> todosOsObjetos();
}
