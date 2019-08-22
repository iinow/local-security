# Spring security

UserDetailsService 를 사용해서 사용자 인증을 처리했었는데 

사용 중인 DB 에서 비밀번호 일치? 확인? 작업을 직접 구현화 시키고 싶으면 AuthenticationProvider 를 직접 구현해서 사용하면 된다.

전에는 자신이 직접 구현한 UserDetailsService 를 사용할 경우 자동으로 Spring 내부에서는 DaoAuthenticationProvider 객체를 사용하게 된다. 

근데 AuthenticationProvider를 사용자 자신이 구현하게 된다면 UserDetailsService 를 사용하지 않아도 된다. 