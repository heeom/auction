package com.ddang.auction.web.security.dto;

import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType; //토큰의 타입 Bearer = jwt, OAuth
    private long accessTokenExpireTime;
}
