package com.example.admin.repository;

import com.example.admin.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void  create() throws Exception{
        //given
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createAt = LocalDateTime.now();
        String createBy = "AdminServer";


        //when
        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createAt);
        category.setCreatedBy(createBy);

        Category newCategory = categoryRepository.save(category);


        //then


        assertNotNull(newCategory);
        assertEquals(newCategory.getType(),type);
        assertEquals(newCategory.getTitle(),title);
    }

    @Test
    public void  read() throws Exception{
        //given
        String type = "COMPUTER";

        Optional<Category> optionalCategory = categoryRepository.findByType(type);

        optionalCategory.ifPresent(c-> {

            Assertions.assertEquals(c.getType(),type);
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });
        //when

        //then
    }
}