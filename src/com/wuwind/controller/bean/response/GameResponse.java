package com.wuwind.controller.bean.response;

import java.util.ArrayList;
import java.util.List;

public class GameResponse {
    public long gameId;

    public List<UserWord> userWords = new ArrayList<>();

    public static class UserWord {
        public long userId;
        public String userName;
        public String word;

        @Override
        public String toString() {
            return "UserWord{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    ", word='" + word + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GameResponse{" +
                "gameId=" + gameId +
                ", userWords=" + userWords +
                '}';
    }
}
