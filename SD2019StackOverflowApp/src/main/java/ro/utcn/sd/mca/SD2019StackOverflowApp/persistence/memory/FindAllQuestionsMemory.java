package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

class FindAllQuestionsMemory implements MemorySpecification<Question> {
    @Override
    public boolean specified(Question question) {
        return true;
    }
}
