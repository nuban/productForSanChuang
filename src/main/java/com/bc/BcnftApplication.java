package com.bc;

//import com.bc.utils.RunContract;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bc.mapper")
public class BcnftApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BcnftApplication.class, args);
//        boolean a = RunContract.test();

    }

}
