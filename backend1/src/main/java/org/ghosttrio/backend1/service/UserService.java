package org.ghosttrio.backend1.service;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.ghosttrio.backend1.feign.SearchFeign;
import org.springframework.stereotype.Service;

import static org.ghosttrio.backend1.domain.QUser.user;


@Service
@RequiredArgsConstructor
public class UserService {

    private final JPAQueryFactory query;
    private final SearchFeign searchFeign;

    /* 1번 방법 */
    public void searchA() {
        query.selectFrom(user)
                .where(user.age.gt(20))
                .orderBy(user.age.desc())
                .fetch();
    }

    public void searchB() {
        query.selectFrom(user)
                .where(user.age.gt(20))
                .orderBy(orderB())
                .fetch();
    }

    public OrderSpecifier<?> orderB() {
        return new OrderSpecifier<>(Order.DESC, user.age);
    }

    /* 2번 방법 */
    public void searchC() {
        query.selectFrom(user)
                .where(user.age.gt(20))
                .orderBy(orderC())
                .fetch();
    }
    public OrderSpecifier<?> orderC() {
        return searchFeign.getOrder();
    }

    /* 3번 방법 */
}
