package com.ust.pos.util;

import com.ust.pos.bean.CredentialsBean;

public interface Authentication {
	boolean authenticate(CredentialsBean credentialsBean);
    String authorize(String userId);
    boolean changeLoginStatus(CredentialsBean credentialsBean, int loginStatus); 
}