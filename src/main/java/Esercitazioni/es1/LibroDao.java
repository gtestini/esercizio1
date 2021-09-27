package Esercitazioni.es1;

import java.util.List;

public interface LibroDao
{
    List<Libro> findAll();
    void insertLibro(Libro libro);

    void updateLibro(Libro libro);

    void executeUpdateLibro(Libro libro);

    public void deleteLibro(Libro libro);
}