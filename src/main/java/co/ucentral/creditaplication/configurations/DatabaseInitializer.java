package co.ucentral.creditaplication.configurations;
import co.ucentral.creditaplication.models.dtos.SignUpDto;
import co.ucentral.creditaplication.models.enums.UserRole;
import co.ucentral.creditaplication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final AuthService authService;

    @Value("${admin.username}")
    private String adminUser;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired
    public DatabaseInitializer(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (authService.loadUserByUsername("admin") == null) {
            authService.signUp(new SignUpDto(this.adminUser, this.adminPassword, UserRole.ADMIN));
        }
    }
}
