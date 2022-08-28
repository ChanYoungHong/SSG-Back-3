package com.spharosacademy.project.SSGBack.oauth2.domain;

import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.entity.UserRole;
import java.util.Map;
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

    private Map<String, Object> attributes; // OAuth2 반환하는 유저 정보 Map
    private String nameAttributeKey;
    private String userName;
    private String userEmail;
    private String mobile;
    private UserRole role;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        if("kakao".equals(registrationId)) {
            return ofKakao("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        // kakao는 kakao_account에 유저정보가 있다. (email)
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");

        return OAuthAttributes.builder()
            .userName((String) kakaoProfile.get("nickname"))
            .userEmail((String) kakaoAccount.get("email"))
            .attributes(attributes)
            .nameAttributeKey(userNameAttributeName)
            .build();
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