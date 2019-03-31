package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import lombok.Getter;

public enum VoteType {
    UPVOTE("upvote"), DOWNVOTE("downvote");
    @Getter
    private final String databaseText;

    VoteType(String databaseText) {
        this.databaseText = databaseText;
    }
}
