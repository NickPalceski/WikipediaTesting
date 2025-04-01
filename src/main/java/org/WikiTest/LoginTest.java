package org.WikiTest;

import org.testng.annotations.Test;

public class LoginTest {

// 1.1 - Valid Username & Password
    @Test(priority = 1)
    public void validUsernamePassword() {
    }

// 1.2 - Invalid Username & Password
    @Test(priority = 2)
    public void invalidUsernamePassword() {
    }

// 1.3 - Valid Username & Invalid Password
    @Test(priority = 3)
    public void validUsernameInvalidPassword() {
    }

// 1.4 - Invalid Username & Valid Password
    @Test(priority = 4)
    public void invalidUsernameValidPassword() {
    }

// 1.5 - Logout
    @Test(priority = 5)
    public void logout() {
    }

}
