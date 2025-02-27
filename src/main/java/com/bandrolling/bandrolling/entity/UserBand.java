package com.bandrolling.bandrolling.entity;

import com.bandrolling.bandrolling.entity.band.Band;
import com.bandrolling.bandrolling.entity.user.User;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class UserBand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
    private Instant joinedAt;
    private String role;

    public UserBand(Builder builder) {
        this.user = builder.user;
        this.band = builder.band;
        this.joinedAt = builder.joinedAt;
        this.role = builder.role;
    }
    public UserBand(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class Builder {
        private User user;
        private Band band;
        private Instant joinedAt;
        private String role;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder band(Band band) {
            this.band = band;
            return this;
        }

        public Builder joinedAt(Instant joinedAt) {
            this.joinedAt = joinedAt;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public UserBand build() {
            return new UserBand(this);
        }
    }
}
