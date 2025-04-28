package com.bandrolling.bandrolling.dto;

import java.util.List;

public class BandResponseDto {
    private Integer id;
    private String name;
    private List<MemberDto> members;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(List<MemberDto> members) {
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MemberDto> getMembers() {
        return members;
    }


    public static class MemberDto {
        private final Integer userId;
        private final String userName;
        private final String role;

        public MemberDto(Integer userId, String userName, String role) {
            this.userId = userId;
            this.userName = userName;
            this.role = role;
        }

        public Integer getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getRole() {
            return role;
        }
    }
}
