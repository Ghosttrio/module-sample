package org.ghosttrio.backend1.feign;

import com.querydsl.core.types.OrderSpecifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "SearchFeign", url = "http://localhost:8082"
)
public interface SearchFeign {

    @GetMapping("/order")
    OrderSpecifier<?> getOrder();
}
