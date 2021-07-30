package github.resources.img.check.core;

public class ContextHolder {

    private static final ThreadLocal<UserContext> THREAD_LOCAL = ThreadLocal.withInitial(UserContext::new);

    private final static ContextHolder CONTEXT_HOLDER = new ContextHolder();

    private ContextHolder(){

    }

    public static ContextHolder getInstance(){
        return ContextHolder.CONTEXT_HOLDER;
    }

    public UserContext getContext(){
        return THREAD_LOCAL.get();
    }

}
