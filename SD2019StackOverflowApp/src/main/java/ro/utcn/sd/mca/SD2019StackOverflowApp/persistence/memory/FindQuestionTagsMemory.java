package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

import java.util.List;

class FindQuestionTagsMemory implements MemorySpecification<Tag> {
    private List<QuestionTag> questionTagList;

    FindQuestionTagsMemory(Integer questionId) {
        FindQuestionTagByQuestionIdMemory specification = new FindQuestionTagByQuestionIdMemory(questionId);
        MemoryRepository<QuestionTag> questionTagMemoryRepository = MemoryRepositoryFactory.questionTagRepository;
        questionTagList = questionTagMemoryRepository.query(specification);
    }

    @Override
    public boolean specified(Tag tag) {
        for (QuestionTag qt : questionTagList) {
            if (qt.getTagId().equals(tag.getId()))
                return true;
        }
        return false;
    }
}
