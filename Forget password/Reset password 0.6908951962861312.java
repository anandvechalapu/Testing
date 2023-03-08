

Controller:

@RestController
@RequestMapping("/reset")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @PostMapping("/password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

        //validate the request

        //call the service to reset the password
        resetPasswordService.resetPassword(resetPasswordRequest);

        return ResponseEntity.ok("Password reset successful!");
    }

}

Service:

@Service
public class ResetPasswordService {

    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        //generate token, send mail containing the token
        String token = UUID.randomUUID().toString();

        //persist the token in the database
        resetPasswordRepository.save(resetPasswordRequest.getEmail(), token);

        //send mail to the user
        sendResetPasswordMail(resetPasswordRequest.getEmail(), token);
    }

    private void sendResetPasswordMail(String email, String token) {
        //send mail using some mail api
    }
}

Repository:

@Repository
public class ResetPasswordRepository {

    //implement methods to save/get/delete token
    public void save(String email, String token) {
        //save the token in the database using some ORM
    }

    public String getToken(String email) {
        //fetch the token from the database using some ORM
    }

    public void deleteToken(String email) {
        //delete the token from the database using some ORM
    }

}