package com.library.validator;

import com.library.domain.Title;
import com.library.domain.enums.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class TitleValidator {

    public List<Title> findBooksByCategory (List<Title> titles, String category) {
        List<Title> resultList = new ArrayList<>();
        for (Title title : titles) {
            for (BookCategory bookCategory : title.getCategories()) {
                if(bookCategory.equals(BookCategory.valueOf(category))) {
                    resultList.add(title);
                }
            }
        }
        return resultList;
    }
}
