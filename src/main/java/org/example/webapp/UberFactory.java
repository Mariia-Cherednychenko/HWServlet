package org.example.webapp;

public class UberFactory {
    private static final UberFactory INSTANCE = new UberFactory();

    public static UberFactory getINSTANCE() {
        return INSTANCE;
    }


    private UserService userService;

    // вся сборка здесь
    private UberFactory() {
        this.userService = new UserService();
    }


    public UserService getUserService() {
        return userService;
    }


}
