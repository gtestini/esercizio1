package Esercitazioni.es1;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LibroDaoImpl implements LibroDao
{
    NamedParameterJdbcTemplate template;

    public LibroDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Libro> findAll()
    {
        return template.query("select * from libri", new MappaLibri());
    }

    public List<Libro> findBook(String titolo) {
        final String sql ="select * from libri where titolo=:titolo";
        SqlParameterSource param = new MapSqlParameterSource().addValue("titolo", titolo);
        return template.query(sql, param, new ResultSetExtractor<List<Libro>>() {
            @Override
            public List<Libro> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Libro> tempList = new ArrayList<Libro>();
                while(rs.next())
                {
                    Libro libro = new Libro(rs.getString("titolo"), rs.getString("autore"),
                            rs.getString("anno_pb"),rs.getString("link"));
                    tempList.add(libro);
                    break;
                }
                return tempList;
            }
        });
    }

    @Override
    public void insertLibro(Libro libro)
    {
        final String sql = "insert into libri(titolo , autore, anno_pb, link) values(:titolo,:autore,:anno_pb,:link)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("titolo",libro.getTitolo())
                .addValue("autore",libro.getAutore() )
                .addValue("anno_pb",libro.getAnno_pb())
                .addValue("link",libro.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void updateLibro(Libro libro)
    {
        final String sql = "update libri set titolo=:titolo, autore=:autore, anno_pb=:anno_pb, link=:link, where titolo=:titolo";

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("titolo",libro.getTitolo())
                .addValue("autore",libro.getAutore() )
                .addValue("anno_pb",libro.getAnno_pb())
                .addValue("link",libro.getLink());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdateLibro(Libro libro)
    {
        final String sql = "update libri set titolo=:titolo, autore=:autore, anno_pb=:anno_pb, link=:link, where titolo=:titolo";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("titolo", libro.getTitolo());
        map.put("autore", libro.getAutore());
        map.put("anno_pb", libro.getAnno_pb());
        map.put("link", libro.getLink());


        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void deleteLibro(Libro libro)
    {
        final String sql = "delete from libri where titolo=:titolo";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("titolo",libro.getTitolo());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}