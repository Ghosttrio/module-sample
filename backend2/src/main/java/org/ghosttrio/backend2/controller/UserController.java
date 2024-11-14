package org.ghosttrio.backend2.controller;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ghosttrio.backend2.domain.QUser;
import org.ghosttrio.backend2.domain.User;
import org.springframework.cloud.openfeign.CollectionFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

import static org.ghosttrio.backend2.domain.QUser.user;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final JPAQueryFactory query;

//    @GetMapping("/order1")
//    public OrderSpecifier<?> order1() {
//        return new OrderSpecifier<>(Order.DESC, user.age);
//    }

    @PostMapping("/order2")
    public List<User> order2(@RequestBody List<User> list) {
        return list.stream().filter(a -> a.getAge() >= 20).sorted(Comparator.comparing(User::getAge)).toList();
    }

    @GetMapping("/result")
    public List<User> result() {
        return query.selectFrom(user)
                .where(user.age.gt(20))
                .orderBy(user.age.desc())
                .fetch();
    }
}
