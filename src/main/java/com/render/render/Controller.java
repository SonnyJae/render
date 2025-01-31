package com.render.render;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class Controller {

    private static final List<Posts> POSTS = List.of(
            new Posts("aaa","aaaaa1111"),
            new Posts("bbbb","bbababab22222")
    );

    @GetMapping
    public List<Posts> getPosts() {
        return POSTS;
    }

}
