package com.pragmatic.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter @Getter @ToString @EqualsAndHashCode
@Builder
public class User {
    private Long id;
    private String name ;
    private String email;
    private String password;
}

