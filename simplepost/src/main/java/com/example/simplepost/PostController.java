package com.example.simplepost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    //글 작성
    @PostMapping("post")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    //글 목록을 조회
    @GetMapping("/posts")
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    //글 목록을 페이징하여 반환

    //글 번호로 조회

    //글 내용으로 검색
}
