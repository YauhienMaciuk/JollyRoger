package com.issoft.service

import com.issoft.entity.User
import org.apache.commons.lang.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.function.Consumer

@Stepwise
@SpringBootTest
class UserServiceTest extends Specification {

    @Autowired
    UserService userService

    @Shared
    User user = createUser()

    def "create user"() {
        given:
            userService.createUser(user)
        when:
            User existedUser = userService.receiveUser(user.id)
        then:
            existedUser != null
            existedUser.id == user.id
            existedUser.firstName == user.firstName
            existedUser.lastName == user.lastName
            existedUser.phoneNumber == user.phoneNumber
    }

    def "update user"() {
        given:
            user.firstName = "NewFirstName"
            user.lastName = "NewLastName"
            userService.updateUser(user)
        when:
            User updatedUser = userService.receiveUser(user.id)
        then:
            updatedUser.firstName == user.firstName
            updatedUser.lastName == user.lastName
    }

    def "delete user"() {
        given:
            Long userId = user.id
            userService.deleteUser(user.id)
        when:
            userService.receiveUser(user.id)
        then:
            thrown(NoSuchElementException)
    }

    def "receive all users"() {
        given:
            User firstUser = createUser()
            User secondUser = createUser()
            User thirdUser = createUser()

            userService.createUser(firstUser)
            userService.createUser(secondUser)
            userService.createUser(thirdUser)
        when:
            Iterable<User> userIterableFromRepository = userService.receiveUsers()
        then:
            userIterableFromRepository != null
            userIterableFromRepository.asList().isEmpty() == false
            Consumer<User> userConsumer = new Consumer<User>() {
                @Override
                void accept(User user) {
                    user != null
                }
            }
            userIterableFromRepository.asList().forEach(userConsumer)
    }

    User createUser() {
        String firstName = RandomStringUtils.randomAlphabetic(10)
        String lastName = RandomStringUtils.randomAlphabetic(10)
        int phoneNumber = new Random().nextInt(7)
        User user = new User()
        user.setFirstName(firstName)
        user.setLastName(lastName)
        user.setBirthDay(generateUserBirthDay())
        user.setPhoneNumber(phoneNumber)
        return user
    }

    private LocalDateTime generateUserBirthDay() {
        LocalDateTime birthDay = LocalDateTime.now()
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(birthDay.format(formatter), formatter)
    }
}
