package org.library.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.library.model.UserContent;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserContentService {

    @NonNull
    public List<UserContent> searchContent() {
        //TODO:
        return null;

    }
    @NonNull
    public UserContent saveContent(UserContent userContent) {
        //TODO:
        return null;
    }
}
