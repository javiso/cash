package com.example.project.project.mapper;

import com.example.project.project.dto.LoanApi;
import com.example.project.project.dto.PageApi;
import com.example.project.project.dto.UserApi;
import com.example.project.project.model.Loan;
import com.example.project.project.model.User;
import dev.akkinoc.spring.boot.orika.OrikaMapperFactoryConfigurer;
import ma.glasnost.orika.MapperFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

@Configuration
public class Mapper implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(User.class, UserApi.class).byDefault().register();
        orikaMapperFactory.classMap(Loan.class, LoanApi.class).field("user.id", "idUser").byDefault().register();
        orikaMapperFactory.classMap(Page.class, PageApi.class).
                field("content", "items")
                .field("totalElements", "paging.total")
                .field("number", "paging.page")
                .field("size", "paging.size")
                .byDefault().register();
    }
}