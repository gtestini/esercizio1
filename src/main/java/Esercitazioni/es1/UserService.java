package Esercitazioni.es1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    private final LibroDaoImpl ldi;

    @Autowired
    public UserService(LibroDaoImpl ldi)
    {
        this.ldi = ldi;
    }

    public boolean verifyTitolo(String titolo)
    {
        List<Libro> lista = new ArrayList<Libro>(this.ldi.findBook(titolo));

        if(lista.isEmpty())
            return false;

        return true;
    }

    public boolean saveBook(LibroDTO libro)
    {
        if(!this.verifyTitolo(libro.getTitolo())){
            Libro l = new Libro(libro.titolo,libro.autore,libro.anno_pb,libro.link);
            this.ldi.insertLibro(l);
            return true;
        }
        return false; //c'è già un utente
    }

    public List<Libro> getAllBooks(){
        return new ArrayList(this.ldi.findAll());
    }
}