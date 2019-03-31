package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Question;

class FindQuestionByTitleMemory implements MemorySpecification<Question> {
    private String title;

    FindQuestionByTitleMemory(String title) {
        this.title = title;
    }

    @Override
    public boolean specified(Question question) {
        return question.getTitle().toLowerCase().contains(title.toLowerCase());
    }
}
