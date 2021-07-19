package com.library.validator.requirement;

import com.library.domain.Reader;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ReaderRequirementsChecker {

    public static boolean checkCorrectLogin(String login, List<Reader> readers) {
        for (Reader reader : readers) {
            if (login.equals(reader.getLogin())) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkCorrectPassword(char[] password) {
        if (password.length < 6) {
            log.info("The password is to short! It has to have 6 sign minimum");
            return false;
        }

        int numberOfBigLetter = 0;
        int numberOfSmallLetter = 0;
        int numberOfFigure = 0;
        int numberOfOtherCharacters = 0;

        for (char sign : password) {
            if ((sign >= 33 && sign <= 47)
                    || (sign >= 58 && sign <= 64)
                    || (sign >= 91 && sign <= 96)
                    || (sign >= 123 && sign <= 126)) {
                numberOfOtherCharacters++;
            } else if (sign >= 48 && sign <= 58) {
                numberOfFigure++;
            } else if (sign >= 65 && sign <= 90) {
                numberOfBigLetter++;
            } else if (sign >= 97 && sign <= 122) {
                numberOfSmallLetter++;
            } else {
                log.info("There are mistake in password. One of chosen sign is not allowed");
                return false;
            }
        }

        log.info("There are {} big letter, {} small letter, {} figures and {} other characters", numberOfBigLetter, numberOfSmallLetter, numberOfFigure, numberOfOtherCharacters);

        if (numberOfBigLetter == 0
                || numberOfSmallLetter == 0
                || numberOfFigure == 0
                || numberOfOtherCharacters == 0) {
            log.info("The password is not correct. It has to include minimum one big letter, " +
                    "one small letter, one figure and one other not alphanumeric character");
            return false;
        }

        return true;
    }
}
