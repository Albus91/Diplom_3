package my.project.chill.constants;

public class URLs {
    public static final String CONSTRUCTOR_URL = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTER_URL = CONSTRUCTOR_URL + "/register";
    public static final String FORGOT_PASS_URL = CONSTRUCTOR_URL + "/forgot-password";
    public static final String LOGIN_PAGE_URL = CONSTRUCTOR_URL + "/login";

    // API:
    public static final String REGISTER_API = "/api/auth/register";
    public static final String LOGIN_API = "/api/auth/login";      // <- вот тут
    public static final String USER_API = "/api/auth/user";
}
