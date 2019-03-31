package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;

class FindQuestionTagByTagIdMemory implements MemorySpecification<QuestionTag> {
    private Integer tagId;

    FindQuestionTagByTagIdMemory(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean specified(QuestionTag questionTag) {
        return tagId.equals(questionTag.getTagId());
    }
}
