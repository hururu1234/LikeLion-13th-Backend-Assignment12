package com.likelion.search_likelion.post.domain.repository;


import com.likelion.search_likelion.post.domain.Category;
import com.likelion.search_likelion.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("select p from Post p where " +
    "(:keyword is null or :keyword= '' or lower(p.title) like lower(concat('%', :keyword, '%'))"+
    "or lower(p.content) like lower(concat('%', :keyword, '%'))) and" +
            "(:category is null or p.category = :category)")
    Page<Post> findKeywordAndCategory(@org.springframework.data.repository.query.Param("keyword") String keyword,
                                      @org.springframework.data.repository.query.Param("category")Category category, Pageable pageable);
}
