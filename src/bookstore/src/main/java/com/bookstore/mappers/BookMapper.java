package com.bookstore.mappers;

import com.bookstore.models.Book;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public final class BookMapper implements RowMapper<Book>{
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
