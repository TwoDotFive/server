package com.example.temp.user.service.oauth.kakao;

import com.example.temp.common.exception.CustomException;
import com.example.temp.user.domain.value.PlatformType;
import com.example.temp.user.service.oauth.OAuthAdapter;
import com.example.temp.user.service.oauth.response.KakaoProfileResponse;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthKakaoAdapter implements OAuthAdapter {

    private final KakaoTokenClient kakaoTokenClient;
    private final KakaoProfileClient kakaoProfileClient;

    @Override
    public String getToken(String code) {
        try {
            // 토큰 받아오기
            return kakaoTokenClient.getAccessToken(code);
        } catch (RuntimeException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "토큰이 유효하지 않습니다");
        }
    }

    @Override
    public OAuthResponse getProfile(String accessToken) {
        try {
            // 프로필 조회
            KakaoProfileResponse profile = kakaoProfileClient.getProfile(accessToken);

            return OAuthResponse.builder()
                    .platformId(profile.getId())
                    .platformType(PlatformType.KAKAO)
                    .email(profile.getKakaoAccount().getEmail())
                    .name(profile.getKakaoAccount().getProfile().getNickname())
                    .profileImageUrl(profile.getKakaoAccount().getProfile().getThumbnailImageUrl())
                    .build();

        } catch (RuntimeException e) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "프로필 조회에 실패했습니다");
        }
    }
}