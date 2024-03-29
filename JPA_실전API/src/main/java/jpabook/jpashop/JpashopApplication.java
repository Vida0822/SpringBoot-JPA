package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

    public static void main(String[] args) {

        /*Hello hello = new Hello();
        hello.setData("hello") ;
        System.out.println(hello.getData());*/


        SpringApplication.run(JpashopApplication.class, args);


    }
    @Bean
    Hibernate5Module hibernate5Module(){
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        // lazy loading 으로 데이터가 로딩된 애들만(proxy가 초기화 된 애들만) api에 반환됨 (proxy면 데이터 안 뿌림)
        // hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING , true) ;
        return hibernate5Module ;
    };

}
