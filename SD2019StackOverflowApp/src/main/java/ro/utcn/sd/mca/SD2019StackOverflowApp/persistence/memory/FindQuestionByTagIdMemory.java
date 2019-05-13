package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;
import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.QuestionTag;

import java.util.List;

class FindQuestionByTagIdMemory implements MemorySpecification<Question> {
    private List<QuestionTag> questionTagList;

    FindQuestionByTagIdMemory(Integer tagId) {
        FindQuestionTagByTagIdMemory specification = new FindQuestionTagByTagIdMemory(tagId);
        MemoryRepository<QuestionTag> questionTagMemoryRepository = MemoryRepositoryFactory.questionTagRepository;
        questionTagList = questionTagMemoryRepository.query(specification);
    }

    @Override
    public boolean specified(Question question) {
        for (QuestionTag qt : questionTagList) {
            if (qt.getQuestionId().equals(question.getId()))
                return true;
        }
        return false;
    }
}
