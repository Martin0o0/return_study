package com.rebe.returnstudy.Controller;

import com.rebe.returnstudy.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OneToManyController {

    private final CategoryService categoryService;

    @GetMapping("/one-to-many-category")
    public void OneToManySave(){
        categoryService.OneToManyPostHashTagSave();
    }
    @GetMapping("/one-to-many-category-read")
    public void OneToManyRead(){
        categoryService.OneToManyPostHashTagRead(1L);
    }
}
