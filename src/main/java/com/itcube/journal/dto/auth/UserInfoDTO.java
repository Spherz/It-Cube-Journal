package com.itcube.journal.dto.auth;

import java.util.List;

public record UserInfoDTO(String sub, String preferred_username,
                           String email, List<String> roles,
                           boolean active, String given_name,
                           String family_name) {
}
