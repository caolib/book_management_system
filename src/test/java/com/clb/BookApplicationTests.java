package com.clb;

import com.clb.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookApplicationTests {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void test() {
        System.out.println(adminMapper.getAll());
    }

}
