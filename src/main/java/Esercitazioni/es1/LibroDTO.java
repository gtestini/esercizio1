package Esercitazioni.es1;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LibroDTO
{
    @Size(min = 1, max = 50, message = "About Me must be between 1 and 50 characters")
    String titolo;
    @Size(min = 1, max = 50, message = "About Me must be between 1 and 50 characters")
    String autore;
    @Size(min = 4, max = 4, message = "About Me must be between 1 and 50 characters")
    String anno_pb;
    @Size(min = 1, max = 50, message = "About Me must be between 1 and 50 characters")
    String link;
}
