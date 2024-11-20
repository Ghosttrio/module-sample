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

    /* 프론트 코드 수정 없이 알고리즘 A, B, C 교체 */
    public UserResponse algorithmA() {
        List<User> result = query.selectFrom(user) 
                .where(user.age.goe(25))
                .orderBy(user.age.desc()) 
                .fetch();
        return new UserResponse(result, "A 방법 - 25살 이상 내림차순");
    }

    public UserResponse algorithmB() {
        List<User> result = query.selectFrom(user)
                .where(user.age.goe(20))
                .orderBy(user.age.desc())
                .fetch();
        return new UserResponse(result, "B 방법 - 20살 이상 내림차순");
    }

    public UserResponse algorithmC() {
        List<User> result = query.selectFrom(user)
                .where(user.age.goe(20))
                .orderBy(user.age.asc())
                .fetch();
        return new UserResponse(result, "C 방법 - 20살 이상 오름차순");
    }

    /* 1번 방법 : order by 부분만 분리 */
    public UserResponse search1() {
        List<User> result = query.selectFrom(user)
                .where(user.age.goe(20))
                .orderBy(order1())
                .fetch();
        return new UserResponse(result, "1번 방법");
    }

    public OrderSpecifier<?> order1() {
        return new OrderSpecifier<>(Order.DESC, user.age);
    }

    /* 2번 방법 : 외부 모듈에서 order by 객체를 불러오는 방법 -> 안됨 */
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

    /* 3번 방법 : 검색 결과를 다른 모듈에 보내서 정렬 후 결과 받아오는 방법 */
    public UserResponse search3() {
        List<User> list = query.selectFrom(user)
                .where(user.age.goe(20))
                .fetch();
        List<User> result = searchFeign.getOrder(list);
        return new UserResponse(result, "3번 방법");
    }

    /* 4번 방법 : 검색 자체를 다른 모듈에서 수행 */
    public UserResponse search4() {
        List<User> result = searchFeign.getResult();
        return new UserResponse(result, "4번 방법");
    }
}
