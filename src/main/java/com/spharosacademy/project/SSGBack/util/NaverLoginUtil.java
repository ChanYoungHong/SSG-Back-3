package com.spharosacademy.project.SSGBack.util;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class NaverLoginUtil {

    private final static String CLIENT_ID = "hvDqUA3VYSNUesE1784z";
    private final static String CLIENT_SECRET = "0_xjAfFnMt";
    private final static String REDIRECT_URI = "http://localhost:8080/login/naver";
    private final static String SESSION_STATE = "oauth_state";

    public String getAuthorizationUrl(HttpSession session) {

        String state = generateRandomString();

        setSession(session, state);

        OAuth20Service oauthService = new ServiceBuilder()
            .apiKey(CLIENT_ID)
            .apiKey(CLIENT_SECRET)
            .callback(REDIRECT_URI)
            .state(state) // 난수값을 인증 URL생성 시 사용
            .build(NaverLoginApi.instance());
    }

    public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws
        IOException {

        String sessionState = getSession(session);

        if (StringUtils.pathEquals(sessionState, state)) {
            OAuth20Service oauthService = new ServiceBuilder()
                .apiKey(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .state(state)
                .build(NaverLoginApi.instance());
            /* Scribe에서 제공하는 AccessToken 획득 기능으로 네아로 Access Token을 획득 */
            OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
            return accessToken;
        }
        return null;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }

    private void setSession(HttpSession session, String state) {
        session.setAttribute(SESSION_STATE, state);
    }

    private String getSession(HttpSession session) {
        return (String) session.getAttribute(SESSION_STATE);
    }

    @RequestMapping("naverLoginCallback")
    public void LoginCallback(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam String code, @RequestParam String state, HttpSession session) throws Exception {
        System.out.println("여기는 callback");

        PrintWriter writer = response.getWriter();

        int cnt = 0;

        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginUtil.getAccessToken(session, code, state);

        String apiResult = naverLoginUtil.getUserProfile(oauthToken); //String형식의 json데이터

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(apiResult);
        org.json.simple.JSONObject jsonObj = (org.json.simple.JSONObject) obj;

        org.json.simple.JSONObject response_obj = (org.json.simple.JSONObject)jsonObj.get("response");
        // 네이버에서 주는 고유 ID
        String naverIfId = (String) response_obj.get("id");
        // 네이버에서 설정된 사용자 이름
        String naverName = (String) response_obj.get("name");
        // 네이버에서 설정된 사용자 별명
        String naverNickname = (String) response_obj.get("nickname");
        // 네이버에서 설정된 이메일
        String naverEmail = (String) response_obj.get("email");
        // 네이버에서 설정된 사용자 프로필 사진
        String naverProfileImage = (String) response_obj.get("profile_image");
        // 랜덤숫자 자리수 초기화(2자리)
        DecimalFormat decimal2Format = new DecimalFormat("00");
        // 랜덤숫자 자리수 초기화(3자리)
        DecimalFormat decimal3Format = new DecimalFormat("000");
        // 랜덤숫자 자리수 초기화(4자리)
        DecimalFormat decimal4Format = new DecimalFormat("0000");
        // 회원정보 세팅
        AivSignUpVO aivSignUpVO = new AivSignUpVO();
        aivSignUpVO.getAivSignUpBean().setUserName(naverName);
        aivSignUpVO.getAivSignUpBean().setUserEmail(naverEmail);
        aivSignUpVO.getAivSignUpBean().setUserProfileUrl(naverProfileImage);
        aivSignUpVO.getAivSignUpBean().setUserIfInfo("NAVER");
        aivSignUpVO.getAivSignUpBean().setUserIfId(naverIfId);
        boolean emailExists = false;
        // 네이버에서 주는 고유 ID의 중복여부 체크(AIV_EMPLOYEE 테이블의 USER_IF_ID의 중복체크)
        int existsUserIfIdCnt = aivSignUpService.selectExistsUserIfId(aivSignUpVO);
        // 중복되는 ID가 없을 경우 신규가입으로 아래 구문을 실행
        if(existsUserIfIdCnt == 0) {
            int existEmailCnt = aivSignUpService.selectExistsUserEmail(aivSignUpVO);
            if(existEmailCnt > 0) {
                emailExists = true;
            } else {
                // 네이버에서 주는 고유 ID 세팅
                aivSignUpVO.getAivSignUpBean().setUserId(naverIfId);
                // USER_ID의 중복된 값이 있을 경우 반복문을 통하여 랜덤수를 뒤에 부여한 뒤 USER_ID를 새로 세팅해 줌
                for(int i = 0; i < 10; i++) {
                    if(i > 0) {
                        // USER_ID가 중복된 경우 랜덤수 두 자리를 뒤에 부여해줌
                        naverIfId = naverIfId + "_" + decimal2Format.format(Math.random() * 99);
                        aivSignUpVO.getAivSignUpBean().setUserId(naverIfId);
                    }
                    // USER_ID 중복 여부 체크
                    int existsUserIdCnt = aivSignUpService.selectExistsUserId(aivSignUpVO);
                    // USER_ID 중복이 안 될 경우 USER_NICK_NAME의 중복을 체크하기 위하여 아래 구문을 실행
                    if(existsUserIdCnt == 0) {
                        // 사용 불가능한 USER_NICK_NAME 체크
                        boolean nickChk = StringUtil.checkNickName(naverNickname);
                        // USER_NICK_NAME이 사용 불가능한 단어가 포함되어 있으면 닉네임_1234(랜덤숫자 4자리를 뒤에 붙임)으로 세팅시켜 줌
                        if(!nickChk) {
                            naverNickname = "닉네임_" + decimal4Format.format(Math.random() * 9999);
                        }
                        aivSignUpVO.getAivSignUpBean().setUserNickName(naverNickname);
                        // USER_NICK_NAME의 중복된 값이 있을 경우 반복문을 통하여 랜덤수를 뒤에 부여한 뒤 USER_NICK_NAME을 새로 세팅해 줌
                        for(int j = 0; j < 10; j++) {
                            if(j > 0) {
                                // USER_NICK_NAME이 중복된 경우 랜덤수 두 자리를 뒤에 부여해줌
                                aivSignUpVO.getAivSignUpBean().setUserNickName(naverNickname + "_" + decimal3Format.format(Math.random() * 999));
                            }
                            // USER_NICK_NAME 중복 여부 체크
                            int existsUserNickNameCnt = aivSignUpService.selectExistsUserNickName(aivSignUpVO);
                            // USER_NICK_NAME 중복이 안 될 경우 회원정보 저장(회원가입 처리)
                            if(existsUserNickNameCnt == 0) {
                                // 기본 데이터 입력(EMP_ID 새로 생성처리)
                                aivSignUpVO.getAivSignUpBean().setEmpId(aivSignUpVO.getAivSignUpBean().ID_PREFIX + StringUtil.generateKey());
                                // 회원데이터 저장
                                cnt = aivSignUpService.insertIFSignUpSave(aivSignUpVO);
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        if(!emailExists) {
            // 로그인처리 로직 시작
    }
}
