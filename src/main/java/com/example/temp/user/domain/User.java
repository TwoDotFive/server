package com.example.temp.user.domain;

import com.example.temp.baseball.domain.Team;
import com.example.temp.common.entity.BaseTimeEntity;
import com.example.temp.common.util.IdUtil;
import com.example.temp.user.domain.value.Nickname;
import com.example.temp.user.domain.value.PlatformType;
import com.example.temp.user.domain.value.UserRole;
import com.example.temp.user.service.oauth.response.OAuthResponse;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements Persistable<Long> {
    @Id
    private Long id;
    private Long platformId;
    @Enumerated(EnumType.STRING)
    private PlatformType platformType;
    private String email;
    private String name;
    @Embedded
    private Nickname nickname;
    private String profileImageUrl;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @ManyToOne
    @JoinColumn(name = "teamId", referencedColumnName = "id")
    private Team favoriteTeam;
    private String oneLiner; // 한 줄 소개


    public static User build(OAuthResponse response) {
        User user = new User();
        user.id = IdUtil.create();
        user.platformId = response.getPlatformId();
        user.platformType = response.getPlatformType();
        user.email = response.getEmail();
        user.name = response.getName();
        user.nickname = new Nickname(response.getName());
        user.profileImageUrl = response.getProfileImageUrl();
        user.userRole = UserRole.USER;
        return user;
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public void updateProfile(String nickname, String oneLiner, String profileImageUrl) {
        this.nickname = new Nickname(nickname);
        this.oneLiner = oneLiner;
        this.profileImageUrl = profileImageUrl;
    }

    public void updateFavoriteTeam(Team team) {
        this.favoriteTeam = team;
    }

    @Override
    public boolean isNew() {
        return getCreatedAt() == null;
    }
}
