package io.github.fernandasj.domain;

import java.util.List;

/**
 *
 * @author fernanda
 */
public interface AlbumDAO{
   
    void salvar(Album album);
    
    void excluir(Album album);
    
    Album buscar(int id);
    
    void atualizar(Album album);

    List<Album> todosOsAlbuns();
}
