package com.spharosacademy.project.SSGBack.Oauth2.domain;

import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import java.util.Map;
import javax.management.relation.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Slf4j
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String userName;
    private String userEmail;
    private String mobile;
    private UserRole role;

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .userName(String.valueOf(attributes.get("name")))
            .userEmail(String.valueOf(attributes.get("email")))
            .mobile(String.valueOf(attributes.get("mobile")))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("naver response : " + response);

        return OAuthAttributes.builder()
            .userName(String.valueOf(response.get("name")))
            .userEmail(String.valueOf(response.get("email")))
            .mobile(String.valueOf(response.get("mobile")))
            .attributes(response)
            .nameAttributeKey(userNameAttributeName)
            .build();
    }

    public User toEntity() {
        return User.builder()
            .userName(userName)
            .userEmail(userEmail)
            .userPhone(mobile)
            .role(UserRole.ROLE_SOCIAL)
            .fromSocial("NAVER")
            .build();
    }
}