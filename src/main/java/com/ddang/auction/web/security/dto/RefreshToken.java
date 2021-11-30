package com.ddang.auction.web.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor
@Entity
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    private String key; //username
    private String value;

    public RefreshToken updateValue(String token){
        value = token;
        return this;
    }
}
