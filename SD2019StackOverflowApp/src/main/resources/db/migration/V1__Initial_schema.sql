CREATE TABLE IF NOT EXISTS stack_overflow_user (
    stack_overflow_user_id serial NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    CONSTRAINT pk_stack_overflow_user PRIMARY KEY (stack_overflow_user_id),
    CONSTRAINT uq_username UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS question (
    question_id serial NOT NULL,
    stack_overflow_user_id integer NOT NULL,
    title character varying(500) NOT NULL,
    text character varying(5000) NOT NULL,
    creation_date_time timestamp NOT NULL,
    CONSTRAINT pk_question PRIMARY KEY (question_id),
    CONSTRAINT fk_question_stack_overflow_user FOREIGN KEY (stack_overflow_user_id)
        REFERENCES stack_overflow_user (stack_overflow_user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS tag (
    tag_id serial NOT NULL,
    text character varying(100) NOT NULL,
    CONSTRAINT pk_tag PRIMARY KEY (tag_id),
    CONSTRAINT uq_text UNIQUE (text)
);

CREATE TABLE IF NOT EXISTS question_tag (
    question_tag_id serial NOT NULL,
    question_id integer NOT NULL,
    tag_id integer NOT NULL,
    CONSTRAINT pk_question_tag PRIMARY KEY (question_tag_id),
    CONSTRAINT uq_question_id_tag_id UNIQUE (question_id, tag_id),
    CONSTRAINT fk_question_tag_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_question_tag_tag FOREIGN KEY (tag_id)
        REFERENCES tag (tag_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS answer (
    answer_id serial NOT NULL,
    question_id integer NOT NULL,
    stack_overflow_user_id integer NOT NULL,
    text character varying(5000) NOT NULL,
    creation_date_time timestamp NOT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (answer_id),
    CONSTRAINT fk_answer_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_answer_stack_overflow_user FOREIGN KEY (stack_overflow_user_id)
        REFERENCES stack_overflow_user (stack_overflow_user_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS question_vote (
    question_vote_id serial NOT NULL,
    stack_overflow_user_id integer NOT NULL,
    question_id integer NOT NULL,
    vote_type character varying(8) NOT NULL,
    CONSTRAINT pk_question_vote PRIMARY KEY (question_vote_id),
    CONSTRAINT uq_stack_overflow_user_id_question_id UNIQUE (stack_overflow_user_id, question_id),
    CONSTRAINT fk_question_vote_question FOREIGN KEY (question_id)
        REFERENCES question (question_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_question_vote_stack_overflow_user FOREIGN KEY (stack_overflow_user_id)
        REFERENCES stack_overflow_user (stack_overflow_user_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT ck_question_vote_type CHECK (vote_type IN ('upvote', 'downvote'))
);

CREATE TABLE IF NOT EXISTS answer_vote (
    answer_vote_id serial NOT NULL,
    stack_overflow_user_id integer NOT NULL,
    answer_id integer NOT NULL,
    vote_type character varying(8) NOT NULL,
    CONSTRAINT pk_answer_vote PRIMARY KEY (answer_vote_id),
    CONSTRAINT uq_stack_overflow_user_id_answer_id UNIQUE (stack_overflow_user_id, answer_id),
    CONSTRAINT fk_answer_vote_answer FOREIGN KEY (answer_id)
        REFERENCES answer (answer_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_answer_vote_stack_overflow_user FOREIGN KEY (stack_overflow_user_id)
        REFERENCES stack_overflow_user (stack_overflow_user_id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT ck_answer_vote_type CHECK (vote_type IN ('upvote', 'downvote'))
);