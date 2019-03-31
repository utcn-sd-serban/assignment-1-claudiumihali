package ro.utcn.sd.mca.SD2019StackOverflowApp.controller;

import ro.utcn.sd.mca.SD2019StackOverflowApp.entity.*;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidAnswerIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidQuestionIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.exception.InvalidSOUserIdException;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.AnswerManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.QuestionManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.SOUserManagementService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class ConsoleCommandHandler {
    private final SOUserManagementService SOUserManagementService;
    private final QuestionManagementService questionManagementService;
    private final AnswerManagementService answerManagementService;
    private Integer loggedInUserId = null;
    private final Scanner scanner = new Scanner(System.in);

    ConsoleCommandHandler(
            SOUserManagementService SOUserManagementService,
            QuestionManagementService questionManagementService,
            AnswerManagementService answerManagementService) {
        this.SOUserManagementService = SOUserManagementService;
        this.questionManagementService = questionManagementService;
        this.answerManagementService = answerManagementService;
    }

    void welcome() {
        System.out.println("\nWelcome to the StackOverflow console application!\n");
    }

    void getAvailableCommands() {
        if (loggedInUserId == null) {
            System.out.println("\nAvailable commands:");
            System.out.println("exit | log-in | sign-up\n");
        } else {
            System.out.println("\nAvailable commands:");
            System.out.println("   exit    |   log-out   | show-questions | filter-title | filter-tags | add-question | vote-question | expand-question");
            System.out.println("add-answer | vote-answer |   edit-answer  | delete-answer\n");
        }
    }

    boolean handleCommand() {
        System.out.print("Enter a command >>> ");
        String command = scanner.next().trim();
        if (loggedInUserId == null) {
            switch (command) {
                case "exit":
                    return true;
                case "log-in":
                    handleLogIn();
                    return false;
                case "sign-up":
                    handleSignUp();
                    return false;
                default:
                    System.out.println("Unknown command. Try again.");
                    return false;
            }
        } else {
            switch (command) {
                case "exit":
                    return true;
                case "log-out":
                    loggedInUserId = null;
                    System.out.println("\nLogged out!\n");
                    getAvailableCommands();
                    return false;
                case "add-question":
                    handleAddQuestion();
                    return false;
                case "vote-question":
                    handleVoteQuestion();
                    return false;
                case "expand-question":
                    handleExpandQuestion();
                    return false;
                case "show-questions":
                    handleShowQuestions();
                    return false;
                case "filter-title":
                    handleFilterTitle();
                    return false;
                case "filter-tags":
                    handleFilterTags();
                    return false;
                case "add-answer":
                    handleAddAnswer();
                    return false;
                case "vote-answer":
                    handleVoteAnswer();
                    return false;
                case "edit-answer":
                    handleEditAnswer();
                    return false;
                case "delete-answer":
                    handleDeleteAnswer();
                    return false;
                default:
                    System.out.println("Unknown command. Try again.");
                    return false;
            }
        }
    }

    private void handleLogIn() {
        System.out.println("Enter your username: ");
        String username = scanner.next().trim();
        System.out.println("Enter your password: ");
        String password = scanner.next().trim();
        Optional<SOUser> sOUser = SOUserManagementService.verifyUserCredentials(username, password);
        if (sOUser.isPresent()) {
            loggedInUserId = sOUser.get().getId();
            System.out.println("\nSuccessfully logged in!\n");
            getAvailableCommands();
        } else {
            System.out.println("Invalid user credentials! Try again.");
        }
    }

    private void handleSignUp() {
        System.out.println("Enter your preferred username: ");
        String username = scanner.next().trim();
        System.out.println("Enter your preferred password: ");
        String password = scanner.next().trim();
        Optional<SOUser> sOUser = SOUserManagementService.addUser(username, password);
        if (sOUser.isPresent()) {
            System.out.println("Created new SOUser: " + sOUser.get().toString());
        } else {
            System.out.println("Username already exists! Try again.");
        }
    }

    private void handleAddQuestion() {
        System.out.println("Enter question title: ");
        String title = scanner.next().trim();
        System.out.println("Enter question text: ");
        String text = scanner.next().trim();
        System.out.println("Enter tags (press enter to stop): ");
        List<String> tags = new ArrayList<>();
        scanner.nextLine();
        String tag;
        while (!(tag = scanner.nextLine()).equals("")) {
            tags.add(tag.trim());
        }
        try {
            Question question = questionManagementService.addQuestion(loggedInUserId, title, text, tags);
            System.out.println("Created new Question: " + question.toString());
        } catch (InvalidSOUserIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleVoteQuestion() {
        System.out.println("Enter question id: ");
        Integer questionId = scanner.nextInt();
        System.out.println("Enter type of vote (\"upvote\" or \"downvote\"): ");
        String vote = scanner.next().trim();
        if (vote.equals("upvote")) {
            try {
                Optional<QuestionVote> qv = questionManagementService.voteQuestion(loggedInUserId, questionId, VoteType.UPVOTE);
                if (qv.isPresent()) {
                    System.out.println("Created new QuestionVote: " + qv.get().toString());
                } else {
                    System.out.println("You cannot vote that question!");
                }
            } catch (InvalidSOUserIdException e) {
                System.out.println(e.getMessage());
            } catch (InvalidQuestionIdException e) {
                System.out.println(e.getMessage());
            }
        } else if (vote.equals("downvote")) {
            try {
                Optional<QuestionVote> qv = questionManagementService.voteQuestion(loggedInUserId, questionId, VoteType.DOWNVOTE);
                if (qv.isPresent()) {
                    System.out.println("Created new QuestionVote: " + qv.get().toString());
                } else {
                    System.out.println("You cannot vote that question!");
                }
            } catch (InvalidSOUserIdException e) {
                System.out.println(e.getMessage());
            } catch (InvalidQuestionIdException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid vote type! Try again.");
        }
    }

    private void handleExpandQuestion() {
        System.out.println("Enter question id: ");
        Integer questionId = scanner.nextInt();
        try {
            QuestionInfo qi = questionManagementService.getQuestionInfo(questionId);
            System.out.println("----------------------------------------------");
            System.out.println(qi.getQuestion().toString());
            System.out.println(qi.getTags().toString());
            System.out.println(qi.getVotes().toString());
            System.out.println("----------------------------------------------");
            List<AnswerInfo> ail = answerManagementService.getQuestionAnswers(questionId);
            for (AnswerInfo ai : ail) {
                System.out.println(ai.getAnswer());
                System.out.println(ai.getVotes());
                System.out.println("----------------------------------------------");
            }
        } catch (InvalidQuestionIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleShowQuestions() {
        List<QuestionInfo> qil = questionManagementService.getAllQuestions();
        System.out.println("----------------------------------------------");
        for (QuestionInfo qi : qil) {
            System.out.println(qi.getQuestion().toString());
            System.out.println(qi.getTags().toString());
            System.out.println(qi.getVotes().toString());
            System.out.println("----------------------------------------------");
        }
    }

    private void handleFilterTitle() {
        System.out.println("Enter title filter: ");
        String title = scanner.next().trim();
        List<QuestionInfo> qil = questionManagementService.getQuestionsByTitle(title);
        System.out.println("----------------------------------------------");
        for (QuestionInfo qi : qil) {
            System.out.println(qi.getQuestion().toString());
            System.out.println(qi.getTags().toString());
            System.out.println(qi.getVotes().toString());
            System.out.println("----------------------------------------------");
        }
    }

    private void handleFilterTags() {
        System.out.println("Enter tags (press enter to stop): ");
        List<String> tags = new ArrayList<>();
        scanner.nextLine();
        String tag;
        while (!(tag = scanner.nextLine()).equals("")) {
            tags.add(tag.trim());
        }
        List<QuestionInfo> qil = questionManagementService.getQuestionsByTags(tags);
        System.out.println("----------------------------------------------");
        for (QuestionInfo qi : qil) {
            System.out.println(qi.getQuestion().toString());
            System.out.println(qi.getTags().toString());
            System.out.println(qi.getVotes().toString());
            System.out.println("----------------------------------------------");
        }
    }

    private void handleAddAnswer() {
        System.out.println("Enter question id: ");
        Integer questionId = scanner.nextInt();
        System.out.println("Enter answer text: ");
        String text = scanner.next().trim();
        try {
            Answer a = answerManagementService.addAnswer(questionId, loggedInUserId, text);
            System.out.println("Created new answer: " + a.toString());
        } catch (InvalidQuestionIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidSOUserIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleVoteAnswer() {
        System.out.println("Enter answer id: ");
        Integer answerId = scanner.nextInt();
        System.out.println("Enter type of vote (\"upvote\" or \"downvote\"): ");
        String vote = scanner.next().trim();
        if (vote.equals("upvote")) {
            try {
                Optional<AnswerVote> av = answerManagementService.voteAnswer(loggedInUserId, answerId, VoteType.UPVOTE);
                if (av.isPresent()) {
                    System.out.println("Created new AnswerVote: " + av.get().toString());
                } else {
                    System.out.println("You cannot vote that answer!");
                }
            } catch (InvalidSOUserIdException e) {
                System.out.println(e.getMessage());
            } catch (InvalidAnswerIdException e) {
                System.out.println(e.getMessage());
            }
        } else if (vote.equals("downvote")) {
            try {
                Optional<AnswerVote> av = answerManagementService.voteAnswer(loggedInUserId, answerId, VoteType.DOWNVOTE);
                if (av.isPresent()) {
                    System.out.println("Created new AnswerVote: " + av.get().toString());
                } else {
                    System.out.println("You cannot vote that answer!");
                }
            } catch (InvalidSOUserIdException e) {
                System.out.println(e.getMessage());
            } catch (InvalidAnswerIdException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Invalid vote type! Try again.");
        }
    }

    private void handleEditAnswer() {
        System.out.println("Enter answer id: ");
        Integer answerId = scanner.nextInt();
        System.out.println("Enter new answer text: ");
        String text = scanner.next().trim();
        try {
            Optional<Answer> a = answerManagementService.editAnswer(loggedInUserId, answerId, text);
            if (a.isPresent()) {
                System.out.println("Created new Answer: " + a.get().toString());
            } else {
                System.out.println("You cannot edit that answer!");
            }
        } catch (InvalidSOUserIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAnswerIdException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteAnswer() {
        System.out.println("Enter answer id: ");
        Integer answerId = scanner.nextInt();
        try {
            boolean deleted = answerManagementService.deleteAnswer(loggedInUserId, answerId);
            if (deleted) {
                System.out.println("Answer successfully deleted.");
            } else {
                System.out.println("You cannot delete that answer!");
            }
        } catch (InvalidSOUserIdException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAnswerIdException e) {
            System.out.println(e.getMessage());
        }
    }
}
