package com.technokratos.util;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class ServletUtil {

    public UUID getUserId(HttpSession session) {
        return UUID.fromString((String) session.getAttribute("userId"));
    }

}
