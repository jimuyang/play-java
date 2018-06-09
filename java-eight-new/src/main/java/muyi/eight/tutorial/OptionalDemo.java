package muyi.eight.tutorial;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: muyi-macpro
 * @datetime: 2018/5/27 上午12:07
 * @description:
 */
public class OptionalDemo {

    /**
     * some examples
     */
    public class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 传统写法下 需要很多的非null判断
     * @param user
     */
    public static String getName(User user) {
        if (user == null)
            return "Unknown";
        return user.getName();
    }

    /**
     * 使用optional进行链式调用
     */
    public static String getNewName(User user) {
        return Optional.ofNullable(user).map(User::getName).orElse("Unknown");
    }

    /**
     * another example
     */
    public class Competition {

        private CompResult compResult;

        public CompResult getCompResult() {
            return compResult;
        }

        public void setCompResult(CompResult compResult) {
            this.compResult = compResult;
        }

        public class CompResult {
            private User champion;

            public User getChampion() {
                return champion;
            }

            public void setChampion(User champion) {
                this.champion = champion;
            }
        }
    }

    public static String getChampionName(Competition competition) {
        if (competition != null) {
            Competition.CompResult result = competition.getCompResult();
            if (result != null) {
                User champion = result.getChampion();
                if (champion != null) {
                    String name = champion.getName();
                    return name;
                }
            }
        }
        throw new RuntimeException("The champion name cannot found!");
    }

    public static String getChampionNameOptional(Competition competition) {
        return Optional.ofNullable(competition)
                .map(Competition::getCompResult)
                .map(Competition.CompResult::getChampion)
                .map(User::getName)
                .orElseThrow(() -> new RuntimeException("The champion name cannot found!"));
    }

}


