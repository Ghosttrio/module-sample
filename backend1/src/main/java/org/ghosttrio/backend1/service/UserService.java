package org.ghosttrio.backend1.service;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ghosttrio.backend1.domain.User;
import org.ghosttrio.backend1.feign.SearchFeign;
import org.ghosttrio.backend1.service.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.ghosttrio.backend1.domain.QUser.user;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JPAQueryFactory query;
    private final SearchFeign searchFeign;

    public UserResponse search() {
        List<User> result = query.selectFrom(user) // 조회
                .where(user.age.gt(20)) // 조건
                .orderBy(user.age.desc()) // 정렬
                .fetch();
        return new UserResponse(result, "기본 방법");
    }

    /* 1번 방법 */
    public UserResponse search1() {
        List<User> result = query.selectFrom(user)
                .where(user.age.gt(20))
                .orderBy(order1())
                .fetch();
        return new UserResponse(result, "1번 방법");
    }

    public OrderSpecifier<?> order1() {
        return new OrderSpecifier<>(Order.DESC, user.age);
    }

    /* 2번 방법 -> 안됨 */
//    public UserResponse search2() {
//        List<User> result = query.selectFrom(user)
//                .where(user.age.gt(20))
//                .orderBy(order2())
//                .fetch();
//        return new UserResponse(result, "2번 방법");
//    }
//
//    public OrderSpecifier<?> order2() {
//        return searchFeign.getOrder();
//    }

    /* 3번 방법 */
    public UserResponse search3() {
        List<User> list = query.selectFrom(user).fetch();
        List<User> result = searchFeign.getOrder(list);
        return new UserResponse(result, "3번 방법");
    }

    /* 4번 방법 */
    public UserResponse search4() {
        List<User> result = searchFeign.getResult();
        return new UserResponse(result, "4번 방법");
    }
}
