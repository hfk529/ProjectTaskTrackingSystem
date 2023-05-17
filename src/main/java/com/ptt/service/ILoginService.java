package com.ptt.service;

public interface ILoginService {
    int verify(String username, String password, String flag);

    int verify(String username, String password);
}
