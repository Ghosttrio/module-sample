package org.ghosttrio.backend1.feign;

import com.querydsl.core.types.OrderSpecifier;
import org.ghosttrio.backend1.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "SearchFeign", url = "http://localhost:8083" // AI 서버
)
public interface SearchFeign {

    /* 2번 방법 */
//    @GetMapping("/order1")
//    OrderSpecifier<?> getOrder();

    /* 3번 방법 */
    @PostMapping("/order3")
    List<User> getOrder(@RequestBody List<User> list);

    /* 4번 방법 */
    @GetMapping("/order4")
    List<User> getResult();
}
