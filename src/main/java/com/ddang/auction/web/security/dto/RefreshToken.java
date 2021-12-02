package com.ddang.auction.web.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="refresh_token")
public class RefreshToken {

    @Id
    @Column(name = "token_key")
    private String key; //username
    @Column(name = "token_value")
    private String value;


    public RefreshToken updateValue(String token){
        value = token;
        return this;
    }
}
