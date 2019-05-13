package ro.utcn.sd.mca.SD2019StackOverflowApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class QuestionInfo {
    @Getter
    private Question question;
    @Getter
    private List<Tag> tags;
    @Getter
    private Integer votes;
}
