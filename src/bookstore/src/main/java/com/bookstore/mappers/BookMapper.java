package com.bookstore.mappers;

import com.bookstore.models.Book;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;


/**
 * This class is a mapper for the Book object. It implements the RowMapper interface provided by Spring JDBC.
 * It is used to map a row of the result set to a Book instance.
 */
public final class BookMapper implements RowMapper<Book>{

    /**
     * This method is used to map a row of the result set to a Book instance.
     *
     * @param rs The ResultSet object that contains the data returned by a SQL query.
     * @param rowNum The number of the current row.
     * @return A Book instance that represents the current row of the result set.
     * @throws SQLException If a database access error occurs or this method is called on a closed result set.
     */
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {


        return Book.builder()
                .id(rs.getString("id"))
                .title(rs.getString("title"))
                .subtitle(Optional.ofNullable(rs.getString("subtitle")))
                .description(rs.getString("description"))
                .authors(rs.getString("authors"))
                .publisher(rs.getString("publisher"))
                .pages(rs.getInt("pages"))
                .year(rs.getInt("published_year"))
                .image(rs.getString("image"))
                .price(rs.getDouble("price"))
                .categories(Arrays.asList(rs.getString("categories").split(",")))
                .build();
    }

}
