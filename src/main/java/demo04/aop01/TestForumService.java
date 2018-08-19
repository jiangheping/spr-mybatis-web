package demo04.aop01;

//模拟带有横切逻辑的实例
public class TestForumService {
    public static void main(String[] args) {
        ForumService forumService = new ForumServiceImpl();

        forumService.removeForum(10);
        forumService.removeTopic(20);
    }
}
