package org.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.library.common.AbstractTest;
import org.library.exception.EntityNotFoundException;
import org.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@DisplayName("user content service test suite")
public class UserServiceTest extends AbstractTest {
  @Autowired
  private UserService userService;

  @Test
  @DisplayName("should get user")
  public void shouldGetUser() {
    //arrange-act
    final User savedUser = userService.findUserById(1L);

    //assert
    assertThat(savedUser.getId()).isEqualTo(1L);
  }

  @Test
  @DisplayName("should throw exception when user was not found")
  public void shouldThrowExceptionWhileGettingUser() {
    //arrange-act-assert
    assertThatThrownBy(() -> userService.findUserById(5L))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("User with id 5 not found.");
  }
}
