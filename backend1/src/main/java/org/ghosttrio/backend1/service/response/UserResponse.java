package org.ghosttrio.backend1.service.response;

import org.ghosttrio.backend1.domain.User;

import java.util.List;

public record UserResponse(
        List<User> list,
        String logic
) {
}
