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
        name = "SearchFeign", url = "http://localhost:8083"
)
public interface SearchFeign {

//    @GetMapping("/order1")
//    OrderSpecifier<?> getOrder();

    @PostMapping("/order2")
    List<User> getOrder(@RequestBody List<User> list);

    @GetMapping("/result")
    List<User> getResult();
}
