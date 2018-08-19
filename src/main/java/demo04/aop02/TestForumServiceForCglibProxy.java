package demo04.aop02;

import demo04.aop01.ForumService;
import demo04.aop01.ForumServiceImpl;

public class TestForumServiceForCglibProxy {
    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        ForumService forumService = (ForumService) cglibProxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(1);
    }
}
