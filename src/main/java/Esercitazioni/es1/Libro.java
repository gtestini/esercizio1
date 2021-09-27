package Esercitazioni.es1;

import lombok.Data;

@Data
public class Libro
{
    String titolo;
    String autore;
    String anno_pb;
    String link;

    public Libro()
    {

    }

    public Libro(String titolo,String autore, String anno_pb, String link)
    {
        this.titolo = titolo;
        this.autore = autore;
        this.anno_pb = anno_pb;
        this.link = link;
    }

}