package com.mywork.discoman.domain.authority.domain;

import com.mywork.discoman.domain.user.domain.EnumRole;
import com.mywork.discoman.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private EnumRole role;
}
