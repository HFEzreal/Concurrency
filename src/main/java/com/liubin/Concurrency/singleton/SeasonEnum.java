/**
 * @description 季节
 * @author liubin
 * @date 21/6/3 15:10
 */
public enum SeasonEnum {
    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINTER("冬天")
    ;
    private String name;

    SeasonEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
