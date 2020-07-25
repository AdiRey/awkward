package pl.awkward.user.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pl.awkward.user.web.UserService;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class UserAgeSchedule {

    private final UserService userService;

    @Scheduled(cron = "0 0 0 * * *")
    public void refreshAge() {
        this.userService.refreshUsersAge();
    }

}
