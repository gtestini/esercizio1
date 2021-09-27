package Esercitazioni.es1;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class MappaLibri implements RowMapper<Libro>
{
    @Override
    public Libro mapRow(ResultSet resultSet, int i) throws SQLException
    {
        Libro l = new Libro();
        l.setTitolo(resultSet.getString("titolo"));
        l.setAutore(resultSet.getString("autore"));
        l.setAnno_pb(resultSet.getString("anno_pb"));
        l.setLink(resultSet.getString("link"));
        return l;
    }
}
