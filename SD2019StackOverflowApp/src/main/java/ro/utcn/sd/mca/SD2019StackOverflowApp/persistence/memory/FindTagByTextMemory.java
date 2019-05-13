package ro.utcn.sd.mca.SD2019StackOverflowApp.persistence.memory;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.Tag;

class FindTagByTextMemory implements MemorySpecification<Tag> {
    private String text;

    FindTagByTextMemory(String text) {
        this.text = text;
    }

    @Override
    public boolean specified(Tag tag) {
        return text.equals(tag.getText());
    }
}
