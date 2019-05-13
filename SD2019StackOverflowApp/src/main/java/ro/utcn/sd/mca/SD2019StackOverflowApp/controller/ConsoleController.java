package ro.utcn.sd.mca.SD2019StackOverflowApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.AnswerManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.QuestionManagementService;
import ro.utcn.sd.mca.SD2019StackOverflowApp.service.SOUserManagementService;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class ConsoleController implements CommandLineRunner {
    private final SOUserManagementService SOUserManagementService;
    private final QuestionManagementService questionManagementService;
    private final AnswerManagementService answerManagementService;

    @Override
    public void run(String... args) {
        ConsoleCommandHandler consoleCommandHandler = new ConsoleCommandHandler(
                SOUserManagementService,
                questionManagementService,
                answerManagementService);
        consoleCommandHandler.welcome();
        consoleCommandHandler.getAvailableCommands();
        boolean exit = false;
        while (!exit) {
            try {
                exit = consoleCommandHandler.handleCommand();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
