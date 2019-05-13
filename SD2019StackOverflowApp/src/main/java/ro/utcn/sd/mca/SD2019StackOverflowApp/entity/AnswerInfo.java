package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class AnswerInfo {
    @Getter
    private Answer answer;
    @Getter
    private Integer votes;
}
